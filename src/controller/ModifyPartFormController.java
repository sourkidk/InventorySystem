package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static controller.SceneController.switchToScene;

public class ModifyPartFormController implements Initializable {

//    private Part partToModify;

    /**
     * Toggle group for In-House/Outsourced radio buttons.  The toggle group functionality is used only to keep one button selected at a time.
     */

    @FXML private ToggleGroup AddPartToggleGroup;

    /**
     * The inhouse radio button fx:ml tag
     * */

    @FXML private RadioButton inhouseRadioBtn;

    /**
     * The outsourced radio button fx:ml tag
     * */

    @FXML private RadioButton outsourcedRadioBtn;

    /**
     * This is the fx:id of the text field for the company name or machineID of the current Part.
     * */

    @FXML private TextField partAuxText;

    /**
     * This is the fx:id of the text field for the ID of the current Part.
     * */

    @FXML private TextField partIDText;

    /**
     * This is the fx:id of the text field for the inventory quantity of the current Part.
     * */

    @FXML private TextField partInvText;

    /**
     * This is the fx:id of the text field for the inventory maximum of the current Part.
     * */

    @FXML private TextField partMaxText;

    /**
     * This is the fx:id of the text field for the inventory minimum of the current Part.
     * */

    @FXML private TextField partMinText;

    /**
     * This is the fx:id of the text field for the name of the current Part.
     * */

    @FXML private TextField partNameText;

    /**
     * This is the fx:id of the text field for the price of the current Part.
     * */

    @FXML private TextField partPriceText;

    /**
     * This is the fx:id of the label field for the company name or MachineID of the current Part.
     * */

    @FXML private Text partTypeAuxField;

    /**
     * This variable holds the Part object brought in by the sendPart method.
     * */

    public Part selectedPart;

    /**
     * This method changes the label text to 'MachineID' when the inhouse button is selected.
     * */

    @FXML
    void onActionSetTypeInhouse(ActionEvent event) {

        partTypeAuxField.setText("Machine ID");
    }

    /**
     * This method changes the label text to 'Company' when the outsourced button is selected.
     * */

    @FXML
    void onActionSetTypeOutsourced(ActionEvent event) {

        partTypeAuxField.setText("Company");
    }

    /**
     * This method raises an alert to notify the user that any changes will be lost before returning to the main form.
     * */

    @FXML
    void onActionDisplayMainForm(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This is clear any changes you've made. " +
                "Do you want to continue? ");
        Optional<ButtonType> result = alert.showAndWait();
        if ( result.isPresent() && result.get() == ButtonType.OK)
            switchToScene(event,"/view/MainForm.fxml");
    }

    /**
     * The sendPart method is used by the main form to send Part data to the modify part form.
     * */

    public void sendPart(Part selectedPart) {
        this.selectedPart = selectedPart;

        partIDText.setText((String.valueOf(selectedPart.getId())));
        partNameText.setText(selectedPart.getName());
        partInvText.setText(String.valueOf(selectedPart.getStock()));
        partMaxText.setText(String.valueOf(selectedPart.getMax()));
        partMinText.setText(String.valueOf(selectedPart.getMin()));
        partPriceText.setText(String.valueOf(selectedPart.getPrice()));

        if ( selectedPart instanceof InHouse) {
            InHouse inHousePart = (InHouse) selectedPart;
            inhouseRadioBtn.setSelected(true);
            partTypeAuxField.setText("Machine ID");
            partAuxText.setText(String.valueOf(inHousePart.getMachineId()));
        }
        else {
            Outsourced outsourcedPart = (Outsourced) selectedPart;
            outsourcedRadioBtn.setSelected(true);
            partTypeAuxField.setText("Company");
            partAuxText.setText(outsourcedPart.getCompanyName());
        }
    }

    /**
     * This method does the validation on the variables for viability and then feeds the data into the object
     * constructor to update the allParts observableList data structure.
     * */

    @FXML
    void onActionSaveChanges(ActionEvent event) throws IOException {

        try {
            int id = Integer.parseInt(partIDText.getText());
            String name = partNameText.getText();
            double price = Double.parseDouble(partPriceText.getText());
            int stock = Integer.parseInt(partInvText.getText());
            int max = Integer.parseInt(partMaxText.getText());
            int min = Integer.parseInt(partMinText.getText());

            if ( max < min) {
                Alert parsAlert = new Alert(Alert.AlertType.ERROR);
                parsAlert.setTitle("Invalid Inventory Pars");
                parsAlert.setContentText("Maximum must exceed minimum.");
                parsAlert.showAndWait();
                return;
            }
            else if ( stock > min && stock <= max && (partAuxText.getText() == "")) {
                Alert parsAlert = new Alert(Alert.AlertType.ERROR);
                parsAlert.setTitle("Invalid Entry.");
                parsAlert.setContentText("You must select In-house or Outsourced and add a value for Machine ID or company.");
                parsAlert.showAndWait();
            }
            else if ( stock > min && stock <= max ) {
                if (inhouseRadioBtn.isSelected()) {
                    int machineID = Integer.parseInt(partAuxText.getText());
                    Part newPart = new InHouse(id, name, price, stock, min, max, machineID);
                    Inventory.updatePart(id, newPart);
                    switchToScene(event, "/view/MainForm.fxml");
                } else if (outsourcedRadioBtn.isSelected()) {
                    String company = partAuxText.getText();
                    Part newPart = new Outsourced(id, name, price, stock, min, max, company);
                    Inventory.updatePart(id, newPart);
                    switchToScene(event, "/view/MainForm.fxml");
                } else {
                    Alert sourceAlert = new Alert(Alert.AlertType.ERROR);
                    sourceAlert.setTitle("No Source Selected");
                    sourceAlert.setContentText("You must select In-house or Outsourced for each item.");
                    sourceAlert.showAndWait();
                    return;
                }

            } else {
                Alert invAlert = new Alert(Alert.AlertType.ERROR);
                invAlert.setTitle("Invalid Inventory Amount");
                invAlert.setContentText("The inventory quantify must be greater than the minimum and not exceed the maximum");
                invAlert.showAndWait();
                return;
            }
        } catch(NumberFormatException e) {
        }
    }

/**
 * This is the initialize method.  This class does not use the method, but I left it in as a placeholder.
 * */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
