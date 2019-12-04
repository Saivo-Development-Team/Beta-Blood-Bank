/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.auth;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.OK_CANCEL_OPTION;

/**
 * FXML Controller class
 *
 * @author perso
 */
public class LoginController implements Initializable {

    LoginService loginService = new LoginService();
    @FXML
    TextField number;
    @FXML
    PasswordField password;
    @FXML
    Label message;
    private int access;

    @FXML
    private void login() throws Exception {

        access = loginService.checkDetails(number.getText(), password.getText());

        switch (access) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            default:
                break;
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
        } catch (IOException | URISyntaxException e) {
        }
    }

    @FXML
    private void exit() {
        int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Close Application", OK_CANCEL_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
