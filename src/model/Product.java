package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This is the concrete class for the Product object.  The objects are instantiated directly from this class.
 * @author Keith Fletcher
 */

public class Product {
    /** Declares the associated parts Observable List for the product object */

    private static ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /** Declares the ID field for the product object */

    private int id;

    /** Declares the Name field for the product object */

    private String name;

    /** Declares the Price field for the product object */

    private double price;

    /** Declares the Stock field for the product object */

    private int stock;

    /** Declares the Min field for the product object */

    private int min;

    /** Declares the Max field for the product object */

    private int max;

    /**
     * The constructor for the concrete Product class.
     * @param id The unique product id of the item. Generated automatically by the application.
     * @param name A descriptive name for the item.
     * @param price The price of the item.
     * @param stock The current inventory quantity of the item.
     * @param min The predetermined minimum stock level of the item.
     * @param max The predetermined maximum stock level of the item.
     */


    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /** Getter for the ID field
     * @return id
     * */

    public int getId() {
        return id;
    }

    /** Setter for the ID field
     * @param id
     * */

    public void setId(int id) {
        this.id = id;
    }

    /** Getter for the Name field
     * @return name
     * */

    public String getName() {
        return name;
    }

    /** Setter for the Name field
     * @param name
     * */

    public void setName(String name) {
        this.name = name;
    }

    /** Getter for the Price field
     * @return price
     * */

    public double getPrice() {
        return price;
    }

    /** Setter for the Price field
     * @param price
     * */

    public void setPrice(double price) {
        this.price = price;
    }

    /** Getter for the Stock field
     * @return stock
     * */

    public int getStock() {
        return stock;
    }

    /** Setter for the Stock field
     * @param stock
     * */

    public void setStock(int stock) {
        this.stock = stock;
    }

    /** Getter for the Min field
     * @return min
     * */

    public int getMin() {
        return min;
    }

    /** Setter for the Min field
     * @param min
     * */

    public void setMin(int min) {
        this.min = min;
    }

    /** Getter for the Max field
     * @return max
     * */

    public int getMax() {
        return max;
    }

    /** Setter for the Max field
     * @param max
     * */

    public void setMax(int max) {
        this.max = max;
    }

    /** Getter for the associatedParts observableList
     * @return associatedParts
     * */

    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

    /**
     * This method adds part objects to the associatedParts observableList.
     * @param parts
     * */

    public void addAssociatedParts(ObservableList<Part> parts) {
        associatedParts.addAll(parts);
    }

    /**
     *  This method removes part objects to the associatedParts observableList.
     * @param part
     * */

    public static void deleteAssociatedPart(Part part) {
        associatedParts.remove(part);
    }
}
