/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.auth;

import beta.blood.Handler;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
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

    @FXML
    TextField number;
    @FXML
    PasswordField password;
    @FXML
    Label message;

    @FXML
    private void login(ActionEvent event) throws Exception {
        Stage window = Handler.getStage(event);

        switch (LoginService.checkDetails(number.getText(), password.getText())) {
            case 0:
                Handler.changeScene(getClass(), event, "Admin Home", "/beta/blood/admin/adminHome.fxml");
                break;
            case 1:
                Handler.changeScene(getClass(), event, "Nurse Home", "/beta/blood/nurse/nurseHome.fxml");
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "UserName Or Password Invalid");
                break;
            default:
                break;
        }
    }

    public void changeScreenAdmin(ActionEvent event) throws IOException {

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
