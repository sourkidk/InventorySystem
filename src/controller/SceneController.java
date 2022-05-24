package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class SceneController {

    private static Stage stage;
    private static Scene scene;


    /**
     *  This class and method were made to reduce code reuse in switching Scenes.  The original routine
     *  taught in the webinar is viewable in the MainMenuController onActionCreateAnimal event handler.
     */
    public static void switchToScene(ActionEvent event, String sceneFile) throws IOException {
        Parent root = FXMLLoader.load(SceneController.class.getResource(sceneFile));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}