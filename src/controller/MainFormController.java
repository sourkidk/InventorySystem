package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static controller.SceneController.switchToScene;

public class MainFormController implements Initializable {


    @FXML
    void onActionDisplayAddPartForm(ActionEvent event) throws IOException {

        switchToScene(event, "/view/AddPartForm.fxml");

    }

    @FXML
    void onActionDisplayAddProductForm(ActionEvent event) throws IOException {

        switchToScene(event, "/view/AddProductForm.fxml");

    }

    @FXML
    void onActionDisplayModifyPartForm(ActionEvent event) throws IOException {

        switchToScene(event,"/view/ModifyPartForm.fxml");

    }

    @FXML
    void onActionDisplayModifyProductForm(ActionEvent event) throws IOException {

        switchToScene(event,"/view/ModifyProductForm.fxml");




    }

    @FXML
    void onActionExit(ActionEvent event) {

        System.exit(0);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
