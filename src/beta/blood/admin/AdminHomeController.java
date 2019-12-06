/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.admin;

import beta.blood.Handler;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AdminHomeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private void adminModifyRepos() {
        Handler.setScene(getClass(), "Add nurse", "/beta/blood/admin/AdminModifyRepos.fxml");
        Handler.getWindow().setMaximized(true);
    }
    
    @FXML
    private void adminAddUser() {
        Handler.setScene(getClass(), "Add nurse", "/beta/blood/admin/AdminAddUser.fxml");
        Handler.getWindow().setMaximized(true);
    }    
    
     @FXML
    private void adminRequestReport() {
        Handler.setScene(getClass(), "Add nurse", "/beta/blood/admin/AdminRequestReport.fxml");
        Handler.getWindow().setMaximized(true);
    }    
    
    @FXML
    private void logout() throws IOException{
    int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out", "Logout", YES_NO_OPTION);
    if(result == JOptionPane.YES_OPTION){
        Handler.setScene(getClass(), "Beta Blood", "/beta/blood/auth/Login.fxml");
    }
    }    
    
    @FXML
    private void changeDetails() {
        Handler.setScene(getClass(), "Verify Blood", "/beta/blood/admin/AdminChangeDetails.fxml");
        Handler.getWindow().setMaximized(true);
    }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
