package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static controller.SceneController.switchToScene;

public class MainFormController implements Initializable {

    private static Stage stage;

    @FXML
    private TableView<Part> partTableView;

    @FXML
    private TableColumn<Part, Integer> partIdColumn;

    @FXML
    private TableColumn<Part, Integer> partInvColumn;

    @FXML
    private TableColumn<Part, String> partNameColumn;

    @FXML
    private TableColumn<Part, Double> partPriceColumn;


    /**
     * This the tableview for products
     */

    @FXML
    private TableView<Product> productTableView;

    /**
     * This is the fx:id for the ID column of the product table.
     */


    @FXML
    private TableColumn<Product, Integer> productIDColumn;

    /**
     * This is the fx:id for the name column of the product table.
     */

    @FXML
    private TableColumn<Product, String> productNameCol;

    /**
     * This is the fx:id for the inventory level column of the product table.
     */

    @FXML
    private TableColumn<Product, Integer> productInvCol;

    /**
     * This is the fx:id for the price column of the product table.
     */

    @FXML
    private TableColumn<Product, Double> productPriceCol;



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

        try {
            Part selectedPart = partTableView.getSelectionModel().getSelectedItem();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifyPartForm.fxml"));
            loader.load();

            ModifyPartFormController MPFController = loader.getController();
            MPFController.sendPart(selectedPart);

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
        catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No Part Selected.");
            alert.show();
        }
    }

    @FXML
    void onActionDisplayModifyProductForm(ActionEvent event) throws IOException {

        try {
            Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifyProductForm.fxml"));
            loader.load();

            ModifyProductFormController ModProdController = loader.getController();
            ModProdController.sendProduct(selectedProduct);

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
        catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No Product Selected.");
            alert.show();
        }
    }



//        switchToScene(event,"/view/ModifyProductForm.fxml");






    @FXML
    void onActionExit(ActionEvent event) {

        System.exit(0);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        productTableView.setItems(Inventory.getAllProducts());

        productIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));


        partTableView.setItems(Inventory.getAllParts());
        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));




    }
}
