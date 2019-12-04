/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.nurse;

import beta.blood.database.DatabaseService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class NurseHomeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
private void addDonor() {
    
}
    
   
@FXML
private void logout(ActionEvent event) throws IOException{
    int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out", "Logout", YES_NO_OPTION);
    if(result == JOptionPane.YES_OPTION){
        Parent nurseHomeParent = FXMLLoader.load(getClass().getResource("/beta/blood/auth/Login.fxml"));
        Scene nurseHomeScene = new Scene(nurseHomeParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(nurseHomeScene);
        window.show();
    }
    
}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
