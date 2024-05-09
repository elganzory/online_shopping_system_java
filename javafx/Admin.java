/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafx;

/**
 *
 * @author genzoo
 */
public class Admin extends User{
    
     public Admin(String username,String password) {
        super(username,password);
    }
    
     @Override
   public void openPage(){
       AdminPage admin=new AdminPage();
       admin.show();
   }
}
