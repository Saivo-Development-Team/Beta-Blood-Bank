/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.admin;

import beta.blood.Handler.Function;
import static beta.blood.Handler.QueryType.RESULT;
import static beta.blood.Helper.StaticData.BLOOD_TYPES;
import static beta.blood.Helper.StaticData.BRANCH_OPTIONS;
import static beta.blood.Helper.createTableColumn;
import beta.blood.model.Blood;
import beta.blood.model.Employee;
import beta.blood.model.Recipient;
import java.lang.reflect.Field;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class AdminModifyReposController implements Initializable {

    @FXML
    ComboBox<String> adminComboBox;
    @FXML
    ComboBox<String> nurseComboBox;
    @FXML
    ComboBox<String> bloodComboBox;

    @FXML
    PieChart currentBloodCount;

    @FXML
    TextField bloodamount;
    @FXML
    TextField recChangeTel;
    @FXML
    TextField recChangeEmail;
    @FXML
    TextArea recChangeAddress;

    ObservableList<String> bloodTypes = FXCollections
            .observableArrayList();
    int[] bloodTypeCount = {
        0, 0, 0, 0, 0, 0, 0, 0, 0
    };

    @FXML
    TableView<Employee> adminTableView;
    @FXML
    TableView<Employee> nurseTableView;
    @FXML
    TableView<Recipient> recipientTableView;

    ObservableList<Data> bloodData = FXCollections.observableArrayList();

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
    public void refresh() {
        adminList.clear();
        nurseList.clear();
        recipientList.clear();

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

        recipientTableView.refresh();
        nurseTableView.refresh();
        adminTableView.refresh();
    }

    @FXML
    public void deleteAdmin() {
        adminTableView.getSelectionModel().getSelectedItems().forEach((admin) -> {
            Employee.delete(admin.getEmployeeId());
            adminList.remove(admin);
        });
        adminTableView.refresh();
    }

    @FXML
    public void deleteNurse() {
        nurseTableView.getSelectionModel().getSelectedItems().forEach((nurse) -> {
            Employee.delete(nurse.getEmployeeId());
            nurseList.remove(nurse);
        });
        nurseTableView.refresh();
    }

    @FXML
    public void deleteBlood() {
        int amount = Integer.parseInt(bloodamount.getText());
        String bloodType = bloodComboBox.getSelectionModel().getSelectedItem();
        Blood.getByQuery(
                String.format(
                        "SELECT * FROM `blood` WHERE `blood`.`Type` = '%s'", bloodType),
                RESULT, (Function<ResultSet>) (result) -> {
                    ArrayList<Blood> list = Blood.resultToList(result);
                    System.out.println(Arrays.toString(list.toArray()));
                    System.out.println(list.size());
                    System.out.println(amount);
                    if (amount > list.size()) {
                        JOptionPane.showMessageDialog(null, "You are deleting too much blood");
                    } else {
                        list.forEach((unit) -> {
                            if (unit != null) {
                                Blood.delete(unit.getBloodID());
                            }
                        });
                    }
                }
        );
    }

    @FXML
    public void changeRecTel() {
        recipientTableView.getSelectionModel().getSelectedItems().forEach((recipient) -> {

            String newTel = recChangeTel.getText();
            recipient.setTelephone(newTel);
            Recipient.update(recipient.getRecipientId(), recipient);

        });
        recChangeTel.clear();
        recipientTableView.refresh();
    }

    @FXML
    public void changeRecEmail() {
        recipientTableView.getSelectionModel().getSelectedItems().forEach((recipient) -> {

            String newEmail = recChangeEmail.getText();
            recipient.setEmail(newEmail);
            Recipient.update(recipient.getRecipientId(), recipient);

        });
        recChangeEmail.clear();
        recipientTableView.refresh();
    }

    @FXML
    public void changeRecAddress() {
        recipientTableView.getSelectionModel().getSelectedItems().forEach((recipient) -> {

            String newAddress = recChangeAddress.getText();
            recipient.setAddress(newAddress);
            Recipient.update(recipient.getRecipientId(), recipient);

        });
        recChangeAddress.clear();
        recipientTableView.refresh();
    }

    @FXML
    public void changeAdminBranch() {
        adminTableView.getSelectionModel().getSelectedItems().forEach((admin) -> {

            String newBranch = adminComboBox.getSelectionModel().getSelectedItem();
            admin.setBranch(newBranch);
            Employee.update(admin.getEmployeeId(), admin);

        });
        adminTableView.refresh();
    }

    @FXML
    public void changeNurseBranch() {
        nurseTableView.getSelectionModel().getSelectedItems().forEach((nurse) -> {

            String newBranch = nurseComboBox.getSelectionModel().getSelectedItem();
            nurse.setBranch(newBranch);
            Employee.update(nurse.getEmployeeId(), nurse);

        });
        nurseTableView.refresh();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        bloodComboBox.setItems(BLOOD_TYPES);
        adminComboBox.setItems(BRANCH_OPTIONS);
        nurseComboBox.setItems(BRANCH_OPTIONS);
        bloodTypes.addAll("A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-", "UN");

        Blood.getAll((blood) -> {
            blood.forEach((unit) -> {
                int index = bloodTypes.indexOf(unit.getType());
                bloodTypeCount[index]++;
            });
        });

        bloodTypes.forEach((type) -> {
            bloodData.add(new Data(type, bloodTypeCount[bloodTypes.indexOf(type)]));
        });

        bloodData.forEach((chartdata) -> {
            chartdata.nameProperty().bind(Bindings.concat(
                    chartdata.getName(), " ", chartdata.pieValueProperty(), " Units"
            ));
        });


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
        currentBloodCount.setData(bloodData);
        adminTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        nurseTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        recipientTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
}
