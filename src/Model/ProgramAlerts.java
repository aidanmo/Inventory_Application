package Model;

import com.sun.net.httpserver.Authenticator;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.io.IOException;
import java.util.Optional;

public class ProgramAlerts {

    private static Alert errorAlert = new Alert(Alert.AlertType.ERROR);
    private static Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);

    /**
     * Provides the end-user with an alert that the min value is invalid.
     */
    public static void invalidMinAlert() {
        errorAlert.setTitle("Error");
        errorAlert.setHeaderText("Invalid value for Min");
        errorAlert.setContentText("Min must be a number greater than 0 and less than Max.");
        errorAlert.showAndWait();
    }

    /**
     * Provides the end-user with an alert that the max value is invalid.
     */
    public static void invalidMaxAlert() {
        errorAlert.setTitle("Error");
        errorAlert.setHeaderText("Invalid value for Max");
        errorAlert.setContentText("Max must be a number greater than 0");
        errorAlert.showAndWait();
    }

    /**
     * Provides the end-user with an alert that the inventory value is invalid.
     */
    public static void invalidInventoryAlert() {
        errorAlert.setTitle("Error");
        errorAlert.setHeaderText("Invalid value for Inventory");
        errorAlert.setContentText("Inventory must be a number equal to or between Min and Max");
        errorAlert.showAndWait();
    }

    /**
     * Provides the end-user with an alert that the machineID value is invalid.
     */
    public static void invalidMachineIdAlert() {
        errorAlert.setTitle("Error");
        errorAlert.setHeaderText("Invalid value for Machine ID");
        errorAlert.setContentText("Machine ID may only contain numbers.");
        errorAlert.showAndWait();
    }

    /**
     * Provides the end-user with an alert that the companyName value is invalid.
     */
    public static void invalidCompanyNameAlert() {
        errorAlert.setTitle("Error");
        errorAlert.setHeaderText("Invalid value for Company Name");
        errorAlert.setContentText("Company name must have character letters.");
        errorAlert.showAndWait();
    }

    /**
     * Provides the end-user with an alert that there are blank fields on the current form.
     */
    public static void blankFieldsAlert() {
        errorAlert.setTitle("Error");
        errorAlert.setHeaderText("Invalid Entries");
        errorAlert.setContentText("Form contains blank fields or invalid values.");
        errorAlert.showAndWait();
    }

    /**
     * Provides the end-user with an alert that there is a null product selection when performing a manipulation.
     */
    public static void noProductSelected() {
        errorAlert.setTitle("Error");
        errorAlert.setHeaderText("No Product Selected");
        errorAlert.setContentText("A product must be selected to perform deletions and modifications");
        errorAlert.showAndWait();
        //This method is used for both the product deletion and modification buttons on the main menu
    }

    /**
     * Provides the end-user with an alert that there is a null part selection when performing a manipulation.
     */
    public static void noPartSelected() {
        errorAlert.setTitle("Error");
        errorAlert.setHeaderText("No Part Selected");
        errorAlert.setContentText("A part must be selected to perform deletions and modifications");
        errorAlert.showAndWait();
        //This method is used for both the part deletion and modification buttons on the main menu
    }

    /**
     * @param event
     * @throws IOException
     * Provides end-user with a confirmation pop up that they would like to forfeit their entries and return to the main menu.
     */
    public static void backToMainMenuConfirmationAdd(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will clear all text-field values do you want to continue?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            SceneSwitcher.goToMain(event);
        }
    }

    /**
     * @param event
     * @throws IOException
     * * Provides end-user with a confirmation pop up that they would like to forfeit their modifications and return to the main menu.
     */
    public static void backToMainMenuConfirmationModify(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "No modifications will be save do you want to continue?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            SceneSwitcher.goToMain(event);
        }
    }

    /**
     * @param event
     * @param selectedPart
     * Provides the end-user with a confirmation pop-up confirming they would like to delete the selected Part object.
     */
    public static void deletePartConfirmation(ActionEvent event,Part selectedPart) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setContentText("Do you want to delete the selected part?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            Inventory.deletePart(selectedPart);
        }
    }

    /**
     * @param Event
     * @param selectedPart
     * @param relatedParts
     * Provides the end-user with a confirmation pop-up confirming they would like to delete the selected part from the
     * relatedParts ObservableList.
     */
    public static void deleteRelatedPartConfirmation(ActionEvent Event, Part selectedPart, ObservableList<Part> relatedParts)  {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setContentText("Do you want to delete the selected part?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            relatedParts.remove(selectedPart);
        }
    }


    /**
     * @param event
     * @param selectedProduct
     * Provides the end-user with a confirmation pop-up confirming they would like to delete the selected Product object.
     */
    public static void deleteProductConfirmation(ActionEvent event, Product selectedProduct) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setContentText("Do you want to delete the selected product?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {

            ObservableList<Part> relatedParts = selectedProduct.getAllRelatedParts();

            if (relatedParts.size() >= 1) {
                relatedParts();
            } else {
                Inventory.deleteProduct(selectedProduct);
            }
        }
    }

    /**
     * Provides the end user with an alert that the Product name field is invalid.
     */
    public static void productNameIsEmpty() {
        errorAlert.setTitle("Error");
        errorAlert.setHeaderText("Name Empty");
        errorAlert.setContentText("Name cannot be empty.");
        errorAlert.showAndWait();
    }

    /**
     * Provides the end-user with an alert that the sum of relatedParts prices is greater than the Product price.
     */
    public static void productPriceLessThanRelatedParts() {
        errorAlert.setTitle("Error");
        errorAlert.setHeaderText("Invalid Product Price");
        errorAlert.setContentText("Product price must be greater than the total cost of the related parts.");
        errorAlert.showAndWait();
    }

    /**
     * Provides end user with a pop-up that the part was successfully added to the inventory.
     */
    public static void partAddSuccessful() {
        infoAlert.setTitle("Success");
        infoAlert.setHeaderText("Part Addition Successful");
        infoAlert.setContentText("The part was successfully added to the inventory.");
        infoAlert.showAndWait();
    }

    /**
     * Provides end user with a pop-up that the product was successfully added to the inventory.
     */
    public static void productAddSuccessful() {
        infoAlert.setTitle("Success");
        infoAlert.setHeaderText("Product Addition Successful");
        infoAlert.setContentText("The Product was successfully added to the inventory.");
        infoAlert.showAndWait();
    }

    /**
     * Provides end user with a pop-up that the part was successfully modified.
     */
    public static void partModificationSuccessful() {
        infoAlert.setTitle("Success");
        infoAlert.setHeaderText("Part Modification Successful");
        infoAlert.setContentText("The Part was successfully modified.");
        infoAlert.showAndWait();
    }

    /**
     * Provides end user with a pop-up that the product was successfully modified.
     */
    public static void productModificationSuccessful() {
        infoAlert.setTitle("Success");
        infoAlert.setHeaderText("Product modification Successful");
        infoAlert.setContentText("The Product was successfully modified.");
        infoAlert.showAndWait();
    }

    /**
     * Provides end user with a pop-up that warns them related parts must be removed from a product before deletion
     */
    public static void relatedParts() {
        errorAlert.setTitle("Error");
        errorAlert.setHeaderText("Related Parts");
        errorAlert.setContentText("All parts must be removed from a product before deletion.");
        errorAlert.showAndWait();
    }

    /**
     * Provides end user with an error pop-up that notifies them the part does not exist in the inventory.
     */
    public static void partNotFound() {
        errorAlert.setTitle("Error");
        errorAlert.setHeaderText("Part Not Found");
        errorAlert.showAndWait();
    }

    /**
     * Provides end user with an error pop-up that notifies them the product does not exist in the inventory.
     */
    public static void productNotFound() {
        errorAlert.setTitle("Error");
        errorAlert.setHeaderText("Product Not Found");
        errorAlert.showAndWait();
    }



















}
