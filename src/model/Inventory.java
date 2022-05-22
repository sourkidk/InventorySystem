package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    /**
     * Initializes the Observable list of products.
     *
     */

    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * This method adds a Product object to the allProducts Observablelist
     * @param product This is a Product object.
     */

    public static void addProduct(Product product) {

        allProducts.add(product);
    }
    /**
     * This method returns the ObservableList object allProducts.
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
     * @param part This is a Part object.
     */

    public static void addPart(Part part) {

        allParts.add(part);
    }
    /**
     * This method returns the ObservableList object allParts.
     */


    public static ObservableList<Part> getAllParts() {

        return allParts;
    }

    public static boolean deleteProduct(Product selectedProduct) {

        if(allProducts.contains(selectedProduct)) {
            allProducts.remove(selectedProduct);
            return true;
        } else {
            return false;
        }

    }

    public static boolean deletePart(Part selectedPart) {
        if (allParts.contains(selectedPart)) {
            allParts.remove(selectedPart);
            return true;
        } else {
            return false;
        }
    }
}
