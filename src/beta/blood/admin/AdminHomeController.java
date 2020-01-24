/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.admin;

import beta.blood.Handler;
import beta.blood.auth.LoginService;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;

/**
 * FXML Controller class
 *
 * @author Aaron
 */
public class AdminHomeController implements Initializable {

    @FXML
    Tab dash;
    @FXML
    Tab add;
    @FXML
    Tab sysrep;
    @FXML
    Tab modrep;
    @FXML
    Label welcome;

    @FXML
    private void changeDetails() {
        Handler.setScene(getClass(), "Change Details", "AdminChangeDetails.fxml");
    }

    @FXML
    private void adminModifyRepos() {
        Handler.setScene(getClass(), "Modify Repository", "AdminModifyRepos.fxml");
    }

    @FXML
    private void adminAddUser() {
        Handler.setScene(getClass(), "Add User/recipient", "AdminAddUser.fxml");
    }

    @FXML
    private void adminRequestReport() {
        Handler.setScene(getClass(), "Report Request", "AdminRequestReport.fxml");
    }

    @FXML
    private void logout() throws IOException {
        int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out", "Logout", YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            Handler.setScene(getClass(), "Beta Blood", "/beta/blood/auth/Login.fxml");
            
        }
    }
    
    public void openWebpage() {
        try {
            Desktop.getDesktop().browse(new URL("https://sanbs.org.za/").toURI());
        } catch (IOException | URISyntaxException e) {
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        welcome.setText("Welcome " + LoginService.getCurrentUser().getName() + " " + LoginService.getCurrentUser().getSurname());
        
        add.setContent(Handler.loadFxml(getClass(), "AdminAddUser.fxml"));
        sysrep.setContent(Handler.loadFxml(getClass(), "AdminRequestReport.fxml"));
        modrep.setContent(Handler.loadFxml(getClass(), "AdminModifyRepos.fxml"));
        

    }
}
