/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafx;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class CustomerPage extends Application {

    private List<Product> products = new ArrayList<>();
    private List<Product> cart = new ArrayList<>();
    private Customer currentUser; // The current logged in user
    
    public CustomerPage(){
        for(int z=0; z<SystemManager.getProductNo();z++){
            products.add(SystemManager.getProducts()[z]);
        }
    }

    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Online Shopping System - Customer Page");

        // Add some products
        
        
        

        
        ListView<Product> productListView = new ListView<>();
        productListView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Product> call(ListView<Product> param) {
                return new ListCell<>() {
                    private ImageView imageView = new ImageView();

                    @Override
                    protected void updateItem(Product item, boolean empty){
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            imageView.setImage(item.getImg());
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
            try{
                Product selectedProduct = productListView.getSelectionModel().getSelectedItem();
                if (selectedProduct != null) {
                    if (selectedProduct.getStock()<= 0) {
                        throw new IllegalStateException("Product stock is empty");
                    }
                    Login_page.getUser().getOrder().additem(selectedProduct);
                    cartListView.getItems().add(selectedProduct); 
                    selectedProduct.setStock(selectedProduct.getStock()-1);
                }
            }catch (IllegalStateException ex) {
                Login_page.showAlert("out of stock");
            }
        });

        Button showSpecsButton = new Button("Show Product Specs");
        showSpecsButton.setOnAction(e -> {
            Product selectedProduct = productListView.getSelectionModel().getSelectedItem();
            if (selectedProduct != null) {
                Stage specstage= new Stage();
                Label l= new Label(selectedProduct.getDescription());
                System.out.println(selectedProduct.getDescription());
                ImageView img=new ImageView(selectedProduct.getImg());
                img.setPreserveRatio(true);
                img.setFitHeight(150);
                img.setFitWidth(200);
                HBox hbox=new HBox();
                hbox.setPadding(new Insets(10));
                hbox.setSpacing(10);
                VBox vbox=new VBox();
                vbox.setSpacing(20);
                vbox.setAlignment(Pos.TOP_CENTER);
                vbox.setBackground(new Background(new BackgroundFill(Color.WHITE,CornerRadii.EMPTY, Insets.EMPTY)));
                vbox.getChildren().addAll(img,new Label("price : $"+selectedProduct.getPrice()),new Label("stock : "+selectedProduct.getStock()));
                hbox.getChildren().addAll(vbox,l);
                Scene specscene=new Scene(hbox,400,300);
                specstage.setScene(specscene);
                specstage.setTitle(selectedProduct.getName());
                specstage.show();
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
            Label PhoneLabel = new Label("Phone:");
            TextField PhoneField = new TextField(((Customer)Login_page.getUser()).getPhone());
            Label EmailLabel = new Label("Email:");
            TextField EmailField = new TextField(((Customer)Login_page.getUser()).getEmail());
            Label AddressLabel = new Label("Address:");
            TextField AddressField = new TextField(((Customer)Login_page.getUser()).getAddress());
            

            Button updateButton = new Button("Update");
            updateButton.setOnAction(ev -> {
                try {
                    Login_page.getUser().setName(usernameField.getText());
                    Login_page.getUser().setPassword(passwordField.getText());
                    ((Customer)Login_page.getUser()).setPhone(PhoneField.getText());
                    ((Customer)Login_page.getUser()).setEmail(EmailField.getText());
                    ((Customer)Login_page.getUser()).setAddress(AddressField.getText());
                    userFormStage.close();
                } catch (EmptyStringException ex) {
                    Login_page.showAlert("user name or password can't be empty");
                }
            });

            // Create a grid pane and add the labels and text fields
            GridPane gridPane = new GridPane();
            gridPane.setVgap(10);
            gridPane.setHgap(10);
            gridPane.setAlignment(Pos.CENTER);
            gridPane.setPadding(new Insets(10));
            gridPane.add(usernameLabel, 0, 0);
            gridPane.add(usernameField, 1, 0);
            gridPane.add(passwordLabel, 0, 1);
            gridPane.add(passwordField, 1, 1);
            gridPane.add(PhoneLabel, 0, 2);
            gridPane.add(PhoneField, 1, 2);
            gridPane.add(EmailLabel, 0, 3);
            gridPane.add(EmailField, 1, 3);
            gridPane.add(AddressLabel, 0, 4);
            gridPane.add(AddressField, 1, 4);
            gridPane.add(updateButton, 0, 5);
            // Add other fields to the grid pane here...

            // Create a scene and set it on the stage
            Scene scene = new Scene(gridPane, 300, 300);
            userFormStage.setScene(scene);
            userFormStage.show();
        });

        Button signOutButton = new Button("Sign Out"); // New Sign Out button
        signOutButton.setOnAction(e -> {
            Login_page login = new Login_page();
            try {
                login.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        
        Button sortb= new Button("Sort with price");
        sortb.setOnAction(e -> {
            Collections.sort(products);
            start(primaryStage);
        });

        HBox hBox1 = new HBox(addButton, showSpecsButton,sortb); // First row of buttons
        hBox1.setSpacing(5);
        HBox hBox2 = new HBox(showUserInfoButton, signOutButton); // Second row of buttons
        hBox2.setSpacing(5);

        VBox vBox = new VBox(productListView, hBox1, hBox2, cartListView); // Add the new buttons to the layout
        Scene scene = new Scene(vBox, 1000, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
}

















 
