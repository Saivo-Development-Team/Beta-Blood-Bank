/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.admin;

import beta.blood.Handler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class AdminAddUserController implements Initializable {

     @FXML
    ComboBox<String> branch;
    
    ObservableList<String> branches = FXCollections.observableArrayList(
        "Cape Town",
        "Durban",
        "Johanessburg",
        "Langebaan",
        "Port Elizabeth",
        "Pretoria");
    
    
    
    @FXML
    private void back() {
    Handler.setScene(getClass(), "Verify Blood", "/beta/blood/admin/AdminHome.fxml");
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        branch.setItems(branches);
    }    
    
}
