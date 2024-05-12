/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafx;

import javafx.stage.Stage;

/**
 *
 * @author genzoo
 */
public class Customer extends User {
private String Phone=null;
private String Email=null;
private String Address=null;
private Order order;
 

 
      public Customer(String name, String password) throws EmptyStringException {
        super(name, password);
        order=new Order();
        SystemManager.addOrder(order);
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    

    public Order getOrder() {
        return order;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }
    
    
    
    
  
    
@Override
    public void openPage(){
        CustomerPage CustomerPage=new CustomerPage();
        CustomerPage.start(new Stage());
    }
}
