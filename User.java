/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafx;

/**
 *
 * @author genzoo
 */
public class User {
    private String Name;
    private String Password;
    private int ID;

    public User(String Name, String Password) {
        this.Name = Name;
        this.Password = Password;
    }
    
    
    
    
    void ShowInformation(){};
    
    public String getName() {
        return Name;
    }
      
    public void setName(String Name) {
        this.Name = Name;
    }

     public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
     public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getPassword() {
        return Password;
    }

    @Override
    public String toString() {
        return "User{" + "Name=" + Name + ", ID=" + ID + '}';
    }
    
    public Order getOrder() {
        return new Order();
    }
    
    public void openPage(){}
}
