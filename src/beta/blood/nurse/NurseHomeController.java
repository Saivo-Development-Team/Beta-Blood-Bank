/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.nurse;

import beta.blood.Handler;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;

/**
 * FXML Controller class
 *
 * @author Admin
 */


public class NurseHomeController implements Initializable {
    
    @FXML
    private void logout() throws IOException {
        int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out", "Logout", YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            Handler.setScene(getClass(), "Login Screen", "/beta/blood/auth/Login.fxml");
        }
    }

    @FXML
    private void donorNavigation() {
        Handler.setScene(getClass(), "Verify Blood", "/beta/blood/nurse/NurseAddDonor.fxml");
    }

    @FXML
    private void verifyBloodNavigation() {
        Handler.setScene(getClass(), "Verify Blood", "/beta/blood/nurse/VerifyBlood.fxml");
    }
    @FXML
    private void changePassword() {
        Handler.setScene(getClass(), "Verify Blood", "/beta/blood/nurse/NurseChangePassword.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }

}
