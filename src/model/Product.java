package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
     * */

    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /** Getter for the ID field */

    public int getId() {
        return id;
    }

    /** Setter for the ID field */

    public void setId(int id) {
        this.id = id;
    }

    /** Getter for the Name field */

    public String getName() {
        return name;
    }

    /** Setter for the Name field */

    public void setName(String name) {
        this.name = name;
    }

    /** Getter for the Price field */

    public double getPrice() {
        return price;
    }

    /** Setter for the Price field */

    public void setPrice(double price) {
        this.price = price;
    }

    /** Getter for the Stock field */

    public int getStock() {
        return stock;
    }

    /** Setter for the Stock field */

    public void setStock(int stock) {
        this.stock = stock;
    }

    /** Getter for the Min field */

    public int getMin() {
        return min;
    }

    /** Setter for the Min field */

    public void setMin(int min) {
        this.min = min;
    }

    /** Getter for the Max field */

    public int getMax() {
        return max;
    }

    /** Setter for the Max field */

    public void setMax(int max) {
        this.max = max;
    }

    /** Getter for the associatedParts observableList */

    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

    /**
     * This method adds part objects to the associatedParts observableList.
     * */

    public void addAssociatedParts(ObservableList<Part> parts) {
        associatedParts.addAll(parts);
    }

    /**
     *  This method removes part objects to the associatedParts observableList.
     * */

    public static void deleteAssociatedPart(Part part) {
        associatedParts.remove(part);
    }
}
