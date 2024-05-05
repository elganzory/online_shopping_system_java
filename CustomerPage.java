/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;

public class CustomerPage extends Application {

    private List<Product> products = new ArrayList<>();
    private List<Product> cart = new ArrayList<>();
    private Customer currentUser; // The current logged in user

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Online Shopping System - Customer Page");

        // Add some products
        
        
        for(int z=0; z<SystemManager.getProductNo();z++){
            products.add(SystemManager.getProducts()[z]);
        }

        
        ListView<Product> productListView = new ListView<>();
        productListView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Product> call(ListView<Product> param) {
                return new ListCell<>() {
                    private ImageView imageView = new ImageView();

                    @Override
                    protected void updateItem(Product item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            Image image = new Image(item.getImageURL());
                            imageView.setImage(image);
                            imageView.setFitHeight(50); // set height
                            imageView.setFitWidth(50);  // set width
                            imageView.setPreserveRatio(true);
                            setText(item.getName() + " - $" + item.getPrice());
                            setGraphic(imageView);
                        }
                    }
                };
            }
        });
        productListView.getItems().addAll(products);

        ListView<Product> cartListView = new ListView<>(); // New ListView to display cart items
        for(int i=0; i<Login_page.getUser().getOrder().NumOfItems; i++)
        cartListView.getItems().add(Login_page.getUser().getOrder().getProducts()[i]);
        
        Button addButton = new Button("Add to Cart");
        addButton.setOnAction(e -> {
            Product selectedProduct = productListView.getSelectionModel().getSelectedItem();
            if (selectedProduct != null) {
                
                Login_page.getUser().getOrder().additem(selectedProduct);
                cartListView.getItems().add(selectedProduct); // Add the product to the cart ListView
                System.out.println(Login_page.getUser().getOrder().displayAllProducts());
            }
        });

        Button showSpecsButton = new Button("Show Product Specs");
        showSpecsButton.setOnAction(e -> {
            Product selectedProduct = productListView.getSelectionModel().getSelectedItem();
            if (selectedProduct != null) {
                System.out.println("Showing specs for: " + selectedProduct);
            }
        });

        Button showUserInfoButton = new Button("Show User Information");
        showUserInfoButton.setOnAction(e -> {
            // Create a new stage for the user information form
            Stage userFormStage = new Stage();
            userFormStage.setTitle("User Information");

            // Create labels and text fields for the user information
            Label usernameLabel = new Label("Username:");
            TextField usernameField = new TextField(Login_page.getUser().getName());
            Label passwordLabel = new Label("Password:");
            TextField passwordField = new TextField(Login_page.getUser().getPassword());
            // Add other fields like email and phone here...

            Button updateButton = new Button("Update");
            updateButton.setOnAction(ev -> {
                Login_page.getUser().setName(usernameField.getText());
                Login_page.getUser().setPassword(passwordField.getText());
                userFormStage.close();
            });

            // Create a grid pane and add the labels and text fields
            GridPane gridPane = new GridPane();
            gridPane.add(usernameLabel, 0, 0);
            gridPane.add(usernameField, 1, 0);
            gridPane.add(passwordLabel, 0, 1);
            gridPane.add(passwordField, 1, 1);
            gridPane.add(updateButton, 0, 2);
            // Add other fields to the grid pane here...

            // Create a scene and set it on the stage
            Scene scene = new Scene(gridPane, 300, 200);
            userFormStage.setScene(scene);
            userFormStage.show();
        });

        Button signOutButton = new Button("Sign Out"); // New Sign Out button
        signOutButton.setOnAction(e -> {
            System.out.println("Signing out...");
            Login_page login = new Login_page();
            try {
                login.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        HBox hBox1 = new HBox(addButton, showSpecsButton); // First row of buttons
        HBox hBox2 = new HBox(showUserInfoButton, signOutButton); // Second row of buttons

        VBox vBox = new VBox(productListView, hBox1, hBox2, cartListView); // Add the new buttons to the layout
        Scene scene = new Scene(vBox, 1000, 500);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

















 
