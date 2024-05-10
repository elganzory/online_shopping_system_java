package javafx;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;

public class SellerPage extends Application {

    private TableView<Product> table = new TableView<>();
    private final ObservableList<Product> data = FXCollections.observableArrayList();
    private Product vendorProducts[]=new Product[((Vendor)Login_page.getUser()).getNumOfProducts()];
    
    public SellerPage(){
        for (int i=0;i<((Vendor)Login_page.getUser()).getNumOfProducts();i++){
            vendorProducts[i]=((Vendor)Login_page.getUser()).getProducts()[i];
        }
    }
    

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Seller Dashboard");

        // Table columns
        TableColumn<Product, String> nameCol = new TableColumn<>("Product Name");
        nameCol.setMinWidth(100);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Product, Double> priceCol = new TableColumn<>("Price");
        priceCol.setMinWidth(50);
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Product, Integer> stockCol = new TableColumn<>("Stock");
        stockCol.setMinWidth(50);
        stockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

        TableColumn<Product, String> imageURLCol = new TableColumn<>("Image URL");
        imageURLCol.setMinWidth(200);
        imageURLCol.setCellValueFactory(new PropertyValueFactory<>("imageURL"));

        TableColumn<Product, String> descriptionCol = new TableColumn<>("Description");
        descriptionCol.setMinWidth(150);
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        TableColumn<Product, Double> discountCol = new TableColumn<>("Discount (%)");
        discountCol.setMinWidth(50);
        discountCol.setCellValueFactory(new PropertyValueFactory<>("offerpercent"));

        table.setItems(data);
        table.getColumns().addAll(nameCol, descriptionCol,priceCol, stockCol, imageURLCol, discountCol);
        data.addAll(vendorProducts);

        // Buttons
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> showAddProductDialog());

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            Product selected = table.getSelectionModel().getSelectedItem();
            data.remove(selected);
        });
        BorderPane buttons=new BorderPane();
        buttons.setPadding(new Insets(10));
        BorderPane.setMargin(addButton, new Insets(0,10,0,0));
        buttons.setLeft(addButton);
        BorderPane.setAlignment(deleteButton, Pos.CENTER_LEFT);
        buttons.setCenter(deleteButton);
        Button signOut=new Button("sign out");
        signOut.setOnAction(e ->{
            Login_page login = new Login_page();
            try {
                login.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(AdminPage.class.getName()).log(Level.SEVERE, null, ex);
            }
            primaryStage.close();
        });
        buttons.setRight(signOut);
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.getChildren().addAll(table, buttons);

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAddProductDialog() {
        Dialog<Product> dialog = new Dialog<>();
        dialog.setTitle("Add New Product");

        // GridPane for input fields
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10)); // Adds padding around the grid

        // Create and configure text fields
        TextField nameField = new TextField();
        nameField.setPromptText("Product Name");
        nameField.setMinWidth(300); // Set minimum width for the text field

        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Description");
        descriptionField.setMinWidth(300); // Ensure all fields have consistent width

        TextField priceField = new TextField();
        priceField.setPromptText("Price");
        priceField.setMinWidth(300);

        TextField stockField = new TextField();
        stockField.setPromptText("Stock");
        stockField.setMinWidth(300);

        TextField imageURLField = new TextField();
        imageURLField.setPromptText("Image URL");
        imageURLField.setMinWidth(300);

        TextField discountField = new TextField();
        discountField.setPromptText("Discount (%)");
        discountField.setMinWidth(300);

        // Add fields and labels to the grid
        grid.add(new Label("Product Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Description:"), 0, 1);
        grid.add(descriptionField, 1, 1);
        grid.add(new Label("Price:"), 0, 2);
        grid.add(priceField, 1, 2);
        grid.add(new Label("Stock:"), 0, 3);
        grid.add(stockField, 1, 3);
        grid.add(new Label("Image URL:"), 0, 4);
        grid.add(imageURLField, 1, 4);
        grid.add(new Label("Discount (%):"), 0, 5);
        grid.add(discountField, 1, 5);

        // Set content and buttons of dialog
        dialog.getDialogPane().setContent(grid);
        ButtonType addButton = new ButtonType("Add Product", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButton) {
                return new Product(
                        nameField.getText(),
                        Double.parseDouble(priceField.getText()),
                        Integer.parseInt(stockField.getText()),
                        imageURLField.getText(),
                        descriptionField.getText(),
                        Double.parseDouble(discountField.getText())

                );
            }
            return null;
        });

        dialog.showAndWait().ifPresent(product -> {
            data.add(product);
            SystemManager.addProduct(product);
            ((Vendor)Login_page.getUser()).addProduct(product);
        });
    }
    public static void main(String[] args){
        Application.launch(args);
    }
}

