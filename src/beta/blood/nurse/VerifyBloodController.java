/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.nurse;

import beta.blood.Handler;
import beta.blood.Handler.Function;
import static beta.blood.Handler.QueryType.RESULT;
import static beta.blood.Handler.QueryType.UPDATE;
import static beta.blood.Helper.StaticData.BLOOD_TYPES;
import beta.blood.model.Blood;
import beta.blood.model.Donor;
import java.net.URL;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    ObservableList<String> donations = FXCollections.observableArrayList();

    ObservableSet<Pair<Donor, Blood>> bloodDonors = FXCollections.observableSet(new HashSet());

    @FXML
    private void back() {
        Handler.setScene(getClass(), "Nurse Home", "/beta/blood/nurse/NurseHome.fxml");
    }

    @FXML
    public void changeBloodType() {
        String type = BloodTypes.getSelectionModel().getSelectedItem();
        donorBloodListView.getSelectionModel().getSelectedItems().forEach((string) -> {
            bloodDonors.forEach((pair) -> {
                String id = pair.getKey().getDonorID();
                if (type != null) {
                    if (string.contains(id)) {
                        Blood.getByQuery(String.format(
                                "UPDATE `betablooddatabase`.`blood` "
                                + "SET `Type` = '%s' "
                                + "WHERE `blood`.`OfferedBy` = %d",
                                type, Long.parseLong(id)), UPDATE, null);
                        donations.remove(string);
                    }
                } else {

                }
            });
        });
        donorBloodListView.refresh();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Blood.getByQuery("SELECT * FROM `blood` WHERE `blood`.`Type` = 'UN'",
                RESULT, (Function<ResultSet>) (result)
                -> Blood.resultToList(result).forEach((unit) -> {
                    if (unit != null) {
                        Donor.getById(unit.getOfferedBy(), (donor) -> {
                            if (donor != null) {
                                bloodDonors.removeIf((pair)
                                        -> Objects.equals(pair
                                                .getKey().getDonorID(),
                                                donor.getDonorID())
                                );
                                bloodDonors.add(new Pair(donor, unit));
                            }
                        });
                    }
                })
        );

        bloodDonors.forEach((pair) -> {
            Donor donor = pair.getKey();
            donations.add(String.format("[DonorId: %s][Fullname: %s %s][Gender: %s]",
                    donor.getDonorID(),
                    donor.getName(),
                    donor.getSurname(),
                    donor.getGender()
            ));
        });
        
        donorBloodListView.setItems(donations);
        BloodTypes.setItems(BLOOD_TYPES);
    }

}
