package Model;

import Controllers.ModifyPartController;
import Controllers.ModifyProductController;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSwitcher {
    static Stage stage;

    static Parent scene;

    /**
     * @param event
     * @throws IOException
     * Switches scenes to the MainMenu.fxml scene.
     */
    public static void goToMain(Event event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(SceneSwitcher.class.getResource("/Views/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * @param event
     * @param selectedPart
     * @throws IOException
     * Switches scenes to the ModifyPart.fxml scene.
     */
    public static void goToModifyPartForm(Event event, Part selectedPart) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(SceneSwitcher.class.getResource("/Views/ModifyPart.fxml"));
        loader.load();

        ModifyPartController mpController = loader.getController();

        mpController.sendPart(selectedPart);

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * @param event
     * @param selectedProduct
     * @throws IOException
     * Switches scenes to the ModifyProduct.fxml scene.
     */
    public static void goToModifyProductForm(Event event, Product selectedProduct) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(SceneSwitcher.class.getResource("/Views/ModifyProduct.fxml"));
        loader.load();

        ModifyProductController mpController = loader.getController();

        mpController.sendProduct(selectedProduct);

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }


    /**
     * @param event
     * @throws IOException
     * Switches scenes to the AddPart.fxml scene.
     */
    public static void goToAddPartForm(Event event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(SceneSwitcher.class.getResource("/Views/AddPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * @param event
     * @throws IOException
     * Switches scenes to the AddPart.fxml scene.
     */
    public static void goToAddProductForm(Event event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(SceneSwitcher.class.getResource("/Views/AddProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
