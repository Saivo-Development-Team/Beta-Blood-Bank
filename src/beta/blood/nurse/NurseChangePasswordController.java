/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.nurse;

import beta.blood.Handler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class NurseChangePasswordController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
@FXML
private void back() {
    
    Handler.setScene(getClass(), "Verify Blood", "NurseHome.fxml");
}    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
