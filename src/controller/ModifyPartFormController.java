package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static controller.SceneController.switchToScene;

public class ModifyPartFormController implements Initializable {

    @FXML
    private ToggleGroup AddPartToggleGroup;

    @FXML
    void onActionDisplayMainForm(ActionEvent event) throws IOException {

        switchToScene(event,"/view/MainForm.fxml");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
