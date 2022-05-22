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
import model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static controller.SceneController.switchToScene;

public class ModifyPartFormController implements Initializable {

    private Part partToModify;

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
    private TextField partMixText;

    @FXML
    private Text partNameText;

    @FXML
    private TextField partPriceText;

    @FXML
    private Text partTypeAuxField;
    public Part selectedPart;
    private int partID;

    @FXML
    void onActionSetTypeInhouse(ActionEvent event) {

    }

    @FXML
    void onActionSetTypeOutsourced(ActionEvent event) {

    }

    @FXML
    void onActionDisplayMainForm(ActionEvent event) throws IOException {

        switchToScene(event,"/view/MainForm.fxml");
    }

    public void sendPart(Part selectedPart) {
        this.selectedPart = selectedPart;
        partID = Inventory.getAllParts().indexOf(selectedPart);
//        System.out.println(partID);

        partIDText.setText((Integer.toString(selectedPart.getId())));

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {





    }
}
