package controller;

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

public class ModifyProductFormController implements Initializable {

    @FXML
    private TableColumn<Part, Integer > allPartsIDColumn;

    @FXML
    private TableColumn<Part,Integer > allPartsInvColumn;

    @FXML
    private TableColumn<Part, Double> allPartsNameColumn;

    @FXML
    private TableColumn<Part, Double> allPartsPriceColumn;

    @FXML
    private TableView<Part> allPartsTableView;

    @FXML
    private TableColumn<Part,Integer > associatedPartsIDColunn;

    @FXML
    private TableColumn<Part, Integer> associatedPartsInvColumn;

    @FXML
    private TableColumn<Part, String> associatedPartsNameColumn;

    @FXML
    private TableColumn<Part, Double> associatedPartsPriceColumn;

    @FXML
    private TableView<Part> associatedPartsTableView;

    @FXML private TextField productIDText;

    @FXML private TextField productInvText;

    @FXML private TextField productMaxText;

    @FXML private TextField productMinText;

    @FXML private TextField productNameText;

    @FXML private TextField productPriceText;

    public Product selectedProduct;

    @FXML
    void onActionDisplayMainForm(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This is clear any changes you've made. " +
                "Do you want to continue? ");
        Optional<ButtonType> result = alert.showAndWait();
        if ( result.isPresent() && result.get() == ButtonType.OK)
            switchToScene(event,  "/view/MainForm.fxml");

    }

    public void sendProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;

        productIDText.setText((String.valueOf(selectedProduct.getId())));
        productNameText.setText(selectedProduct.getName());
        productInvText.setText(String.valueOf(selectedProduct.getStock()));
        productPriceText.setText(String.valueOf(selectedProduct.getPrice()));
        productMaxText.setText(String.valueOf(selectedProduct.getMax()));
        productMinText.setText(String.valueOf(selectedProduct.getMin()));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        allPartsTableView.setItems(Inventory.getAllParts());
        allPartsIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        allPartsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        allPartsInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        allPartsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));


    }


}
