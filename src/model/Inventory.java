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
     * This method returns the ObservableList object.
     */


    public static ObservableList<Product> getAllProducts() {

        return allProducts;
    }

}
