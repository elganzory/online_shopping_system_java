package javafx;

import javafx.stage.Stage;

public class Vendor extends User {
    private Product products[]=new Product[20];
    private int NumOfProducts;
    
    public Vendor(String name, String password) throws EmptyStringException {
        super(name,password);

    }

    public Product[] getProducts() {
        return products;
    }

    public int getNumOfProducts() {
        return NumOfProducts;
    }

    public void setNumOfProducts(int NumOfProducts) {
        this.NumOfProducts = NumOfProducts;
    }
    
    
    public void addProduct(Product p){
        products[NumOfProducts]=p;
        NumOfProducts++;
    }
    
    



    @Override
    public String toString() {
        return "Name: " + super.getName() + " ID: " + super.getID();
    }
    
    @Override
    public void openPage(){
        SellerPage sellerpage=new SellerPage();
        sellerpage.start(new Stage());
    }
    
}
