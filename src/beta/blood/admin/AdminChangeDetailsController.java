/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.admin;

import beta.blood.Handler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Admin
 */


public class AdminChangeDetailsController implements Initializable {

    @FXML
    TextField username;
    @FXML
    TextField curpass;
    @FXML
    TextField newpass;

    @FXML
    private void back() {
        Handler.setScene(getClass(), "Admin Home", "/beta/blood/admin/AdminHome.fxml");
    }

    @FXML
    private void change() {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
