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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    
    @FXML
    TextField   bloodamount;

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

    //RECIPIENT CONTENT
    @FXML
    TableView<beta.blood.model.TableModel.recipientTable> recipienttable;
    @FXML
    TableColumn<beta.blood.model.TableModel.recipientTable, String> col_id_rec;
    @FXML
    TableColumn<beta.blood.model.TableModel.recipientTable, String> col_name_rec;
    @FXML
    TableColumn<beta.blood.model.TableModel.recipientTable, String> col_address_rec;
    @FXML
    TableColumn<beta.blood.model.TableModel.recipientTable, String> col_tel_rec;
    @FXML
    TableColumn<beta.blood.model.TableModel.recipientTable, String> col_email_rec;

    //Data for modrepo combo box of the admin and nurse
    private final ObservableList<String> branchList = FXCollections
            .observableArrayList(
                    "Cape Town", "Durban", "Johanessburg", "Langebaan",
                    "Port Elizabeth", "Pretoria"
            );

    //Data for modrepo combo box of the blood
    private final ObservableList<String> bloodList = FXCollections
            .observableArrayList(
                    "A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"
            );

    //Data for the admin listview
    private final ObservableList<beta.blood.model.TableModel.adminTable> adminList = FXCollections
            .observableArrayList();

    //Data for the nurse listview
    private final ObservableList<beta.blood.model.TableModel.nurseTable> nurseList = FXCollections
            .observableArrayList();

    //Data for the recipient listview
    private final ObservableList<beta.blood.model.TableModel.recipientTable> recipientList = FXCollections
            .observableArrayList();
    
    @FXML
    public void changeAdminBranch() {
        
    }

    @FXML
    public void deleteAdmin() {
        ObservableList<beta.blood.model.TableModel.adminTable> emps = admintable.getSelectionModel().getSelectedItems();
        emps.forEach((emp) -> {
            String sql = ("DELETE FROM employee WHERE employeeID = '" + emp.getEmpid() + "'");
            DatabaseService.service().executeUpdateQuery(sql);
            adminList.remove(emp);
        });
    }
    
    @FXML
    public void changeNurseBranch() {
        
    }
    
    @FXML
    public void deleteNurse() {
        ObservableList<beta.blood.model.TableModel.nurseTable> emps = nursetable.getSelectionModel().getSelectedItems();
        emps.forEach((emp) -> {
            String sql = ("DELETE FROM employee WHERE employeeID = '" + emp.getEmpid() + "'");
            DatabaseService.service().executeUpdateQuery(sql);
            nurseList.remove(emp);
        });
    }
    
    @FXML
    public void addBlood() {
        
    }
    
    @FXML
    public void deleteBlood() {
        
    }
    
    @FXML
    public void changeRecTel() {
        
    }
    
    @FXML
    public void changeRecEmail() {
        
    }
    
    @FXML
    public void changeRecAddress() {
        
    }

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

            while (rs.next()) {
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

        admintable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        admintable.setItems(adminList);

        //NURSE CONTENT
        try {
            ResultSet rs = DatabaseService.service().executeResultQuery("SELECT `EmployeeID`, `Name`, `Surname`, `Branch` FROM `employee` WHERE `Position` = 1");

            while (rs.next()) {
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

        nursetable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        nursetable.setItems(nurseList);

        //RECIPIENT CONTENT
        try {
            ResultSet rs = DatabaseService.service().executeResultQuery("SELECT `RecipientID`, `Name`, `Address`, `Telephone`, `Email` FROM `recipient`");

            while (rs.next()) {
                recipientList.add(new beta.blood.model.TableModel.recipientTable(rs.getString("RecipientID"), rs.getString("Name"),
                        rs.getString("Address"), rs.getString("Telephone"), rs.getString("Email")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminModifyReposController.class.getName()).log(Level.SEVERE, null, ex);
        }

        col_id_rec.setCellValueFactory(new PropertyValueFactory<>("empid"));
        col_name_rec.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_address_rec.setCellValueFactory(new PropertyValueFactory<>("address"));
        col_tel_rec.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        col_email_rec.setCellValueFactory(new PropertyValueFactory<>("email"));

        recipienttable.setItems(recipientList);
    }
}
