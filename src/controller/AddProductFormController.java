package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Inventory;
import model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static controller.SceneController.switchToScene;

public class AddProductFormController implements Initializable {

    @FXML
    private TableView<Part> allPartTableView;

    @FXML
    private TableColumn<Part, Integer> allPartIDColumn;

    @FXML
    private TableColumn<Part, Integer> allPartInvColumn;

    @FXML
    private TableColumn<Part, String> allPartNameColumn;

    @FXML
    private TableColumn<Part, Double> allPartPriceColumn;


    @FXML
    private TableView<?> associatedPartTableView;

    @FXML
    private TableColumn<Part, Integer> associatedPartIDColumn;

    @FXML
    private TableColumn<Part, Integer> associatedPartInvColumn;

    @FXML
    private TableColumn<Part, String> associatedPartNameColumn;

    @FXML
    private TableColumn<Part, Double> associatedPartPriceColumn;

    @FXML
    void onActionRemovePart(ActionEvent event) {

    }

    @FXML
    void onActionDisplayMainForm(ActionEvent event) throws IOException {

        switchToScene(event,"/view/MainForm.fxml");

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        allPartTableView.setItems(Inventory.getAllParts());
        allPartIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        allPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        allPartInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        allPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));



    }
}
