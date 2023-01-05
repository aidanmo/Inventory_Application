package Controllers;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;


/**
 * AddPartController class controls AddPart.fxml
 */
public class AddPartController {

    public Label companyNameOrMachineIdLbl;

    @FXML
    private RadioButton inhouseRBt;

    @FXML
    private RadioButton outsourcedRBt;

    @FXML
    private TextField partCompanyNameTxt;

    @FXML
    private TextField partInventoryTxt;

    @FXML
    private TextField partMaxTxt;

    @FXML
    private TextField partMinTxt;

    @FXML
    private TextField partNameTxt;

    @FXML
    private TextField partPriceTxt;

    @FXML
    private ToggleGroup sourceTG;

    /**
     * @param event
     * @throws IOException
     * Switches scenes back to the main menu. While providing the user with a confirmation that doing so will delete txt field entries.
     */
    @FXML
    void onActionDisplayMainMenu(ActionEvent event) throws IOException {
        ProgramAlerts.backToMainMenuConfirmationAdd(event);
    }

    /**
     * @param event
     * @throws IOException
     * assigns txt fields to respective variables then creates a new part object if entries are valid.
     */
    @FXML
    void onActionSave(ActionEvent event) throws IOException {
        try {
            int id  = Inventory.generatePartId();
            String name = partNameTxt.getText();
            int inventory = Integer.parseInt(partInventoryTxt.getText());
            double price = Double.parseDouble(partPriceTxt.getText());
            int max = Integer.parseInt(partMaxTxt.getText());
            int min = Integer.parseInt(partMinTxt.getText());
            int machineId;
            String companyName;
            boolean partAdditionSucessful = false;
            if (EntryValidator.isMinValid(min ,max) && EntryValidator.isInventoryValid(min, max, inventory)) {
                if(outsourcedRBt.isSelected()) {
                    try {
                        companyName = partCompanyNameTxt.getText();
                        External newExternalPart = new External(id, name, price, inventory, min, max, companyName);
                        Inventory.addPart(newExternalPart);
                        partAdditionSucessful = true;
                    } catch (Exception e) {
                        ProgramAlerts.invalidCompanyNameAlert();
                    }
                } if(inhouseRBt.isSelected()) {
                    try {
                        machineId = Integer.parseInt(partCompanyNameTxt.getText());
                        InHouse newInHousePart = new InHouse(id, name, price, inventory, min, max, machineId);
                        Inventory.addPart(newInHousePart);
                        partAdditionSucessful = true;
                    } catch (Exception e) {
                        ProgramAlerts.invalidMachineIdAlert();
                    }
                } if (partAdditionSucessful) {
                    ProgramAlerts.partAddSuccessful();
                    SceneSwitcher.goToMain(event);
                }
            }
        } catch (Exception e) {
            ProgramAlerts.blankFieldsAlert();
        }
    }

    /**
     * @param event
     * Sets companyNameOrMachineIdLbl to the correct label based on which radio button is selected.
     */
    public void onOutSourcedSelected(ActionEvent event) {
        companyNameOrMachineIdLbl.setText("Company Name");
    }

    /**
     * @param event
     * Sets companyNameOrMachineIdLbl to the correct label based on which radio button is selected.
     */
    public void onInHouseSelected(ActionEvent event) {
        companyNameOrMachineIdLbl.setText("Machine ID");
    }
}
