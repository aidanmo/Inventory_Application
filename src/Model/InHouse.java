package Model;

public class InHouse extends Part{

    private int machineId;

    /**
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     * @param machineId
     * Constructor for an InHouse object. A subclass of Part allowing to pass a machine ID that created the part.
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /* Using the super keyword to create a constructor for the External subclass*/

    /**
     * @return
     * Returns the machine ID Integer associated with a InHouse object.
     */
    public int getMachineId() {
        return machineId;
    }

    /**
     * @param machineId
     * Sets the machine ID Integer associated with a InHouse object.
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
