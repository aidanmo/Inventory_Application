package Main;

import Model.External;
import Model.InHouse;
import Model.Inventory;
import Model.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * The Inventory Manager is an application built to help a manufacturing company in managing
 * an inventory of parts and products made of parts.
 *
 * A compatible feature to implement in future updates of this program would be introducing part quantity when working
 * with a product. As a computer may require multiple sticks of ram or a car may require multiple wheels. Adding this
 * feature would extend its utility to an end user.
 *
 * @author Aidan Morrison
 */
public class Main extends Application {
    /**
     * @param primaryStage
     * @throws Exception
     * The start method creates the FXML stage and loads the initial scene as the starting point for your program.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/MainMenu.fxml"));
        primaryStage.setTitle("Inventory Manager");
        primaryStage.setScene(new Scene(root, 1050, 365));
        primaryStage.show();
    }

    /**
     * @param args
     * The main method is the starting point of the application which launches it.
     *
     * The lines of code preceding the launch(args) method populates the program with sample data to view in the app.
     */
    public static void main(String[] args) {

        int partId = Inventory.generatePartId();
        InHouse plu = new InHouse(partId,"Programmable Logic Unit", 149.99, 7, 2, 30,45);

        partId = Inventory.generatePartId();
        InHouse copperCabling = new InHouse(partId, "STP Cabling 50m", 9.89, 10, 5, 30, 18);

        partId = Inventory.generatePartId();
        InHouse plasticCasing = new InHouse(partId, "Plasti-Form Casing", 15.89, 6, 3, 10, 21);

        partId = Inventory.generatePartId();
        External rubberSuctionCups = new External(partId, "15 mm Rubber Suction Cups", 3.99, 30, 10, 50, "Industrial Labs");

        Inventory.addPart(plu);
        Inventory.addPart(copperCabling);
        Inventory.addPart(plasticCasing);
        Inventory.addPart(rubberSuctionCups);

        int productId = Inventory.generateProductId();
        Product ace = new Product(productId, "Automated Case Erector", 450.00, 5, 2, 10);
        ace.addRelatedPart(plu);
        ace.addRelatedPart(copperCabling);
        ace.addRelatedPart(plasticCasing);
        ace.addRelatedPart(rubberSuctionCups);
        Inventory.addProduct(ace);

        launch(args);
    }
}

