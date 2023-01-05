package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {
    private int id;

    private String name;

    private double price;

    private int stock;

    private int min;

    private int max;

    private ObservableList<Part> relatedParts = FXCollections.observableArrayList();

    /**
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     * Constructor that creates a Product object.
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * @return
     * Returns Integer id of a Product object.
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     * Sets id of a Product object.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return
     * Returns String name of a Product object.
     */
    public String getName() {return name;}

    /**
     * @param name
     * Sets name of Product object.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return
     * Returns Double price of a Product object.
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price
     * Sets price of a Product object.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return
     * Returns Integer stock of a Product object.
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock
     * Sets stock of a Product object.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return
     * Returns Integer min of a Product object.
     */
    public int getMin() {
        return min;
    }

    /**
     * @param min
     * Sets min of a Product object.
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @return
     * Returns Integer max of a Product object.
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max
     * Sets max of Product object.
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * @param part
     * Adds part to relatedParts ObservableList associated with a Product object.
     */
    public void addRelatedPart(Part part) {
        relatedParts.add(part);
        //Adds Part object passed as an argument to the relatedParts Array List.
    }

    /**
     * @param part
     * @return
     * Returns a boolean based on whether the selected Part exists in the relatedParts ObservableList
     * If the part exists, then it is removed from the relatedParts ObservableList.
     */
    public boolean removeRelatedPart(Part part) {
        if (relatedParts.contains(part)) {
            //checks if the Part object passed in the method arguments is present in the Observable Array List.
            relatedParts.remove(part);
            //If Part object is found. It is then subsequently deleted from the Observable ArrayList
            return true;
        } else {
            return false;
            //Returns false and does nothing if Part object is not present in the Observable Array List
        }
    }

    /**
     * @return
     * Returns the ObservableList of relatedParts associated with a given Product object.
     */
    public ObservableList getAllRelatedParts() {
        return relatedParts;
    }
    //returns all parts contained in the relatedParts Observable Array List
}
