package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static controller.SceneController.switchToScene;

public class AddProductFormController implements Initializable {

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();


    @FXML
    private TextField productIDText;

    @FXML
    private TextField productInvText;

    @FXML
    private TextField productMaxText;

    @FXML
    private TextField productMinText;

    @FXML
    private TextField productNameText;

    @FXML
    private TextField productPriceText;

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
    private TableView<Part> associatedPartTableView;

    @FXML
    private TableColumn<Part, Integer> associatedPartIDColumn;

    @FXML
    private TableColumn<Part, Integer> associatedPartInvColumn;

    @FXML
    private TableColumn<Part, String> associatedPartNameColumn;

    @FXML
    private TableColumn<Part, Double> associatedPartPriceColumn;

    @FXML
    void onActionAddAssociatedPart(ActionEvent event) {

        Part selectedPartToAdd = allPartTableView.getSelectionModel().getSelectedItem();
        associatedParts.add(selectedPartToAdd);
        associatedPartTableView.setItems(associatedParts);


    }


    @FXML
    void onActionRemovePart(ActionEvent event) {

    }

    @FXML
    void onActionSaveProduct(ActionEvent event) throws IOException {
        try {
            int id = Integer.parseInt(productIDText.getText());
            String name = productNameText.getText();
            double price = Double.parseDouble(productPriceText.getText());
            int stock = Integer.parseInt(productInvText.getText());
            int max = Integer.parseInt(productMaxText.getText());
            int min = Integer.parseInt(productMinText.getText());

            if ( max < min) {
                Alert parsAlert = new Alert(Alert.AlertType.ERROR);
                parsAlert.setTitle("Invalid Inventory Pars");
                parsAlert.setContentText("Maximum must exceed minimum.");
                parsAlert.showAndWait();
                return;
            }
            else if ( stock > min && stock <= max ) {
                Product product = new Product(id, name, price, stock, min, max);
                product.addAssociatedParts(associatedParts);
                Inventory.addProduct(product);
                switchToScene(event, "/view/MainForm.fxml");
            }
            else {
                Alert invAlert = new Alert(Alert.AlertType.ERROR);
                invAlert.setTitle("Invalid Inventory Amount");
                invAlert.setContentText("The inventory quantify must be greater than the minimum and not exceed the maximum");
                invAlert.showAndWait();
                return;
            }
        }
        catch (NumberFormatException e) {

        }

    }

    @FXML
    void onActionDisplayMainForm(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This is clear any changes you've made. " +
                "Do you want to continue? ");
        Optional<ButtonType> result = alert.showAndWait();
        if ( result.isPresent() && result.get() == ButtonType.OK)
            switchToScene(event,  "/view/MainForm.fxml");

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        allPartTableView.setItems(Inventory.getAllParts());
        allPartIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        allPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        allPartInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        allPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        associatedPartTableView.setItems(associatedParts);
        associatedPartIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));


    }
}
