/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafx;

/**
 *
 * @author genzoo
 */
public class Order {
    private int id;
    Product products[]= new Product[10] ;
    int NumOfItems=0;

    public Order() {
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
    public String displayAllProducts()
    {
        String temp="";
        for (int i=0;i<NumOfItems;i++){
            temp=temp+"Product ID: "+products[i].getID()+"    Product name: "+products[i].getName()+"     Product Price: "+products[i].getPrice()+"$\n";
        }
        temp=temp+"\n\nTOTAL COST = "+getTotalCost()+"$";
        return temp;
    }
}
