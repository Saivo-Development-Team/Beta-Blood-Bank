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
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.util.Pair;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class VerifyBloodController implements Initializable {

    @FXML
    ComboBox<String> BloodTypes;

    @FXML
    ListView<String> donorBloodListView;

    ObservableSet<Pair<Donor, Blood>> bloodDonors = FXCollections.observableSet(new HashSet());

    @FXML
    private void back() {
        Handler.setScene(getClass(), "Nurse Home", "/beta/blood/nurse/NurseHome.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Blood.getByQuery("SELECT * FROM `blood` WHERE `blood`.`Type` = 'UN'",
                RESULT, (Function<ResultSet>) (result)
                -> Blood.resultToList(result).forEach((unit) -> {
                    if (unit != null) {
                        if (!bloodDonors.stream()
                                .findAny()
                                .filter((pair)
                                        -> pair
                                        .getValue()
                                        .getBloodID() == unit.getBloodID())
                                .isPresent()) {
                            Donor.getById(unit.getOfferedBy(), (donor) -> {
                                if (donor != null) {
//                                    bloodDonors.removeIf((pair)
//                                            -> Objects.equals(
//                                                    pair.getKey().getDonorID(),
//                                                    donor.getDonorID())
//                                    );
                                    bloodDonors.add(new Pair(donor, unit));
                                }
                            });
                        }
                    }
                })
        );

        donorBloodListView.getItems().addAll(bloodDonors.stream()
                .map((pair) -> String.format("[%s %s] [Id: %d] [Gender: %s]",
                pair.getKey().getName(),
                pair.getKey().getSurname(),
                pair.getKey().getDonorID(),
                pair.getKey().getGender()))
                .collect(Collectors.toList()));
        BloodTypes.setItems(BLOOD_TYPES);
    }

}
