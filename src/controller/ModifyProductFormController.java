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

/**
 * The ModifyProductFormController class is the complement to the ModifyPartForm view.  Contains the methods required to manipulate
 * data into and out of the ModifyPart scene.
 * @author Keith Fletcher
 */

public class ModifyProductFormController implements Initializable {

    /**
     * The selected product object variable is used in several context to send or receive data from the main form controller.
     * */

    public Product selectedProduct;

    /**
     * The associatedParts observablelist is used by this class to hold part items temporarily for manipulation.
     * */

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     * This is the table containing the full parts list.
     * */

    @FXML
    private TableView<Part> allPartsTableView;

    /**
     * This is the column containing the ID of each item in the full parts list.
     * */

    @FXML
    private TableColumn<Part, Integer > allPartsIDColumn;

    /**
     * This is the column containing the inventory quantity of each item in the full parts list.
     * */

    @FXML
    private TableColumn<Part,Integer > allPartsInvColumn;

    /**
     * This is the column containing the name of each item in the full parts list.
     * */

    @FXML
    private TableColumn<Part, Double> allPartsNameColumn;

    /**
     * This is the column containing the price of each item in the full parts list.
     * */

    @FXML
    private TableColumn<Part, Double> allPartsPriceColumn;

    /**
     * This is the table containing the associated parts list for the current item.
     * */

    @FXML
    private TableView<Part> associatedPartsTableView;

    /**
     * This is the column containing the ID of each item in the associated parts list
     * for the current item.
     * */

    @FXML
    private TableColumn<Part,Integer > associatedPartsIDColunn;

    /**
     * This is the column containing the inventory quantity of each item in the associated parts list for the current item.
     * */

    @FXML
    private TableColumn<Part, Integer> associatedPartsInvColumn;

    /**
     * This is the column containing the name of each item in the associated parts list for the current item.
     * */

    @FXML
    private TableColumn<Part, String> associatedPartsNameColumn;

    /**
     * This is the column containing the price of each item in the associated parts list for the current item.
     * */

    @FXML
    private TableColumn<Part, Double> associatedPartsPriceColumn;


    /**
     * This is the text field for the ID of the current Part.  This field should not be editable to the user.
     * */

    @FXML private TextField productIDText;

    /**
     * This is the text field for the inventory quantity of the current Part.
     * */

    @FXML private TextField productInvText;

    /**
     * This is the text field for the inventory maximum of the current Part.
     * */

    @FXML private TextField productMaxText;

    /**
     * This is the text field for the inventory minimum of the current Part.
     * */

    @FXML private TextField productMinText;

    /**
     * This is the text field for the name of the current Part.
     * */

    @FXML private TextField productNameText;

    @FXML
    private TextField partSearchText;

    /**
     * This is the text field for the price of the current Part.
     * */

    @FXML private TextField productPriceText;

    /**
     * This method adds the selectedPart to the associatedParts list.
     * @param event
     * */

    @FXML
    void onActionAddAssociatedPart(ActionEvent event) {
        Part selectedPartToAdd = allPartsTableView.getSelectionModel().getSelectedItem();
        associatedParts.add(selectedPartToAdd);
        associatedPartsTableView.setItems(associatedParts);
    }

    /**
     * This method raises an alert to notify the user that any changes will be lost before returning to the main form.
     * @param event
     * @throws IOException
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
     * This method removes the selectPart from the associatedParts list.
     * @param event
     * */

    @FXML
    void onActionRemovePart(ActionEvent event) {

        Part selectedPartToDelete = associatedPartsTableView.getSelectionModel().getSelectedItem();
        Product.deleteAssociatedPart(selectedPartToDelete);
        associatedParts.remove(selectedPartToDelete);
        associatedPartsTableView.setItems(associatedParts);

    }

    /**
     * This method does the validation on the variables for viability and then feeds the data into the object
     * constructor to update the core observableList data structure.
     * @param event
     * @throws IOException
     * */

    @FXML
    void onActionSave(ActionEvent event) throws IOException {
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
                Alert saveAlert = new Alert(Alert.AlertType.CONFIRMATION);
                saveAlert.setTitle("Save Changes");
                saveAlert.setContentText("Saving changes will overwrite previous data. Confirm Save?");
                Optional<ButtonType> result = saveAlert.showAndWait();
                if ( result.isPresent() && result.get() == ButtonType.OK) {
                    Product newProduct = new Product(id, name, price, stock, min, max);
                    newProduct.addAssociatedParts(associatedParts);
                    Inventory.updateProduct(id, newProduct);
                    switchToScene(event, "/view/MainForm.fxml");
                }

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
     * The sendProduct method is used by the main form to send Product data to the modify product form.
     * @param selectedProduct
     * */

    public void sendProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;

        productIDText.setText((String.valueOf(selectedProduct.getId())));
        productNameText.setText(selectedProduct.getName());
        productInvText.setText(String.valueOf(selectedProduct.getStock()));
        productPriceText.setText(String.valueOf(selectedProduct.getPrice()));
        productMaxText.setText(String.valueOf(selectedProduct.getMax()));
        productMinText.setText(String.valueOf(selectedProduct.getMin()));

        associatedParts.addAll(selectedProduct.getAllAssociatedParts());

    }
    /**
     * This method uses both forms of the lookupPart method to check the searchText against a Parts' name and ID. It checks first
     * for parts using the method that can return multiple objects then if that is still empty, it will check using the method that
     * checks against ID and returns a single part.
     * @param event
     * */

    @FXML
    void onActionPartSearch(ActionEvent event) {
        String searchText = partSearchText.getText();
        ObservableList<Part> tempList = Inventory.lookupPart(searchText);
        if (tempList.size() != 0 ) {
            allPartsTableView.setItems(tempList);
        }
        else {
            try {
                int partID = Integer.parseInt(searchText);
                tempList.add(Inventory.lookupPart(partID));
                allPartsTableView.setItems(tempList);
            } catch (NumberFormatException e) {
                Alert notFound = new Alert(Alert.AlertType.ERROR);
                notFound.setTitle("Part Not Found");
                notFound.setContentText("No Part with that search term");
                notFound.showAndWait();
            }
        }
    }

    /**
     * When the modify products form is loaded, the allParts and associatedParts lists are loaded into the table views.
     * @param url
     * @param resourceBundle
     * */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        allPartsTableView.setItems(Inventory.getAllParts());
        allPartsIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        allPartsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        allPartsInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        allPartsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        associatedPartsTableView.setItems(associatedParts);
        associatedPartsIDColunn.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartsInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}
