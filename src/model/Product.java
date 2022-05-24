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

    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

    public void addAssociatedParts(ObservableList<Part> parts) {
        associatedParts.addAll(parts);
    }

    public static void deleteAssociatedPart(Part part) {
        associatedParts.remove(part);
    }
}
