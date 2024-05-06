/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafx;



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
