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

    public Order getOrder() {
        return order;
    }
    
    
    
  
    
@Override
    public void openPage(){
        CustomerPage CustomerPage=new CustomerPage();
        CustomerPage.start(new Stage());
    }
}
