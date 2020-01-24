package beta.blood.nurse;

import beta.blood.Handler;
import beta.blood.Helper;
import static beta.blood.Helper.createTableColumn;
import static beta.blood.Helper.isNotEmpty;
import static beta.blood.auth.LoginService.getCurrentUser;
import beta.blood.model.Blood;
import static beta.blood.model.Blood.DEFULT_ID;
import static beta.blood.model.Blood.DEFULT_QUANTITY;
import static beta.blood.model.Blood.DEFULT_TYPE;
import beta.blood.model.Donor;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class NurseExistingDonorController implements Initializable {

    @FXML
    TextField id;
    @FXML
    TableView<Donor> existingDonorTableView;

    ObservableList<Donor> donorList = FXCollections.observableArrayList();

    @FXML
    public void donorSearch() {
        String donorId = id.getText();
        if (isNotEmpty(donorId)) {
            Donor.getById(Long.parseLong(donorId), donor -> {
                boolean isDonorInList = donorList.stream()
                        .findAny().filter((it) -> it
                        .getDonorId()
                        .equals(donorId))
                        .isPresent();
                if (!isDonorInList) {
                    donorList.add(donor);
                    id.clear();
                }
            });
        }
        existingDonorTableView.refresh();
    }

    @FXML
    public void donateBlood() {
        Donor newDonor = existingDonorTableView.getSelectionModel().getSelectedItem();

        Blood.insert(new Blood(DEFULT_ID, DEFULT_QUANTITY, Long.parseLong(
                newDonor.getDonorId()), DEFULT_TYPE,
                getCurrentUser().getEmployeeId()));
        Helper.popup();
    }

    @FXML
    public void back() {
        Handler.setScene(getClass(), "Nurse Home", "NurseHome.fxml");
        Handler.getWindow().setMaximized(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (Field field : Donor.class.getDeclaredFields()) {
            existingDonorTableView.getColumns().add(createTableColumn(field));
        }

        existingDonorTableView.setItems(donorList);
    }

}
