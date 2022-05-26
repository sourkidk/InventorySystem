package model;

/**
 * This is the abstract class that is the model for InHouse and Outsources part objects.
 * @author Keith Fletcher
 */

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
     * @param id The unique part id of the item. Generated automatically by the application.
     * @param name A descriptive name for the item.
     * @param price The price of the item.
     * @param stock The current inventory quantity of the item.
     * @param min The predetermined minimum stock level of the item.
     * @param max The predetermined maximum stock level of the item.
     */


    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /** Getter for the ID field
     * @return id
     */

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
     */

    public int getMax() {
        return max;
    }

    /** Setter for the Max field
     * @param max
     * */

    public void setMax(int max) {
        this.max = max;
    }
}
