/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.nurse;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

 


public class VerifyBloodController implements Initializable {

    @FXML
    ComboBox<String> combobox;
    
    
    ObservableList<String> types = FXCollections.observableArrayList(
        "A+",
        "A-",    
        "B+",
        "B-",
        "AB+",
        "AB-",
        "O+",    
        "O-"    
    );
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      combobox.setItems(types);
    }    
    
}
