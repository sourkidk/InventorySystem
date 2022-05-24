package model;

public class InHouse extends Part{

    /**
     * Declares machineID. This is the field that extends the abstract part class.
     * */

    private int machineId;

    /**
     * Constructor for the Inhouse class. Calls the constructor of the superclass, part, and adds machineID.
     * */


    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * The getter for the machineID field
     * */

    public int getMachineId() {
        return machineId;
    }

    /**
     * The setter for the machineID field.  Ends up being unused in the current version of the program.
     * */

    public void setMachineId(int machineId) {

        this.machineId = machineId;
    }
}
