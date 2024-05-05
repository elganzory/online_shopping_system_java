/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafx;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Khaled Mohamed
 */
public class SystemManager {
    static private User[] user = new User[50];
    static private int userNo=0;
    static private Product products[] = new Product[50];
    static private int productNo=0;
    static private Order orders[] = new Order[50];
    static private int orderNo=0;

    public static void addUser(User c){
        user[userNo]=c;
        userNo++;
    }
    public static void addProduct(Product p){
        products[productNo]=p;
        productNo++;
    }
    public static void addOrder(Order O){
        orders[orderNo]=O;
        orderNo++;
    }
    public static void OrdersGUI()
    {
        Stage stage=new Stage();
        StackPane sp= new StackPane();
        BorderPane ord[]=new BorderPane[orderNo];
        Label ordl[]=new Label[orderNo];
        Button ordb[]=new Button[orderNo];
        
        for (int i=0;i<orderNo;i++){
            int x=i;
            ord[i]=new BorderPane();
            ordl[i]=new Label(Integer.toString(orders[i].getId()));
            ordb[i]=new Button("show details");
            ordb[i].setOnAction(e ->{
                Stage s=new Stage();
                BorderPane v=new BorderPane();
                Scene sc=new Scene(v,550,300);
                Label l=new Label("Order ID : "+orders[x].getId()+"\nNumber of Products: "+orders[x].getNumOfItems()+"\n\n\n"+orders[x].displayAllProducts());
                l.setFont(new Font("Arial",18));
                v.setLeft(l);
                s.setTitle("Shopping Store");
                s.setScene(sc);
                s.show();
                
            });
            ord[i].setRight(ordb[i]);
            ord[i].setLeft(ordl[i]);
            
        }
        Scene scene = new Scene(sp,500,300);
        ListView<Node> lv1 =new ListView<>();
        BorderPane title=new BorderPane();
        title.setLeft(new Label("Order ID"));
        title.getLeft().setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        lv1.getItems().add(title);
        for (BorderPane x:ord){
        lv1.getItems().add(x);}
        sp.getChildren().add(lv1);
        stage.setTitle("Shopping Store");
        stage.setScene(scene);
        stage.show();
    }
    
    /*public static void displayAllCustomers(){
        for (int i=0;i<costumerNo;i++){
            System.out.println(user[i].getName());
        }
    }
    
    public static void displayAllProducts(){
        for (int i=0;i<productNo;i++){
            System.out.println(products[i].getId());
        }
    }
    
    public static void displayAllOrders(){
        for (int i=0;i<orderNo;i++){
            System.out.println(orders[i].getId());
        }
    }*/
    
    public static int getUserNo() {
        return userNo;
    }

    public static User getUser(int n) {
        return user[n];
    }

    public static Product[] getProducts() {
        return products;
    }

    public static int getProductNo() {
        return productNo;
    }

    public static int getOrderNo() {
        return orderNo;
    }

    public static Order[] getOrders() {
        return orders;
    }

    public static User[] getUser() {
        return user;
    }
    
    
}
