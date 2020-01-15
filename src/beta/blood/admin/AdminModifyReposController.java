/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.admin;

import static beta.blood.Helper.StaticData.BLOOD_TYPES;
import static beta.blood.Helper.StaticData.BRANCH_OPTIONS;
import static beta.blood.Helper.createTableColumn;
import beta.blood.model.Employee;
import beta.blood.model.Recipient;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class AdminModifyReposController implements Initializable {

    @FXML
    ComboBox adminComboBox,
            nurseComboBox,
            bloodComboBox;

    @FXML
    TextField bloodamount;

    @FXML
    TableView<Employee> adminTableView;
    @FXML
    TableView<Employee> nurseTableView;
    @FXML
    TableView<Recipient> recipientTableView;

    //Data for the admin listview
    private final ObservableList<Employee> adminList = FXCollections
            .observableArrayList();

    //Data for the nurse listview
    private final ObservableList<Employee> nurseList = FXCollections
            .observableArrayList();

    //Data for the recipient listview
    private final ObservableList<Recipient> recipientList = FXCollections
            .observableArrayList();

    @FXML
    public void deleteAdmin() {
        adminTableView.getSelectionModel().getSelectedItems().forEach((admin) -> {
            adminList.remove(admin);
        });
    }


    @FXML
    public void deleteNurse() {
        nurseTableView.getSelectionModel().getSelectedItems().forEach((nurse) -> {
            nurseList.remove(nurse);
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

        bloodComboBox.setItems(BLOOD_TYPES);
        adminComboBox.setItems(BRANCH_OPTIONS);
        nurseComboBox.setItems(BRANCH_OPTIONS);

        Employee.getAll((employees) -> {
            employees.forEach((employee) -> {
                if (employee != null) {
                    switch (employee.getPosition()) {
                        case 0:
                            adminList.add(employee);
                            break;
                        case 1:
                            nurseList.add(employee);
                            break;
                    }
                }
            });
        });

        Recipient.getAll((recipients) -> {
            recipients.forEach((recipient) -> {
                if (recipient != null) {
                    recipientList.add(recipient);
                }
            });
        });

        for (Field field : Employee.class.getDeclaredFields()) {
            switch (field.getName()) {
                case "employeeId":
                case "name":
                case "surname":
                case "telephone":
                case "branch":
                    adminTableView.getColumns().add(createTableColumn(field));
                    nurseTableView.getColumns().add(createTableColumn(field));
                    break;
            }
        }

        for (Field field : Recipient.class.getDeclaredFields()) {
            switch (field.getName()) {
                case "DEFAULT_ID":
                    break;
                default:
                    recipientTableView.getColumns().add(createTableColumn(field));
                    break;
            }
        }
        
        
        nurseTableView.setItems(nurseList);
        adminTableView.setItems(adminList);
        recipientTableView.setItems(recipientList);
        adminTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        nurseTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        recipientTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
}
