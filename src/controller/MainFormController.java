package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Inventory;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static controller.SceneController.switchToScene;

public class MainFormController implements Initializable {

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

        productTableView.setItems(Inventory.getAllProducts());

        productIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));


    }
}
