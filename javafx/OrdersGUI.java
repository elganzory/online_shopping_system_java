/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Khaled Mohamed
 */
public class OrdersGUI extends Application{
    
    private Order orders[]=new Order[SystemManager.getOrderNo()];
    public OrdersGUI()
    {
        for (int i=0;i<SystemManager.getOrderNo();i++)
        {
            orders[i]=SystemManager.getOrders()[i];
        }
    }
    @Override
    public void start(Stage stage)
    {
        VBox vb= new VBox();
        vb.setPadding(new Insets(10));
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(5);
        BorderPane ord[]=new BorderPane[SystemManager.getOrderNo()];
        Label ordl[]=new Label[SystemManager.getOrderNo()];
        Button ordb[]=new Button[SystemManager.getOrderNo()];
        
        for (int i=0;i<SystemManager.getOrderNo();i++){
            int x=i;
            ord[i]=new BorderPane();
            ordl[i]=new Label(Integer.toString(orders[i].getId()));
            ordb[i]=new Button("show details");
            ordb[i].setOnAction(e ->{
                Stage s=new Stage();
                BorderPane v=new BorderPane();
                Scene sc=new Scene(v,550,300);
                Label l=new Label("Order ID : "+orders[x].getId()+"\nNumber of Products: "+orders[x].getNumOfItems());
                l.setFont(new Font("Arial",18));
                v.setTop(l);
                ListView<String> listID= new ListView<>();
                listID.getItems().add("Product ID:");
                listID.getItems().addAll(orders[x].getProductsID());
                listID.setPrefSize(150, 80);
                ListView<String> listName= new ListView<>();
                listName.getItems().add("Product Name:");
                listName.getItems().addAll(orders[x].getProductsName());
                listName.setPrefSize(250, 80);
                ListView<String> listPrice= new ListView<>();
                listPrice.getItems().add("Product Price:");
                listPrice.getItems().addAll(orders[x].getProductsPrice());
                listPrice.setPrefSize(150, 80);
                BorderPane.setMargin(l, new Insets(0,0,10,0));
                v.setLeft(listID);
                v.setCenter(listName);
                v.setRight(listPrice);
                Label l2=new Label("Total Cost = "+orders[x].getTotalCost()+"$");
                l2.setFont(new Font("Arial",18));
                BorderPane.setMargin(l2, new Insets(10,0,0,0));
                v.setBottom(l2);
                v.setPadding(new Insets(10));
                s.setTitle("Shopping Store");
                s.setScene(sc);
                s.show();
                
            });
            ord[i].setRight(ordb[i]);
            ord[i].setLeft(ordl[i]);
            
        }
        Scene scene = new Scene(vb,500,300);
        ListView<Node> lv1 =new ListView<>();
        BorderPane title=new BorderPane();
        title.setLeft(new Label("Order ID"));
        title.getLeft().setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        lv1.getItems().add(title);
        for (BorderPane x:ord){
        lv1.getItems().add(x);}
        vb.getChildren().add(lv1);
        Button sortb= new Button("Sort");
        sortb.setOnAction(e -> {
            java.util.Arrays.sort(orders);
            start(stage);
        });
        vb.getChildren().add(sortb);
        stage.setTitle("Shopping Store");
        stage.setScene(scene);
        stage.show();
    }
    
}
