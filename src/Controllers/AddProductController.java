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
 * AddProductController class control AddProduct.fxml
 */
public class AddProductController implements Initializable {

    private ObservableList<Part> relatedParts = FXCollections.observableArrayList();
    public TableView<Part> addedPartTableView;
    public TableView<Part> addPartTableView;

    public TextField addProductSearchBar;
    @FXML
    private TableColumn<Part, Integer> addPartIdCol;

    @FXML
    private TableColumn<Part, Integer> addPartInventoryCol;

    @FXML
    private TableColumn<Part, String> addPartNameCol;

    @FXML
    private TableColumn<Part, Double> addPartPriceCol;

    @FXML
    private TextField addProductInventoryTxt;

    @FXML
    private TextField addProductMaxTxt;

    @FXML
    private TextField addProductMinTxt;

    @FXML
    private TextField addProductNameTxt;

    @FXML
    private TextField addProductPriceTxt;

    @FXML
    private TableColumn<Part, Integer> addedPartIdCol;

    @FXML
    private TableColumn<Part, Integer> addedPartInventoryCol;

    @FXML
    private TableColumn<Part, String> addedPartNameCol;

    @FXML
    private TableColumn<Part, Double> addedPartPriceCol;

    /**
     * @param event
     * Adds selected part to the related parts table view.
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
        ProgramAlerts.backToMainMenuConfirmationAdd(event);
    }

    /**
     * @param event
     * Deletes part from related parts tableview if selection is not null.
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
     * assigns txt fields to respective variables then creates a new product object if entries are valid.
     */
    @FXML
    void onSaveProduct(ActionEvent event) {
        try {
            int id = 0;
            String name = addProductNameTxt.getText();
            Double price = Double.parseDouble(addProductPriceTxt.getText());
            int stock = Integer.parseInt(addProductInventoryTxt.getText());
            int min = Integer.parseInt(addProductMinTxt.getText());
            int max = Integer.parseInt(addProductMaxTxt.getText());

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
                        ProgramAlerts.productAddSuccessful();
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
     * Populates tables views with all parts
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addPartTableView.setItems(Inventory.getAllParts());
        addPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        addPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addPartInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        addedPartTableView.setItems(relatedParts);
        addedPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        addedPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addedPartInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addedPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /**
     * @param keyEvent
     * Searches all parts tableview to check for any matches and populates them to the all parts tableview.
     */
    public void onKeyTypedAddPartSearch(KeyEvent keyEvent) {
        ObservableList<Part> partsFound = FXCollections.observableArrayList();

        Part partFound;

        try { int id = Integer.parseInt(addProductSearchBar.getText());
            partFound = Inventory.lookupPart(id);
            if (partFound != null) {
                partsFound.add(Inventory.lookupPart(id));
                addPartTableView.setItems(partsFound);
                addPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
                addPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                addPartInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
                addPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
            } else {
                ProgramAlerts.partNotFound();
            }
        } catch (NumberFormatException e) {
            String name = addProductSearchBar.getText();
            partsFound = Inventory.lookupPart(name);
            if (partsFound.size() > 0) {
                addPartTableView.setItems(Inventory.lookupPart(name));
                addPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
                addPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                addPartInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
                addPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
            } else {
                ProgramAlerts.partNotFound();
            }
        }
    }
}
