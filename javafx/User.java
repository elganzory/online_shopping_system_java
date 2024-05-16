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

    public User (String Name, String Password)throws EmptyStringException {
        
        if(Name==null || Name.trim().isEmpty() || Password==null || Password.trim().isEmpty())
            throw new EmptyStringException("user name or password can't be empty");
        this.Name = Name;
        this.Password = Password;
        ID=SystemManager.getUserNo()+1;
    }
    
    
    
    
    
    public String getName() {
        return Name;
    }
      
    public void setName(String Name) throws EmptyStringException {
        if(Name==null || Name.trim().isEmpty())
             throw new EmptyStringException("Name can't be empty");
        this.Name = Name;
    }

     public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
     public void setPassword(String Password) throws EmptyStringException {
         if(Password==null || Password.trim().isEmpty())
             throw new EmptyStringException("Password can't be empty");
        this.Password = Password;
    }

    public String getPassword() {
        return Password;
    }

