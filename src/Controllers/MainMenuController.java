package Controllers;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * MainMenuController class controls MainMenu.fxml
 */
public class MainMenuController implements Initializable {


    public TableView<Part> allPartsTableView;
    public TableColumn<Part, Integer> mainPartIdCol;
    public TableColumn<Part, String> mainPartNameCol;
    public TableColumn<Part, Integer> mainPartInventoryCol;
    public TableColumn<Part, Double> mainPartPriceCol;
    public TableView<Product> allProductsTableView;
    public TableColumn<Product, Integer> mainProductIdCol;
    public TableColumn<Product, String> mainProductNameCol;
    public TableColumn<Product, Integer> mainProductInventoryCol;
    public TableColumn<Product, Double> mainProductPriceCol;
    public TextField mainProductSearchField;
    public TextField mainPartSearchField;

    private static Part selectedPart;
    private static Product selectedProduct;


    /**
     * @return
     * returns selectedPart, which is a variable that holds the selected part from the table view.
     * Called from ModifyPartController to populate text fields with its specifications.
     */
    public static Part getSelectedPart() {
        return selectedPart;
    }

    /**
     * @return
     * returns selectedProduct, which is a variable that holds the selected product from the table view.
     * Called from modifyProductController to populate text fields with its specifications.
     */
    public static Product getSelectedProduct() {
        return selectedProduct;
    }

    /**
     * @param event
     * @throws IOException
     * Switches scene to AddPart.fxml
     */
    public void onAddPart(ActionEvent event) throws IOException {
        SceneSwitcher.goToAddPartForm(event);
    }

    /**
     * @param event
     * @throws IOException
     * Switches scene to ModifyPart.fxml
     * Provides alert if selectedPart is null.
     */
    public void onModifyPart(ActionEvent event) throws IOException {

        selectedPart = allPartsTableView.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            ProgramAlerts.noPartSelected();
        } else {
            SceneSwitcher.goToModifyPartForm(event,selectedPart);
        }
    }

    /**
     * @param event
     * Deletes selected part from inventory if selectedPart is not null.
     * Provides user with confirmation that they want to delete the selected part.
     *
     */
    public void onDeletePart(ActionEvent event) {
        selectedPart = allPartsTableView.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            ProgramAlerts.noPartSelected();
        } else {
            ProgramAlerts.deletePartConfirmation(event, selectedPart);
        }
    }

    /**
     * @param event
     * Deletes selected product from inventory if selectedProduct is not null.
     * Provides user with confirmation that they want to delete the selected product.
     */
    public void onDeleteProduct(ActionEvent event) {
        selectedProduct = allProductsTableView.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            ProgramAlerts.noProductSelected();
        } else {
            ProgramAlerts.deleteProductConfirmation(event, selectedProduct);
        }
    }

    /**
     * @param event
     * @throws IOException
     * Switches scene to AddProduct.fxml
     */
    public void onAddProduct(ActionEvent event) throws IOException {
        SceneSwitcher.goToAddProductForm(event);
        //
    }

    /**
     * @param event
     * @throws IOException
     * Switches scene to ModifyProduct.fxml
     * Provides alert if selectedProduct is null.
     */
    public void onModifyProduct(ActionEvent event) throws IOException {
        selectedProduct = allProductsTableView.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            ProgramAlerts.noProductSelected();
        } else {
            SceneSwitcher.goToModifyProductForm(event, selectedProduct);
        }
    }

    /**
     * @param url
     * @param resourceBundle
     * Populates part table with all parts and product table with all products.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Populate sample part
        allPartsTableView.setItems(Inventory.getAllParts());

        mainPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        mainPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        mainPartInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        mainPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Populate sample products
        allProductsTableView.setItems(Inventory.getAllProducts());

        mainProductIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        mainProductNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        mainProductInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        mainProductPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    /**
     * @param keyEvent
     * Populates parts table with matching ID or strings.
     * Can be searched by part ID or name.
     * String Entries are not case-sensitive.
     */
    public void onKeyTypedPartSearch(KeyEvent keyEvent) {
        ObservableList<Part> partsFound = FXCollections.observableArrayList();
        //This observable arraylist is used to populate matching parts to the part table view.
        Part partFound;

        try { int id = Integer.parseInt(mainPartSearchField.getText());
            //using a try catch block to tell whether an ID (int) or Name (String) is being entered into the search bar.
            partFound = Inventory.lookupPart(id);
            if (partFound != null) {
                partsFound.add(Inventory.lookupPart(id));
                allPartsTableView.setItems(partsFound);
                mainPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
                mainPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                mainPartInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
                mainPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
            } else {
                ProgramAlerts.partNotFound();
                mainPartSearchField.clear();
                resetPartsTableview();
            }

        } catch (NumberFormatException e) {
            //if  value in the part search text field is not an integer this code is ran as it's a string.
            String name = mainPartSearchField.getText();
            partsFound = Inventory.lookupPart(name);
            if (partsFound.size() > 0) {
                allPartsTableView.setItems(Inventory.lookupPart(name));
                mainPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
                mainPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                mainPartInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
                mainPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
            } else {
                ProgramAlerts.partNotFound();
                mainPartSearchField.clear();
                resetPartsTableview();
            }
        }
    }

    /**
     * @param keyEvent
     * Populates products table with matching ID or strings.
     * Can be searched by product ID or name.
     * String Entries are not case-sensitive.
     */
    public void onKeyTypedProductSearch(KeyEvent keyEvent) {
        ObservableList<Product> productsFound = FXCollections.observableArrayList();
        //This observable arraylist is used to populate matching products to the part table view.
        Product productFound;

        try { int id = Integer.parseInt(mainProductSearchField.getText());
            //using a try catch block to tell whether a product ID (int) or Name (String) is being entered into the search bar.
            productFound = Inventory.lookupProduct(id);
            if (productFound != null) {
                productsFound.add(Inventory.lookupProduct(id));
                allProductsTableView.setItems(productsFound);
                mainProductIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
                mainProductNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                mainProductInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
                mainProductPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
            } else {
                ProgramAlerts.productNotFound();
                mainProductSearchField.clear();
                resetProductsTableview();
            }

        } catch (NumberFormatException e) {
            //if  value in the part search text field is not an integer this code is ran as it's a string.
            String name = mainProductSearchField.getText();
            productsFound = Inventory.lookupProduct(name);
            if (productsFound.size() > 0) {
                allProductsTableView.setItems(Inventory.lookupProduct(name));
                mainProductIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
                mainProductNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                mainProductInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
                mainProductPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
            } else {
                ProgramAlerts.productNotFound();
                mainProductSearchField.clear();
                resetProductsTableview();
            }
        }
    }

    public void resetPartsTableview() {
        allPartsTableView.setItems(Inventory.getAllParts());

        mainPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        mainPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        mainPartInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        mainPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    public void resetProductsTableview() {
        allProductsTableView.setItems(Inventory.getAllProducts());

        mainProductIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        mainProductNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        mainProductInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        mainProductPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }


    /**
     * @param event
     * Exits Program.
     */
    public void onActionExit(ActionEvent event) {
        System.exit(0);
    }
    /* Exits Program */
}
