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

public class AddPartFormController implements Initializable {

    @FXML
    private Text partTypeAuxField;

    /**
     * Toggle group for In-House/Outsourced radio buttons
     *
     */


    @FXML
    private ToggleGroup AddPartToggleGroup;

    @FXML
    private RadioButton inhouseRadioBtn;

    @FXML
    private RadioButton outsourcedRadioBtn;

    @FXML
    private TextField partAuxText;

    @FXML
    private TextField partIDText;

    @FXML
    private TextField partInvText;

    @FXML
    private TextField partMaxText;

    @FXML
    private TextField partMinText;

    @FXML
    private TextField partNameText;

    @FXML
    private TextField partPriceText;

    @FXML
    void onActionSetTypeInhouse(ActionEvent event) {
        partTypeAuxField.setText("Machine ID");

    }

    @FXML
    void onActionSetTypeOutsourced(ActionEvent event) {

        partTypeAuxField.setText("Company");
    }


    @FXML
    void onActionSavePart(ActionEvent event) throws IOException {
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
                    if (machineID != 0) {
                        Inventory.addPart(new InHouse(id, name, price, stock, min, max, machineID));
                        switchToScene(event, "/view/MainForm.fxml");
                    } else
                        System.out.println("Problem");
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

    /***/
    @FXML
    void onActionDisplayMainForm(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This is clear any changes you've made. " +
                "Do you want to continue? ");
        Optional<ButtonType> result = alert.showAndWait();
        if ( result.isPresent() && result.get() == ButtonType.OK)
            switchToScene(event,"/view/MainForm.fxml");

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
