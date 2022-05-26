package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import model.InHouse;
import model.Inventory;
import model.Outsourced;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static controller.SceneController.switchToScene;
/**
 * The addPartFormController class is the complement to the addPartForm view.  Contains the methods required to manipulate
 * data into and out of the addPart scene.
 * @author Keith Fletcher
 */

public class AddPartFormController implements Initializable {

    /**
     * Calls the generateNewID method to get the next ID in the sequence
     * */

    int nextID = generateNewID();

    /**
     * This is the fx:id of the label field for the company name or machineID of the new Part.
     * */

    @FXML
    private Text partTypeAuxField;

    /**
     * Toggle group for In-House/Outsourced radio buttons.  The toggle group functionality is used only to keep one button selected at a time.
     */

    @FXML
    private ToggleGroup AddPartToggleGroup;

    /**
     * The inhouse radio button fx:ml tag
     * */

    @FXML
    private RadioButton inhouseRadioBtn;

    /**
     * The outsourced radio button fx:ml tag
     * */

    @FXML
    private RadioButton outsourcedRadioBtn;

    /**
     * This is the fx:id of the text field for the company name or machineID of the new Part.
     * */

    @FXML
    private TextField partAuxText;

    /**
     * This is the fx:id of the text field for the company name or machineID of the new Part.
     * */

    @FXML
    private TextField partIDText;

    /**
     * This is the fx:id of the text field for the inventory quantity of the new Part.
     * */

    @FXML
    private TextField partInvText;

    /**
     * This is the fx:id of the text field for the inventory maximum of the new Part.
     * */

    @FXML
    private TextField partMaxText;

    /**
     * This is the fx:id of the text field for the inventory minimum of the new Part.
     * */

    @FXML
    private TextField partMinText;

    /**
     * This is the fx:id of the text field for the name of the new Part.
     * */

    @FXML
    private TextField partNameText;

    /**
     * This is the fx:id of the text field for the price of the new Part.
     * */

    @FXML
    private TextField partPriceText;

    /**
     * This method changes the label text to 'MachineID' when the inhouse button is selected.
     * @param event
     * */

    @FXML
    void onActionSetTypeInhouse(ActionEvent event) {
        partTypeAuxField.setText("Machine ID");
    }

    /**
     * This method changes the label text to 'Company' when the outsourced button is selected.
     * @param event
     * */

    @FXML
    void onActionSetTypeOutsourced(ActionEvent event) {
        partTypeAuxField.setText("Company");
    }

    /**
     * This method does the validation on the variables for viability and then feeds the data into the object
     * constructor to add to the allParts observableList data structure.
     * @param event
     * @throws IOException
     * */

    @FXML
    void onActionSavePart(ActionEvent event) throws IOException {
        try {
            int id = nextID;
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
                    Inventory.addPart(new InHouse(id, name, price, stock, min, max, machineID));
                    switchToScene(event, "/view/MainForm.fxml");

                } else if (outsourcedRadioBtn.isSelected()) {
                    String company = partAuxText.getText();
                    Inventory.addPart(new Outsourced(id, name, price, stock, min, max, company));
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
     * This method raises an alert to notify the user that any changes will be lost before returning to the main form.
     * @param event
     * @throws IOException
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
     * This method calls the size method of the allParts list object and adds the quantity to a base number to make unique sequential ID numbers.

     * */

    public static int generateNewID() {
        int startingID = 5001;
        int count = Inventory.getAllParts().size();
        return startingID + count;
    }

    /**
     * The initialize method feeds the nextID variable into the partIDText field so that the ID is set before the rest of the fields are added by the user.
     * @param url
     * @param resourceBundle
     * */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        partIDText.setText(String.valueOf(nextID));

    }
}
