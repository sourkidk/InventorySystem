package model;

public class Outsourced extends Part{

    /**
     * Declares companyName. This is the field that extends the abstract part class.
     * */

    private String companyName;

    /**
     * Constructor for the Outsourced class. Calls the constructor of the superclass, part, and adds companyName.
     * */

    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * The getter for the companyName field
     * */

    public String getCompanyName() {
        return companyName;
    }

    /**
     * The setter for the companyName field.  Ends up being unused in the current version of the program.
     * */

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
