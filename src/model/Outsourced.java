package model;

/**
 * The Outsourced class is the concrete subclass of the Part superclass.  It inherits all of its members from
 * the superclass, except for companyName and it's getter and setter.
 * @author Keith Fletcher
 */

public class Outsourced extends Part{

    /**
     * Declares companyName. This is the field that extends the abstract part class.
     * */

    private String companyName;

    /**
     * Constructor for the Outsourced class. Calls the constructor of the superclass, part, and adds companyName.
     * @param id The unique part id of the item. Generated automatically by the application.
     * @param name A descriptive name for the item.
     * @param price The price of the item.
     * @param stock The current inventory quantity of the item.
     * @param min The predetermined minimum stock level of the item.
     * @param max The predetermined maximum stock level of the item.
     * @param companyName The name of the company from whom the product is sourced.
     */


    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * The getter for the companyName field
     * @return companyName
     * */

    public String getCompanyName() {
        return companyName;
    }

    /**
     * The setter for the companyName field.  Ends up being unused in the current version of the program.
     * @param companyName
     * */

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
