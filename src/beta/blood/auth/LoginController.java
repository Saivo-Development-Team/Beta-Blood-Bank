/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.auth;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author perso
 */
public class LoginController implements Initializable {
    @FXML TextField number;
    @FXML PasswordField password;
            
    @FXML
    private void login(){        
        if("admin".equals(number.getText())){
                if("admin".equals(password.getText())){
                    System.out.println("login");
                }else{
                    System.out.println("incorrect password");
                }
            }else{
                System.out.println("incorrect username or password");
            }   
    }
    
    @FXML
    private void exit() {
        System.exit(0);
    }
    
    @FXML
    private void help() {
        JOptionPane.showMessageDialog(null, "enter");
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
