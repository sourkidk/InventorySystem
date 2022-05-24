package model;

public abstract class Part {

    /** Declares the id field for the part object */

    private int id;

    /** Declares the name field for the part object */

    private String name;

    /** Declares the price field for the part object */

    private double price;

    /** Declares the stock(inventory quantity) field for the part object */

    private int stock;

    /** Declares the minimum field for the part object */

    private int min;

    /** Declares the maximum field for the part object */

    private int max;

    /**
     * The constructor for the abstract Part class.
     * */

    public Part(int id, String name, double price, int stock, int min, int max) {
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
}
