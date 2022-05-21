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


    public static void main(String[] args) {

        Product product1 = new Product(1001, "Schwinn Road Bike", 299.99, 2, 1, 4);
        Product product2 = new Product(1002, "Lady's Bianchi Girl Road Bike", 599.99, 1, 1, 8);
        Product product3 = new Product(1003, "Giant Cross Bike", 1299.99, 2, 1, 5);
        Product product4 = new Product(1004, "Twenty-Niner Mountain Bike", 1499.99, 1, 1, 2);

        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        Inventory.addProduct(product3);
        Inventory.addProduct(product4);
//        System.out.println(Inventory.getAllProducts());

        InHouse inhouse1 = new InHouse(5001, "700cc Wheel", 80.0, 6, 4, 12, 3);
        InHouse inhouse2 = new InHouse(5002, "650cc Wheel", 80.0, 3, 2, 8, 3);
        InHouse inhouse3 = new InHouse(5003, "700cc Tire", 12.0, 10, 6, 16, 2);

        Inventory.addPart(inhouse1);
        Inventory.addPart(inhouse2);
        Inventory.addPart(inhouse3);
//        System.out.println(Inventory.getAllParts());



        launch(args);
    }
}
