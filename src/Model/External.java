package Model;

public class External extends Part{

    private String companyName;

    /**
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     * @param companyName
     * Constructor for an External object. A subclass of Part allowing to pass a company name that the part was sourced from.
     */
    public External(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }
    /* Using the super keyword to create a constructor for the External subclass*/

    /**
     * @return
     * Returns an external objects companyName string.
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName
     * Sets the companyName String of an External object.
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }



}
