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

    private Part partToModify;

    @FXML private ToggleGroup AddPartToggleGroup;
    @FXML private RadioButton inhouseRadioBtn;

    @FXML private RadioButton outsourcedRadioBtn;

    @FXML private TextField partAuxText;

    @FXML private TextField partIDText;

    @FXML private TextField partInvText;

    @FXML private TextField partMaxText;

    @FXML private TextField partMinText;

    @FXML private TextField partNameText;

    @FXML private TextField partPriceText;

    @FXML private Text partTypeAuxField;
    public Part selectedPart;
    private int partID;

    @FXML
    void onActionSetTypeInhouse(ActionEvent event) {
        partTypeAuxField.setText("Machine ID");
    }

    @FXML
    void onActionSetTypeOutsourced(ActionEvent event) {
        partTypeAuxField.setText("Company");
    }

    @FXML
    void onActionDisplayMainForm(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This is clear any changes you've made. " +
                "Do you want to continue? ");
        Optional<ButtonType> result = alert.showAndWait();
        if ( result.isPresent() && result.get() == ButtonType.OK)
            switchToScene(event,"/view/MainForm.fxml");
    }

    public void sendPart(Part selectedPart) {
        this.selectedPart = selectedPart;

//        partID = Inventory.getAllParts().indexOf(selectedPart);

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {





    }
}
