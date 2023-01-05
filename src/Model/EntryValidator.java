package Model;

import javafx.collections.ObservableList;



/**
 * Entry Validator is a class that provides error checking for Integers passed into add and modify forms.
 */
public class EntryValidator {

    /**
     * @param min 
     * @param max
     * @return
     * Checks that the min variable pass is both less than the max variable and more than zero.
     * Provides and alert for invalid min from the alert class if the min Integer are invalid.
     * Additionally, checks that max is greater than zero as having a negative max stock makes no sense.
     */
    public static boolean isMinValid(int min, int max) {

        boolean isMinValid = true;

        if (max <= 0) {
            isMinValid = false;
            ProgramAlerts.invalidMaxAlert();
        }

        if (min <= 0 || min >= max) {
            isMinValid = false;
            ProgramAlerts.invalidMinAlert();
        }
        return true;
    }

    /**
     * @param min
     * @param max
     * @param inventory
     * @return
     * Checks that the inventory Integer passed is between the bounds of the min and max Integer variables.
     * Provides and alert for invalid inventory from the alert class if the inventory Integer invalid.
     */
    public static boolean isInventoryValid(int min, int max, int inventory) {

        boolean isInventoryValid = true;
        if (inventory < min || inventory > max) {
            isInventoryValid = false;
            ProgramAlerts.invalidInventoryAlert();
        }
        return isInventoryValid;
    }

    /**
     * @param price
     * @param relatedParts
     * @return
     * Checks that the total cost of parts used in a product is less than the cost of the product.
     * Uses total price variable to sum the cost of parts by iterating through the relatedParts ObservableList passed.
     */
    public static boolean isPriceValid(Double price, ObservableList<Part> relatedParts) {
        double totalPrice = 0;

        for (Part part: relatedParts) {
            totalPrice += part.getPrice();
        }

        if (totalPrice < price) {
            return true;
        }

        ProgramAlerts.productPriceLessThanRelatedParts();
        return false;
    }




}
