package model;

/**
 * The InHouse class is the concrete subclass of the Part superclass.  It inherits all of its members from
 * the superclass, except for machineID and it's getter and setter.
 * @author Keith Fletcher
 */

public class InHouse extends Part{

    /**
     * Declares machineID. This is the field that extends the abstract part class.
     * */

    private int machineId;

    /**
     * Constructor for the Inhouse class. Calls the constructor of the superclass, part, and adds machineID.
     * @param id The unique part id of the item. Generated automatically by the application.
     * @param name A descriptive name for the item.
     * @param price The price of the item.
     * @param stock The current inventory quantity of the item.
     * @param min The predetermined minimum stock level of the item.
     * @param max The predetermined maximum stock level of the item.
     * @param machineId The id of the machine that the item was manufactured on.
     * */



    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * The getter for the machineID field
     * @return machineId The id of the machine that the item was manufactured on.
     * */

    public int getMachineId() {
        return machineId;
    }

    /**
     * The setter for the machineID field.  Ends up being unused in the current version of the program.
     * @param machineId of the machine that the item was manufactured on.
     * */

    public void setMachineId(int machineId) {

        this.machineId = machineId;
    }
}
