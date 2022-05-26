package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The inventory class hold the methods and objects that manage the Part and Product Objects.
 * @author Keith Fletcher
 */

public class Inventory {

    /**
     * Declares the Observable list of products.
     *
     */

    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * This lookupProduct method checks the given integer against the productIDs in the list and returns a single match or null.
     * @param productID
     * @return
     * */

    public static Product lookupProduct(int productID) {
        for (Product product : Inventory.getAllProducts() ) {
            if (product.getId() == productID )
                return product;
        }
        return null;
    }

    /**
     * This lookupPart method checks the given integer against the partIDs in the list and returns a single match or null.
     * @param partID
     * @return Part
     * */

    public static Part lookupPart(int partID) {
        for (Part part : Inventory.getAllParts()) {
            while (part.getId() == partID) {
                return part;
            }
        }
        return null;
    }

    /**
     * This lookupPart method checks the given string against the names of each object in the list and returns a new
     * observableList of Parts that match.
     * @param partName
     * @param ObservableList<Part>
     * */

    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> PartName = FXCollections.observableArrayList();

        for (Part part: allParts) {
            if (part.getName().contains(partName)) {
                PartName.add(part);
            }
        }
        return PartName;
    }

    /**
     * This lookupProduct method checks the given string against the names of each object in the list and returns a new
     * observableList of Products that match.
     * @param productName
     * @param ObservableList<Product>
     * */

    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> ProductName = FXCollections.observableArrayList();

        for (Product product: allProducts) {
            if (product.getName().contains(productName)) {
                ProductName.add(product);
            }
        }
        return ProductName;
    }

    /**
     * This method adds a Product object to the allProducts Observablelist
     * @param product This is a Product object.
     */

    public static void addProduct(Product product) {

        allProducts.add(product);
    }
    /**
     * This method returns the ObservableList object allProducts.
     * @return allProducts
     */


    public static ObservableList<Product> getAllProducts() {

        return allProducts;
    }

    /**
     * Initializes the Observable list of Parts.
     *
     */

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    /**
     * This method adds a Part object to the allParts Observablelist
     * @param part
     */

    public static void addPart(Part part) {

        allParts.add(part);
    }
    /**
     * This method returns the ObservableList object allParts.
     * @return allParts
     */


    public static ObservableList<Part> getAllParts() {

        return allParts;
    }

    /**
     * This method removes the selectedProduct from the allProducts list
     * @param selectedProduct
     * @return boolean
     */

    public static boolean deleteProduct(Product selectedProduct) {

        if(allProducts.contains(selectedProduct)) {
            allProducts.remove(selectedProduct);
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method removes the selectedPart from the allParts list
     * @param selectedPart
     * @return boolean
     */

    public static boolean deletePart(Part selectedPart) {
        if (allParts.contains(selectedPart)) {
            allParts.remove(selectedPart);
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method replaces the product with a specific id with newProduct which is the modified version.
     * @ param id
     * @ param newProduct
     */

    public static void updateProduct(int id, Product newProduct){
        int index = -1;

        for(Product product: Inventory.getAllProducts()) {
            index++;

            if (product.getId() == id) {
                Inventory.getAllProducts().set(index, newProduct);
            }
        }
    }

    /**
     * This method replaces the part with a specific id with newPart which is the modified version.
     * @param id
     * @param newPart
     */

    public static void updatePart(int id, Part newPart ) {
        int index = -1;

        for(Part part: Inventory.getAllParts()) {
            index++;

            if (part.getId() == id) {
                Inventory.getAllParts().set(index, newPart);
            }
        }
    }
}
