/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafx;

//public class Product implements Offer , Available{
public class Product implements Comparable<Product> {
    
    private int ID;
    private String name;
    private String Description;
    private double price;
    private double offerpercent;
    private int stock;
    private String ImageURL;

    
    public Product( String name, double price, int stock, String ImageURL,String Description) {
       this.ID = ID=SystemManager.getProductNo()+1;;
        this.name = name;
        this.Description = Description;
        this.price = price;
        this.stock=stock;
        this.ImageURL=ImageURL;
    }
    
    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return Description;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }
    
    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String title) {
        this.name = name;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public void setStock(int stock) {
        this.stock = stock;
    }
        
    public void set_discount(double discount) {
        this.offerpercent=discount;
        setPrice (price - price*(offerpercent/100));
    }
	
    public boolean IsAvailable() {
        if(stock==0){
            return false;
        }
        else{
            return true;
        }
    }
    
     public String toString() { // Override toString to display the product name and price in the cart ListView
        return name + " - $" + price;
    }

    @Override
    public int compareTo(Product o) {
        if (this.getPrice()>o.getPrice()) return 1;
        else if (this.getPrice()<o.getPrice()) return -1;
        return 0;
    }

}
