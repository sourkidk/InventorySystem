package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import model.InHouse;
import model.Inventory;
import model.Outsourced;

import java.io.IOException;
import java.net.URL;
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

            if( inhouseRadioBtn.isSelected() ) {
                int machineID = Integer.parseInt(partAuxText.getText());
                Inventory.addPart(new InHouse(id, name, price, stock, max, min, machineID));
                switchToScene(event, "/view/MainForm.fxml");
            }
            else if ( outsourcedRadioBtn.isSelected() ) {
                String company = partAuxText.getText();
                Inventory.addPart(new Outsourced(id, name, price, stock, max, min, company ));
                switchToScene(event, "/view/MainForm.fxml");
            }



        } catch(NumberFormatException e) {

        }
    }

    /***/
    @FXML
    void onActionDisplayMainForm(ActionEvent event) throws IOException {

        switchToScene(event,"/view/MainForm.fxml");

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
