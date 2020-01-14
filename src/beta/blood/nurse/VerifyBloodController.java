/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.nurse;

import beta.blood.Handler;
import beta.blood.Handler.Function;
import static beta.blood.Handler.QueryType.RESULT;
import static beta.blood.Helper.StaticData.BLOOD_TYPES;
import beta.blood.model.Blood;
import beta.blood.model.Donor;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class VerifyBloodController implements Initializable {

    @FXML
    ComboBox<String> BloodTypes;

    @FXML
    ListView<Blood> donorBloodListView;

    ObservableList<Blood> bloodList = FXCollections.observableArrayList();

    @FXML
    private void back() {
        Handler.setScene(getClass(), "Nurse Home", "/beta/blood/nurse/NurseHome.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Blood.getByQuery("SELECT * FROM `blood` WHERE `blood`.`Type` = 'UN'",
                RESULT, (Function<ResultSet>) (result)
                -> bloodList.addAll(Blood.resultToList(result)));
        
        donorBloodListView.getItems().addAll(bloodList);
        BloodTypes.setItems(BLOOD_TYPES);
    }

}
