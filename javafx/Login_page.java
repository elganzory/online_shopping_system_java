
package javafx;

import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Login_page extends Application{

    private static User user;

    public static void setUser(User user1) {
        user = user1;
    }

    public static User getUser() {
        return user;
    }
   
   
  
    
    
    @Override
    public void start(Stage Primarystage) {
        
           
       Text text1=new Text("username");
       Text text2=new Text("password");
      
       TextField UserName=new TextField();
       PasswordField Password=new PasswordField();
       
       //temprarily add admins and vendors
       
     
       
       
       Button register=new Button("register");
       register.setOnAction(e ->{          
          
              String username = UserName.getText();
              String password = Password.getText();

              for(int n=0; n < SystemManager.getUserNo(); n++){     
                  if(SystemManager.getUser(n).getName().equals (username)){
                      showAlert("This name is already taken");
                      return;                     
              }}
              try{
              SystemManager.addUser(new Customer(username,password ));
              CustomerPage CustomerPage=new CustomerPage();
              Login_page.setUser(SystemManager.getUser(SystemManager.getUserNo()-1));
              CustomerPage.start(Primarystage);
              }catch(EmptyStringException ex){
                  showAlert("Username and password fields cannot be empty");
              }
           });
       
       
       
       Button login=new Button("login");
       login.setOnAction(e ->{
            
              String username = UserName.getText();
              String password = Password.getText();

              if(username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
                  showAlert("Username and password fields cannot be empty");
                  return;
              }

              for(int n=0; n < SystemManager.getUserNo(); n++){     
                  if(SystemManager.getUser(n).getName().equals (username)){
                      if(SystemManager.getUser(n).getPassword().equals (password)){
                          
                          Login_page.setUser(SystemManager.getUser(n));
                          SystemManager.getUser(n).openPage();
                          Primarystage.close();
                          return;
                      }                          
                  }
              }
              showAlert("Username or Password may be invalid");
             
           });   

       
       GridPane pane=new GridPane();
       pane.add(text1,0,0);
       pane.add(text2,0,1);
       pane.add(UserName,1,0);
       pane.add(Password,1,1);
       pane.add(register,0,4);
       pane.add(login,1,4);
       pane.setHgap(10);
       pane.setVgap(5);
       pane.setAlignment(Pos.CENTER);
        
        Scene scene=new Scene(pane,300,200);

        Primarystage.setScene(scene);
        Primarystage.setTitle("register");
        Primarystage.show();  
    }

    public static void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    public static void main(String[] args) throws EmptyStringException,FileNotFoundException {
        SystemManager.addUser(new Admin("admin","admin"));
        SystemManager.addUser(new Admin("yousef","yousef"));
        SystemManager.addUser(new Vendor("khaled","khaled"));
        SystemManager.addUser(new Vendor("mark","mark"));
        SystemManager.addProduct(new Product("Product 1", 39.99, 50, "src\\images\\photo-1505740420928-5e560c06d30e.jpeg","m",0));
        SystemManager.addProduct(new Product("Product 2", 19.99, 50, "src\\images\\tmp.jpg", "m",0));
        SystemManager.addProduct(new Product("Product 3", 29.99, 50, "src\\images\\tmp (1).jpg", "m",0));
        launch(args);
    }
//
}




