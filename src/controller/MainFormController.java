package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import static controller.SceneController.switchToScene;

public class MainFormController implements Initializable {

    /**
     * This is the stage object that the scene is built on
     * */

    private static Stage stage;

    /**
     * This is the table containing the full list of Parts.
     * */

    @FXML
    private TableView<Part> partTableView;

    /**
     * This column contains the ID for each part object in the list.
     * */

    @FXML
    private TableColumn<Part, Integer> partIdColumn;

    /**
     * This column contains the inventory quantity for each part object in the list.
     * */

    @FXML
    private TableColumn<Part, Integer> partInvColumn;

    /**
     * This column contains the name for each part object in the list.
     * */

    @FXML
    private TableColumn<Part, String> partNameColumn;

    /**
     * This column contains the price for each part object in the list.
     * */

    @FXML
    private TableColumn<Part, Double> partPriceColumn;


    /**
     * This the tableview for products
     */

    @FXML
    private TableView<Product> productTableView;

    /**
     * This is the ID column of the product table.
     */


    @FXML
    private TableColumn<Product, Integer> productIDColumn;

    /**
     * This is the name column of the product table.
     */

    @FXML
    private TableColumn<Product, String> productNameCol;

    /**
     * This is the inventory level column of the product table.
     */

    @FXML
    private TableColumn<Product, Integer> productInvCol;

    /**
     * This is the price column of the product table.
     */

    @FXML
    private TableColumn<Product, Double> productPriceCol;

    /**
     * This event handler switches to the AddParts scene.
     * */

    @FXML
    void onActionDisplayAddPartForm(ActionEvent event) throws IOException {

        switchToScene(event, "/view/AddPartForm.fxml");
    }

    /**
     * This event handler switches to the AddProducts scene.
     * */

    @FXML
    void onActionDisplayAddProductForm(ActionEvent event) throws IOException {

        switchToScene(event, "/view/AddProductForm.fxml");
    }

    /**
     * RUNTIME ERROR: The try-catch clause prevents the program from crashing due to NullPointerException.  By default
     * no part is selected in the main form, so it's common to try to switch to that scene without first selecting an object.
     *
     * This method uses the sendPart method of the modifyPart class to send data from the selected part to the
     * modifyParts form and switches the scene.
     * */

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

    /**
     * This method uses the sendProduct method of the modifyProduct class to send data from the selected product to the
     * modifyProducts form and switches the scene.
     * */

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

    /**
     * This method removes the selected part permanently from the core observableList data structure
     * */

    @FXML
    void onActionDeletePart(ActionEvent event) {

        Part selectedPart = partTableView.getSelectionModel().getSelectedItem();

        Alert confirmDelete = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove this part?");
        Optional<ButtonType> result = confirmDelete.showAndWait();

        if ( result.isPresent() && result.get() == ButtonType.OK )

            Inventory.deletePart(selectedPart);
    }

    /**
     * This method removes the selected product permanently from the core observableList data structure
     * */

    @FXML
    void onActionDeleteProduct(ActionEvent event) {

        Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();

        Alert confirmDelete = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove this product?");
        Optional<ButtonType> result = confirmDelete.showAndWait();

        if ( result.isPresent() && result.get() == ButtonType.OK ) {

            if (selectedProduct.getAllAssociatedParts().size() > 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR MESSAGE!");
                alert.setContentText("You must remove all associated parts before you delete the product.");
                alert.showAndWait();
                return;

            } else
                Inventory.deleteProduct(selectedProduct);
        }
    }

    /**
     * This method does a system call to exit the program
     * */

    @FXML
    void onActionExit(ActionEvent event) {
        System.exit(0);
    }

    /**
     * When the Main form is loaded, the allParts and allProducts lists are loaded into the table views.
     * */

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
