/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.admin;

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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    
     
    //ADMIN CONTENT
    @FXML
    TableView<beta.blood.model.TableModel.adminTable> admintable;
    @FXML
    TableColumn<beta.blood.model.TableModel.adminTable, String> col_id_ad;
    @FXML
    TableColumn<beta.blood.model.TableModel.adminTable, String> col_name_ad;
    @FXML
    TableColumn<beta.blood.model.TableModel.adminTable, String> col_surname_ad;
    @FXML
    TableColumn<beta.blood.model.TableModel.adminTable, String> col_branch_ad;
    
    
    //NURSE CONTENT
    @FXML
    TableView<beta.blood.model.TableModel.nurseTable> nursetable;
    @FXML
    TableColumn<beta.blood.model.TableModel.nurseTable, String> col_id_nu;
    @FXML
    TableColumn<beta.blood.model.TableModel.nurseTable, String> col_name_nu;
    @FXML
    TableColumn<beta.blood.model.TableModel.nurseTable, String> col_surname_nu;
    @FXML
    TableColumn<beta.blood.model.TableModel.nurseTable, String> col_branch_nu;
    
        
    
    
    //Data for modrepo combo box of the admin and nurse
    private final ObservableList<String> branchList = FXCollections
            .observableArrayList(
                    "Cape Town", "Durban", "Johanessburg", "Langebaan",
                    "Port Elizabeth",
                    "Pretoria"
            );
    
    //Data for modrepo combo box of the blood
    private final ObservableList<String> bloodList = FXCollections
            .observableArrayList(
                    "A+","A-","B+","B-","O+","O-","AB+","AB-"
            );

    //Data for the admin listview
    private final ObservableList<beta.blood.model.TableModel.adminTable> adminList = FXCollections
            .observableArrayList();
    
    //Data for the nurse listview
    private final ObservableList<beta.blood.model.TableModel.nurseTable> nurseList = FXCollections
            .observableArrayList(
                    
            );
    
    //Data for the recipient listview
    private final ObservableList<String> recipientList = FXCollections
            .observableArrayList(
                    
            );
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            //setting content for admin and nurse combo boxes
            modadmcombo.setItems(branchList);
            modnurcombo.setItems(branchList);
            //setting content for blood combo box
            modbloodcombo.setItems(bloodList);
            
            //ADMIN CONTENT            
            try {
            ResultSet rs = DatabaseService.service().executeResultQuery("SELECT `EmployeeID`, `Name`, `Surname`, `Branch` FROM `employee` WHERE `Position` = 0");
            
                while(rs.next()) {
                    adminList.add(new beta.blood.model.TableModel.adminTable(rs.getString("EmployeeID"), rs.getString("Name"), 
                            rs.getString("Surname"), rs.getString("Branch")));
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(AdminModifyReposController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            col_id_ad.setCellValueFactory(new PropertyValueFactory<>("empid"));
            col_name_ad.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_surname_ad.setCellValueFactory(new PropertyValueFactory<>("surname"));
            col_branch_ad.setCellValueFactory(new PropertyValueFactory<>("branch"));
        
            admintable.setItems(adminList);
            
            
            //NURSE CONTENT
            try {
            ResultSet rs = DatabaseService.service().executeResultQuery("SELECT `EmployeeID`, `Name`, `Surname`, `Branch` FROM `employee` WHERE `Position` = 1");
            
                while(rs.next()) {
                    nurseList.add(new beta.blood.model.TableModel.nurseTable(rs.getString("EmployeeID"), rs.getString("Name"), 
                            rs.getString("Surname"), rs.getString("Branch")));
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(AdminModifyReposController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            col_id_nu.setCellValueFactory(new PropertyValueFactory<>("empid"));
            col_name_nu.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_surname_nu.setCellValueFactory(new PropertyValueFactory<>("surname"));
            col_branch_nu.setCellValueFactory(new PropertyValueFactory<>("branch"));
        
            nursetable.setItems(nurseList);
        
    }
}
