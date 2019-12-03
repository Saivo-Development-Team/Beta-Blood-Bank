/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.auth;

import beta.blood.database.DatabaseService;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
    DatabaseService dbService = DatabaseService.service();
    @FXML TextField number;
    @FXML PasswordField password;
    @FXML Label message;

                
    @FXML
    private void login(ActionEvent event) throws Exception {
        
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
                    Parent adminHomeParent = FXMLLoader.load(getClass().getResource("/beta/blood/admin/adminHome.fxml"));
                    Scene adminHomeScene = new Scene(adminHomeParent);
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(adminHomeScene);
                    window.setTitle("Admin Home");
                    window.show();
                    
                } else if (userType.equals("0")){
                    System.out.println("Welcome nurse");
                    Parent nurseHomeParent = FXMLLoader.load(getClass().getResource("/beta/blood/nurse/nurseHome.fxml"));
                    Scene nurseHomeScene = new Scene(nurseHomeParent);
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(nurseHomeScene);
                    window.setTitle("Nurse Home");
                    window.show();
                }
        } else{
            conn.close();
            JOptionPane.showMessageDialog(null,"UserName Or Password Invalid");
        }
            
        }catch(SQLException ex){
            System.out.println("Unable to connect to database");
        }
    }
    
    public void changeScreenAdmin(ActionEvent event) throws IOException
    {
        
    }
        
    @FXML
    private void help() {
        JOptionPane.showMessageDialog(null, "Enter employee number and password provided by your administrator", "Help me", OK_CANCEL_OPTION);
    }
    
    @FXML
    public void openWebpage() {
        try {
            Desktop.getDesktop().browse(new URL("https://sanbs.org.za/").toURI());
        } catch (IOException | URISyntaxException e) {}
    }
    
    @FXML
    private void exit() {
        int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Close Application", OK_CANCEL_OPTION);
        if(result == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
        
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
