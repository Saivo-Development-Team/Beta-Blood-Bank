package beta.blood.nurse;

import beta.blood.Helper;
import static beta.blood.Helper.createTableColumn;
import beta.blood.model.Donor;
import beta.blood.model.Employee;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class NurseExistingDonorController implements Initializable {

    @FXML
    TextField id;
    @FXML
    Button search;
    @FXML
    TableView existingDonorTableView;

    ObservableList<Donor> donorList = FXCollections.observableArrayList();

    @FXML
    public void donorSearch() {

        String donorId = id.getText();
                
        //Donor.getById(Long.parseLong(donorId), e -> donor = e);

        Donor.getAll((donors) -> {
            donors.forEach((donor) -> {
                if (donor != null) {
                    if (donor.getDonorID().equals(donorId)) {
                        donorList.add(donor);
                    }
                } else {
                    System.out.println("Donor does not exist");
                }
            });
        });
    }
    
    @FXML
    public void donateBlood() {
        Helper.popup();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (Field field : Donor.class.getDeclaredFields()) {
            switch (field.getName()) {
                case "DonorId":
                case "Name":
                case "Surname":
                case "Address":
                case "Gender":
                case "Age":
                    break;
                default:
                    existingDonorTableView.getColumns().add(createTableColumn(field));
                    break;
            }
        }
        
        donorSearch();
    }

}
