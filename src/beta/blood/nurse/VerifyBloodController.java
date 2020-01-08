/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.nurse;

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
 * @author Admin
 */
public class VerifyBloodController implements Initializable {

    @FXML
    ComboBox<String> BloodTypes;

    ObservableList<String> BloodType = FXCollections.observableArrayList(
            "A+", "B+", "A-", "B-", "AB+", "AB-", "O+", "O-"
    );

    @FXML
    private void back() {
        Handler.setScene(getClass(), "Nurse Home", "/beta/blood/nurse/NurseHome.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BloodTypes.setItems(BloodType);
    }

}
