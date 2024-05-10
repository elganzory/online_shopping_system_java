/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafx;

/**
 *
 * @author genzoo
 */
public class Order implements Comparable<Order> {
    private int id;
    Product products[]= new Product[10] ;
    int NumOfItems=0;

    public Order() {
        id=SystemManager.getOrderNo()+1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public int getNumOfItems() {
        return NumOfItems;
    }
    
    
    public void additem(Product p){
        products[NumOfItems]=p;
        NumOfItems++;
    }
    public int getTotalCost()
    {
        int price=0;
        for (int i=0;i<NumOfItems;i++){
            price+=products[i].getPrice();
        }
        return price;
    }
    public String[] getProductsID(){
        String s[]=new String[NumOfItems];
        for (int i=0;i<NumOfItems;i++){
            s[i]=Integer.toString(products[i].getID());
        }
        return s;
    }
    public String[] getProductsName(){
        String s[]=new String[NumOfItems];
        for (int i=0;i<NumOfItems;i++){
            s[i]=products[i].getName();
        }
        return s;
    }
    public String[] getProductsPrice(){
        String s[]=new String[NumOfItems];
        for (int i=0;i<NumOfItems;i++){
            s[i]=Double.toString(products[i].getPrice());
        }
        return s;
    }

    @Override
    public int compareTo(Order o) {
        if (this.getTotalCost()>o.getTotalCost()) return 1;
        else if (this.getTotalCost()<o.getTotalCost()) return -1;
        return 0;
    }
}
