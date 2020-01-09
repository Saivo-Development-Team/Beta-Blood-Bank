/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.nurse;

import beta.blood.Handler;
import beta.blood.database.DatabaseService;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class VerifyBloodController implements Initializable {

    @FXML
    TableView<beta.blood.model.TableModel.donorTable> donorTable;
    @FXML
    TableColumn<beta.blood.model.TableModel.donorTable, String> col_id_ad;
    @FXML
    TableColumn<beta.blood.model.TableModel.donorTable, String> col_name_ad;
    @FXML
    TableColumn<beta.blood.model.TableModel.donorTable, String> col_surname_ad;
    
     //Data for the donor Tableview
    private final ObservableList<beta.blood.model.TableModel.donorTable> donorList = FXCollections
            .observableArrayList();
    
    
    
    
    @FXML
    ComboBox<String> BloodTypes;
    
    
    ObservableList<String> BloodType = FXCollections.observableArrayList(
            "A+","B+","A-","B-","AB+","AB-","O+","O-"
    );
    
    @FXML
private void back() {
    Handler.setScene(getClass(), "Nurse Home", "/beta/blood/nurse/NurseHome.fxml");
}  

    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      BloodTypes.setItems(BloodType);
      
      //DONOR CONTENT            
            try {
            ResultSet rs = DatabaseService.service().executeResultQuery("SELECT Name, Surname, DonorID FROM `donor` ");
            
                while(rs.next()) {
                    donorList.add(new beta.blood.model.TableModel.donorTable(rs.getString("Name"), rs.getString("Surname"), 
                            rs.getString("DonorID")));
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(VerifyBloodController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            col_name_ad.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_surname_ad.setCellValueFactory(new PropertyValueFactory<>("surname"));
            col_id_ad.setCellValueFactory(new PropertyValueFactory<>("DonorID"));
            
            donorTable.setItems(donorList);
            
      
      
    }    
    
}
