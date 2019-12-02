/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.auth;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.OK_CANCEL_OPTION;

/**
 * FXML Controller class
 *
 * @author perso
 */
public class LoginController implements Initializable {
    @FXML TextField number;
    @FXML PasswordField password;
    @FXML Label message;
                
    @FXML
    private void login() throws Exception {
        
             Connection conn;
             Statement s;
             ResultSet rs;
    try{
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/betablooddatabase", "root", "");
            System.out.println("Connected");
            
            s = conn.createStatement();
            rs = s.executeQuery("SELECT * FROM employee");
            
            while(rs.next()){
                if(rs.getString(1).equals(number.getText())){
                if(rs.getString(4).equals(password.getText())){
                    message.setText("Logged in");                    
                }else{
                    message.setText("Incorrect password");
                }
                }else{
                    message.setText("Incorrect username or password");
                }   
            }

    } catch (SQLException ex) {
            System.out.println("Connection to database failed");
        }
    }
    
    @FXML
    private void help() {
        JOptionPane.showMessageDialog(null, "Enter employee number and password provided by your administrator", "Help me", INFORMATION_MESSAGE);
    }
    
    @FXML
    private void exit() {
        int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Close Application", OK_CANCEL_OPTION);
        if(result == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
        
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
