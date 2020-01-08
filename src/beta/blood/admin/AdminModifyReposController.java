/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.admin;

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
public class AdminModifyReposController implements Initializable {
    
    @FXML
    ComboBox    modadmcombo,
                modnurcombo,
                modbloodcombo;
    
    private final ObservableList<String> branchList = FXCollections
            .observableArrayList(
                    "Cape Town", "Durban", "Johanessburg", "Langebaan",
                    "Port Elizabeth",
                    "Pretoria"
            );
    
    private final ObservableList<String> bloodList = FXCollections
            .observableArrayList(
                    "A+","A-","B+","B-","O+","O-","AB+","AB-"
            );

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modadmcombo.setItems(branchList);
        modnurcombo.setItems(branchList);
        modbloodcombo.setItems(bloodList);
    }
}
