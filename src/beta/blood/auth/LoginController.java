/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.auth;

import java.awt.Desktop;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
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
             PreparedStatement ps;
    try{
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/betablooddatabase","root","");
        ps = conn.prepareStatement("SELECT * FROM employee WHERE employeeNumber = ? AND Password = ?");
        ps.setString(1, number.getText());
        ps.setString(2, password.getText());
        rs = ps.executeQuery();

        if(rs.next()){
                String userType = rs.getString("Admin");
                if (userType.equals("1")){
                    System.out.println("Welcome admin");
                    Parent root2 = FXMLLoader.load(getClass().getResource("Login.fxml"));
                    Scene sceneAdminHome = new Scene(root2);
                    //stage.setScene(sceneAdminHome);
                } else if (userType.equals("0")){
                    System.out.println("Welcome nurse");
                }
        } else{
            conn.close();
            JOptionPane.showMessageDialog(null,"UserName Or Password Invalid");
        }
            
        }catch(SQLException ex){
            System.out.println("Unable to connect");
        }
    }
        
    @FXML
    private void help() {
        JOptionPane.showMessageDialog(null, "Enter employee number and password provided by your administrator", "Help me", OK_CANCEL_OPTION);
    }
    
    @FXML
    public void openWebpage() {
        try {
            Desktop.getDesktop().browse(new URL("https://sanbs.org.za/").toURI());
        } catch (Exception e) {}
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