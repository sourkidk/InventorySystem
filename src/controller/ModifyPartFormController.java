package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;

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
    void onActionSetTypeInhouse(ActionEvent event) {}

    @FXML
    void onActionSetTypeOutsourced(ActionEvent event) {}

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {





    }
}
