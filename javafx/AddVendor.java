package javafx;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;






public class AddVendor extends Stage {

	public AddVendor() {
        Text text1=new Text("Seller Name");
        Text text2=new Text("Seller Password");
       
        TextField nameField=new TextField();
        TextField passwordfield=new TextField();
        
        Button but1=new Button("Add Seller");
        but1.setOnAction(e -> {
            try {
                String username = nameField.getText();
                String password = passwordfield.getText();
                
                for(int n=0; n < SystemManager.getUserNo(); n++){
                    if(SystemManager.getUser(n) instanceof Vendor ){
                        if(SystemManager.getUser(n).getName().equals (username)){
                            Login_page.showAlert("This user is already a vendor");
                            return;
                        }}}
                SystemManager.addUser(new Vendor(username,password));
                AdminPage adminPage=new AdminPage();
                adminPage.show();
                close();
                //AdminPage.start(Primarystage);
            } catch (EmptyStringException ex) {
                Login_page.showAlert("Username and password fields cannot be empty");
            }
            
        });

        GridPane pane=new GridPane();
        pane.add(text1,0,0);
        pane.add(text2,0,1);
        pane.add(nameField,1,0);
        pane.add(passwordfield,1,1);
        pane.add(but1,0,4);
        pane.setHgap(10);
        pane.setVgap(5);
        pane.setAlignment(Pos.CENTER);
         
        // Scene scene=new Scene(pane, 300,300);
         Scene scene =new Scene(pane,300,200);
         
         setScene(scene);
         setTitle("Add Seller");
    }
    

    
    /*
    public static void main(String[] args) {
        
    }
    */

}

