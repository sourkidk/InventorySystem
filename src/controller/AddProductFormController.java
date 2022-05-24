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

    /**
     * Calls the generateNewID method to get the next ID in the sequence
     * */

    int nextID = generateNewID();

    /**
     * The associatedParts observablelist is used by this class to hold part items temporarily for manipulation.
     * */

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     * This is the text field for the ID of the new Part.  This field should not be editable to the user.
     * */

    @FXML
    private TextField productIDText;

    /**
     * This is the text field for the inventory quantity of the new Product.
     * */

    @FXML
    private TextField productInvText;

    /**
     * This is the text field for the inventory quantity of the new Product.
     * */

    @FXML
    private TextField productMaxText;

    /**
     * This is the text field for the inventory quantity of the new Product.
     * */

    @FXML
    private TextField productMinText;

    /**
     * This is the text field for the inventory quantity of the new Product.
     * */

    @FXML
    private TextField productNameText;

    /**
     * This is the text field for the inventory quantity of the new Product.
     * */

    @FXML
    private TextField productPriceText;

    /**
     * This is the table containing the full parts list.
     * */

    @FXML
    private TableView<Part> allPartTableView;

    /**
     * This is the column containing the ID of each item in the full parts list.
     * */

    @FXML
    private TableColumn<Part, Integer> allPartIDColumn;

    /**
     * This is the column containing the inventory quantity of each item in the full parts list.
     * */

    @FXML
    private TableColumn<Part, Integer> allPartInvColumn;

    /**
     * This is the column containing the name of each item in the full parts list.
     * */

    @FXML
    private TableColumn<Part, String> allPartNameColumn;

    /**
     * This is the column containing the price of each item in the full parts list.
     * */

    @FXML
    private TableColumn<Part, Double> allPartPriceColumn;

    /**
     * This is the table containing the associated parts list for the new item.
     * */

    @FXML
    private TableView<Part> associatedPartTableView;

    /**
     * This is the column containing the ID of each item in the associated parts list
     * for the new item.
     * */

    @FXML
    private TableColumn<Part, Integer> associatedPartIDColumn;

    /**
     * This is the column containing the inventory quantity of each item in the associated parts list
     * for the new item.
     * */

    @FXML
    private TableColumn<Part, Integer> associatedPartInvColumn;

    /**
     * This is the column containing the name of each item in the associated parts list
     * for the new item.
     * */

    @FXML
    private TableColumn<Part, String> associatedPartNameColumn;

    /**
     * This is the column containing the price of each item in the associated parts list
     * for the new item.
     * */

    @FXML
    private TableColumn<Part, Double> associatedPartPriceColumn;

    /**
     * This method adds the selectedPart to the associatedparts list.
     * */

    @FXML
    void onActionAddAssociatedPart(ActionEvent event) {

        Part selectedPart = allPartTableView.getSelectionModel().getSelectedItem();
        associatedParts.add(selectedPart);
        associatedPartTableView.setItems(associatedParts);
    }

    /**
     *
     * */


    @FXML
    void onActionRemovePart(ActionEvent event) {

        Part selectedPart = allPartTableView.getSelectionModel().getSelectedItem();
        associatedParts.remove(selectedPart);
        associatedPartTableView.setItems(associatedParts);
    }

    /**
     * This method does the validation on the variables for viability and then feeds the data into the object
     * constructor to add to the allProducts observableList data structure.
     * */

    @FXML
    void onActionSaveProduct(ActionEvent event) throws IOException {
        try {
            int id = nextID;
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

    /**
     * This method raises an alert to notify the user that any changes will be lost before returning to the main form.
     * */

    @FXML
    void onActionDisplayMainForm(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This is clear any changes you've made. " +
                "Do you want to continue? ");
        Optional<ButtonType> result = alert.showAndWait();
        if ( result.isPresent() && result.get() == ButtonType.OK)
            switchToScene(event,  "/view/MainForm.fxml");
    }

    /**
     * This method calls the size method of the allProducts list object and adds the quantity to a base number to make unique sequential ID numbers
     * */

    public static int generateNewID() {
        int startingID = 1001;
        int count = Inventory.getAllProducts().size();
        return startingID + count;
    }

    /**
     * When the addProducts form is loaded, the allParts and associatedParts lists are loaded into the table views.
     * */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        productIDText.setText(String.valueOf(nextID));

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
