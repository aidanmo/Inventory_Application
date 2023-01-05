package Controllers;


import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * ModifyProductController class controls ModifyProduct.fxml
 */
public class ModifyProductController implements Initializable {

    public TableView<Part> addPartTableView;
    public TableView<Part> addedPartTableView;


    @FXML
    private TableColumn<Part, Integer> modifyAddPartIdCol;

    @FXML
    private TableColumn<Part, Integer> modifyAddPartInventoryCol;

    @FXML
    private TableColumn<Part, String> modifyAddPartNameCol;

    @FXML
    private TableColumn<Part, Double> modifyAddPartPriceCol;

    @FXML
    private TextField modifyAddProductIdTxt;

    @FXML
    private TextField modifyAddProductInventoryTxt;

    @FXML
    private TextField modifyAddProductMaxTxt;

    @FXML
    private TextField modifyAddProductMinTxt;

    @FXML
    private TextField modifyAddProductNameTxt;

    @FXML
    private TextField modifyAddProductPriceTxt;

    @FXML
    private TextField modifyAddProductSearchBar;

    @FXML
    private TableColumn<Part, Integer> modifyAddedPartIdCol;

    @FXML
    private TableColumn<Part, Integer> modifyAddedPartInventoryCol;

    @FXML
    private TableColumn<Part, String> modifyAddedPartNameCol;

    @FXML
    private TableColumn<Part, Double> modifyAddedPartPriceCol;

    Product selectedProduct;

    private ObservableList<Part> relatedParts = FXCollections.observableArrayList();

    /**
     * @param event
     * Adds selected part to related parts table view
     */
    @FXML
    void onAddPart(ActionEvent event) {
        Part selectedPart = addPartTableView.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            ProgramAlerts.noPartSelected();
        } else {
            relatedParts.add(selectedPart);
            addedPartTableView.setItems(relatedParts);
        }
    }

    /**
     * @param event
     * @throws IOException
     * Switches scenes back to the main menu. While providing the user with a confirmation that doing so will delete txt field entries.
     */
    @FXML
    void onCancel(ActionEvent event) throws IOException {
        ProgramAlerts.backToMainMenuConfirmationModify(event);
    }

    /**
     * @param event
     * Removes selected part from related parts table view.
     */
    @FXML
    void onRemovePart(ActionEvent event) {
        Part selectedPart = addedPartTableView.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            ProgramAlerts.noPartSelected();
        } else {
            ProgramAlerts.deleteRelatedPartConfirmation(event, selectedPart, relatedParts);
            addedPartTableView.setItems(relatedParts);
        }
    }

    /**
     * @param event
     * Modifies specifications of selectedProduct if entries are valid.
     */
    @FXML
    void onSaveProduct(ActionEvent event) {
        try {
            selectedProduct = MainMenuController.getSelectedProduct();
            int id = 0;
            String name = modifyAddProductNameTxt.getText();
            Double price = Double.parseDouble(modifyAddProductPriceTxt.getText());
            int stock = Integer.parseInt(modifyAddProductInventoryTxt.getText());
            int min = Integer.parseInt(modifyAddProductMinTxt.getText());
            int max = Integer.parseInt(modifyAddProductMaxTxt.getText());

            if (name.isEmpty()) {
                ProgramAlerts.productNameIsEmpty();
            } else {
                if (EntryValidator.isMinValid(min, max) && EntryValidator.isInventoryValid(min, max, stock)) {
                    if (EntryValidator.isPriceValid(price, relatedParts)) {
                        Product newProduct = new Product(id, name, price, stock, min, max);

                        for(Part parts: relatedParts) {
                            newProduct.addRelatedPart(parts);
                        }
                        newProduct.setId(Inventory.generateProductId());
                        Inventory.addProduct(newProduct);
                        Inventory.deleteProduct(selectedProduct);
                        ProgramAlerts.productModificationSuccessful();
                        SceneSwitcher.goToMain(event);
                    }
                }
            }
        } catch (Exception e) {
            ProgramAlerts.blankFieldsAlert();
        }
    }

    /**
     * @param url
     * @param resourceBundle
     * Populates table views with all parts and related parts.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectedProduct = MainMenuController.getSelectedProduct();
        relatedParts = selectedProduct.getAllRelatedParts();

        addPartTableView.setItems(Inventory.getAllParts());
        modifyAddPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        modifyAddPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        modifyAddPartInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modifyAddPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));


        addedPartTableView.setItems(relatedParts);
        modifyAddedPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        modifyAddedPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        modifyAddedPartInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modifyAddedPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /**
     * @param keyEvent
     * Searches all parts tableview to check for any matches and populates them to the all parts tableview.
     */
    public void onKeyTypedPartSearch(KeyEvent keyEvent) {
        ObservableList<Part> partsFound = FXCollections.observableArrayList();
        //This observable arraylist is for IDs typed in as a part is returned vs and observable array list which is required for the tableview.
        Part partFound;

        try { int id = Integer.parseInt(modifyAddProductSearchBar.getText());
            //using a try catch block to tell whether an ID (int) or Name (String) is being entered into the search bar.
            partFound = Inventory.lookupPart(id);
            if (partFound != null) {
                partsFound.add(Inventory.lookupPart(id));
                addPartTableView.setItems(partsFound);
                modifyAddPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
                modifyAddPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                modifyAddPartInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
                modifyAddPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
            } else {
                ProgramAlerts.partNotFound();
            }

        } catch (NumberFormatException e) {
            //if  value in the part search text field is not an integer this code is ran as it's a string.
            String name = modifyAddProductSearchBar.getText();
            partsFound = Inventory.lookupPart(name);
            if (partsFound.size() > 0) {
                addPartTableView.setItems(Inventory.lookupPart(name));
                modifyAddPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
                modifyAddPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                modifyAddPartInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
                modifyAddPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
            } else {
                ProgramAlerts.partNotFound();
            }
        }
    }

    /**
     * @param product
     * Used to populate text fields with selectedProducts specifications.
     */
    public void sendProduct(Product product) {
        modifyAddProductNameTxt.setText(product.getName());
        modifyAddProductInventoryTxt.setText(String.valueOf(product.getStock()));
        modifyAddProductMaxTxt.setText(String.valueOf(product.getMax()));
        modifyAddProductMinTxt.setText(String.valueOf(product.getMin()));
        modifyAddProductPriceTxt.setText(String.valueOf(product.getPrice()));
        modifyAddProductIdTxt.setText(String.valueOf(product.getId()));
    }
}
