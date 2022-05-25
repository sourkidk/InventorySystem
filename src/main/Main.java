package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Product;

import java.sql.SQLOutput;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/MainForm.fxml"));
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * This is the Main method for the application.  It's primary function is call the launch method, which initializes
     * the application.  I've also instantiated some test Parts and Products here so that we can work with the application
     * without adding objects manually every time you execute.
     *
     * FUTURE ENHANCEMENT:
     * I'd like to add an adjustable parameter to the application that allows the person managing inventory to set
     * a value for a specific threshold at which they'd like to be reminded to reorder or produce items.  This value would likely
     * be a percentage that checks the current INV value against the min/max and flags the item for reorder.
     * You could have a flag or checkbox on each item detail page indicating needing to be reordered or produced in the
     * case of In-house products.  You could also add an additional scene accessible via a button on the Main form that provides a
     * list of all items that need to be reordered or produced.
     * */


    public static void main(String[] args) {

        Product product1 = new Product(1001, "Schwinn Road Bike", 299.99, 2, 1, 4);
        Product product2 = new Product(1002, "Lady's Bianchi Girl Road Bike", 599.99, 3, 1, 8);
        Product product3 = new Product(1003, "Giant Cross Bike", 1299.99, 2, 1, 5);
        Product product4 = new Product(1004, "Twenty-Niner Mountain Bike", 1499.99, 2, 1, 2);

        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        Inventory.addProduct(product3);
        Inventory.addProduct(product4);

        InHouse inhouse1 = new InHouse(5001, "700cc Wheel", 80.0, 6, 4, 12, 3);
        InHouse inhouse2 = new InHouse(5002, "650cc Wheel", 80.0, 3, 2, 8, 3);
        InHouse inhouse3 = new InHouse(5003, "700cc Tire", 12.0, 10, 6, 16, 2);

        Inventory.addPart(inhouse1);
        Inventory.addPart(inhouse2);
        Inventory.addPart(inhouse3);


        launch(args);
    }
}
