package Controllers;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;


/**
 * ModifyPartController class controls ModifyPart.fxml
 */
public class ModifyPartController {

    public TextField partIdTxt;

    @FXML
    private Label companyNameOrMachineIdLbl;

    @FXML
    private RadioButton inhouseRBt;

    @FXML
    private TextField partCompanyNameOrMachineId;

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
    private RadioButton outsourcedRBt;

    @FXML
    private ToggleGroup sourceTG;

    Part selectedPart;

    /**
     * @param event
     * @throws IOException
     * Switches scenes back to the main menu. While providing the user with a confirmation that doing so will delete txt field entries.
     */
    @FXML
    void onActionDisplayMainMenu(ActionEvent event) throws IOException {
        ProgramAlerts.backToMainMenuConfirmationModify(event);
    }

    /**
     * @param event
     * @throws IOException
     * Modifies specifications of selectedPart if entries are valid.
     */
    @FXML
    void onActionSave(ActionEvent event) throws IOException {

            try {
                selectedPart = MainMenuController.getSelectedPart();
                int partId = Integer.parseInt(partIdTxt.getText());
                String partName = partNameTxt.getText();
                int partInventory = Integer.parseInt(partInventoryTxt.getText());
                double partPrice = Double.parseDouble(partPriceTxt.getText());
                int max = Integer.parseInt(partMaxTxt.getText());
                int min = Integer.parseInt(partMinTxt.getText());
                String companyName;
                int machineId;
                boolean partModificationSucessful = false;

                if (EntryValidator.isMinValid(min ,max) && EntryValidator.isInventoryValid(min, max, partInventory)) {
                    if(outsourcedRBt.isSelected()) {
                        try {
                            companyName = partCompanyNameOrMachineId.getText();
                            External newExternalPart = new External(partId, partName, partPrice, partInventory, min, max, companyName);
                            Inventory.addPart(newExternalPart);
                            partModificationSucessful = true;
                        } catch (Exception e) {
                            ProgramAlerts.invalidCompanyNameAlert();
                        }
                    } if(inhouseRBt.isSelected()) {
                        try {
                            machineId = Integer.parseInt(partCompanyNameOrMachineId.getText());
                            InHouse newInHousePart = new InHouse(partId, partName, partPrice, partInventory, min, max, machineId);
                            Inventory.addPart(newInHousePart);
                            partModificationSucessful = true;
                        } catch (Exception e) {
                            ProgramAlerts.invalidMachineIdAlert();
                        }
                    } if (partModificationSucessful) {
                        Inventory.deletePart(selectedPart);
                        ProgramAlerts.partModificationSuccessful();
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
    @FXML
    void onInHouseSelected(ActionEvent event) {
        companyNameOrMachineIdLbl.setText("Machine ID");
    }

    /**
     * @param event
     * Sets companyNameOrMachineIdLbl to the correct label based on which radio button is selected.
     */
    @FXML
    void onOutSourcedSelected(ActionEvent event) {
        companyNameOrMachineIdLbl.setText("Company Name");
    }

    /**
     * @param part
     * Used to populate text fields with selectedParts specifications.
     */
    public void sendPart(Part part) {
        partNameTxt.setText(part.getName());
        partInventoryTxt.setText(String.valueOf(part.getStock()));
        partMaxTxt.setText(String.valueOf(part.getMax()));
        partMinTxt.setText(String.valueOf(part.getMin()));
        partPriceTxt.setText(String.valueOf(part.getPrice()));
        partIdTxt.setText(String.valueOf(part.getId()));
        if (part instanceof External) {
            outsourcedRBt.setSelected(true);
        } if (part instanceof InHouse) {
            inhouseRBt.setSelected(true);
        }
        if(part instanceof InHouse) {
            companyNameOrMachineIdLbl.setText("Machine ID");
            partCompanyNameOrMachineId.setText(String.valueOf(((InHouse) part).getMachineId()));

        } if (part instanceof External) {
            companyNameOrMachineIdLbl.setText("Company Name");
            partCompanyNameOrMachineId.setText(((External) part).getCompanyName());
        }


    }
}
