package javafx;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;



public class AdminPage extends Stage {

    public AdminPage() {
    	 // Create lists for products and users
        ListView<Product> productList = new ListView<>();
        ListView<User> vendorList = new ListView<>();

        // Sample data for demonstration
        List<Product> products = new ArrayList<>();
        for(int z=0; z<SystemManager.getProductNo();z++){
            products.add(SystemManager.getProducts()[z]);
        }
        // Sort products list in ascending order according to price
        //Collections.sort(products);

        List<User> vendors = new ArrayList<>();
       
        for(int i=0;i<SystemManager.getUserNo();i++) {
            if(SystemManager.getUser(i) instanceof Vendor){
        	vendors.add(SystemManager.getUser(i));
        }}

        productList.getItems().addAll(products);
        vendorList.getItems().addAll(vendors);

        // Create labels for column titles
        Label productsLabel = new Label("Products");
        productsLabel.setFont(Font.font(16)); // Set font size to 1.5 times bigger

        Label usersLabel = new Label("Vendors");
        usersLabel.setFont(Font.font(16)); // Set font size to 1.5 times bigger

        // Create button for adding user
        Button addButton = new Button("Add Vendor");
        addButton.setOnAction(e -> {
        	openAddVendorStage();
            close();
        });
        
        Button showOrders=new Button("Show Orders");
        showOrders.setOnAction((e)->{
            OrdersGUI ordergui=new OrdersGUI();
            ordergui.start(new Stage());});
        // Create layout for footer
        Button signOut=new Button("sign out");
        signOut.setOnAction(e ->{
            Login_page login = new Login_page();
            try {
                login.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(AdminPage.class.getName()).log(Level.SEVERE, null, ex);
            }
            close();
        });
        // Create layout for footer
        BorderPane footer = new BorderPane();
        footer.setPadding(new Insets(10));
        footer.setRight(addButton);
        footer.setLeft(showOrders);
        footer.setCenter(signOut);

        
        // Create layout for titles
        BorderPane titlesPane = new BorderPane();
        titlesPane.setLeft(productsLabel);
        titlesPane.setRight(usersLabel);

        // Create layout for the main content
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        productList.setPrefWidth(350);
        vendorList.setPrefWidth(350);
        root.setLeft(productList);
        root.setRight(vendorList);
        root.setTop(titlesPane); // Set titlesPane as the top of the BorderPane
        root.setBottom(footer);

        // Create scene and set it to the stage
        Scene scene = new Scene(root, 800, 500);
        setTitle("Admin Page");
        setScene(scene);
        show();
    }

    // Function to open new page
    private void openAddVendorStage() {
        AddVendor addVendor = new AddVendor();
        addVendor.show();
        //addVendor.start(primaryStage);
    }

}

