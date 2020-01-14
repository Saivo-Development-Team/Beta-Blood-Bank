/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.admin;

import beta.blood.Handler;
import static beta.blood.Helper.isNotEmpty;
import beta.blood.auth.LoginService;
import beta.blood.model.Employee;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.OK_CANCEL_OPTION;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AdminChangeDetailsController implements Initializable {

  
    @FXML
    TextField curpass;
    @FXML
    TextField newpass;

    private String employeeId =LoginService.getLoggedInUser().getEmployeeId();
    private String password;
    
    @FXML
    private void back() {
        Handler.setScene(getClass(), "Admin Home", "/beta/blood/admin/AdminHome.fxml");
        Handler.getWindow().setMaximized(true);
    }

    @FXML
    private void change() {
    int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to change your password?", "Alert", OK_CANCEL_OPTION);
        if(result == JOptionPane.OK_OPTION)
        {
            if (isPasswordChangeCompleted()) {
                Employee.update(employeeId ,newpass.getText());
               JOptionPane.showMessageDialog(null, "Password Changed");
             }
            Handler.setScene(getClass(), "Admin Home", "/beta/blood/admin/AdminHome.fxml");
            Handler.getWindow().setMaximized(true); 
        }
        
    Handler.setScene(getClass(), "Admin Home", "/beta/blood/admin/AdminHome.fxml");
    Handler.getWindow().setMaximized(true);  
    }

    private boolean isPasswordChangeCompleted() {
        return (setPassword());
    }
    
     private boolean setPassword() {
        password = newpass.getText();
        return isNotEmpty(password);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
