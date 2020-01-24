/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.admin;

import static beta.blood.Helper.StaticData.BLOOD_TYPES;
import static beta.blood.Helper.StaticData.BRANCH_OPTIONS;
import static beta.blood.Helper.StaticData.VERIFIED_BLOOD_TYPES;
import static beta.blood.Helper.createTableColumn;
import beta.blood.model.Blood;
import beta.blood.model.Employee;
import beta.blood.model.Recipient;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
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

    int[] unitTypeCount = {
        0, 0, 0, 0, 0, 0, 0, 0, 0
    };

    Blood[] bloodCounts = new Blood[unitTypeCount.length];
    ObservableList<Blood> bloodCountList = FXCollections.observableArrayList();

    @FXML
    TableView<Employee> adminTableView;
    @FXML
    TableView<Employee> nurseTableView;
    @FXML
    TableView<Recipient> recipientTableView;
    @FXML
    TableView<Blood> bloodAmountTableView;

    ObservableList<Data> bloodData = FXCollections.observableArrayList();
    ObservableList<Blood> bloodUnits = FXCollections.observableArrayList();

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
        bloodData.clear();
        adminList.clear();
        nurseList.clear();
        recipientList.clear();
        bloodCountList.clear();
        bloodCounts = new Blood[bloodCounts.length];
        unitTypeCount = new int[unitTypeCount.length];
        loadBloodData();
        loadEmployeeData();
        loadRecipientData();
        nurseTableView.refresh();
        adminTableView.refresh();
        recipientTableView.refresh();
        bloodAmountTableView.refresh();
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
    public void deleteRecipient() {
        recipientTableView.getSelectionModel().getSelectedItems().forEach((recipient) -> {
            Recipient.delete(Integer.parseInt(recipient.getRecipientId()));
            recipientList.remove(recipient);
        });
        nurseTableView.refresh();
    }

    @FXML
    public void deleteBlood() {
        int amount = Integer.parseInt(bloodamount.getText());
        String type = bloodComboBox.getSelectionModel().getSelectedItem();
        if (type != null) {
            if (amount <= unitTypeCount[BLOOD_TYPES.indexOf(type)]) {
                bloodUnits.stream().filter((unit) -> {
                    return unit.getType().equals(type);
                }).limit(amount).forEach((it) -> {
                    Blood.delete(it.getBloodID());
                });
                bloodComboBox.getSelectionModel().clearSelection();
            } else {
                JOptionPane.showMessageDialog(null, "You are deleting too much blood");
            }
        }
        refresh();
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

        adminComboBox.setItems(BRANCH_OPTIONS);
        nurseComboBox.setItems(BRANCH_OPTIONS);
        bloodComboBox.setItems(VERIFIED_BLOOD_TYPES);

        loadBloodData();
        loadEmployeeData();
        loadRecipientData();

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

        for (Field field : Blood.class.getDeclaredFields()) {
            switch (field.getName()) {
                case "quantity":
                case "type":
                    bloodAmountTableView.getColumns().add(createTableColumn(field));
                    break;
            }
        }

        nurseTableView.setItems(nurseList);
        adminTableView.setItems(adminList);
        currentBloodCount.setData(bloodData);
        recipientTableView.setItems(recipientList);
        bloodAmountTableView.setItems(bloodCountList);
        adminTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        nurseTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        recipientTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    private void loadEmployeeData() {
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
    }

    private void loadRecipientData() {
        Recipient.getAll((recipients) -> {
            recipients.forEach((recipient) -> {
                if (recipient != null) {
                    recipientList.add(recipient);
                }
            });
        });
    }

    private void loadBloodData() {
        Blood.getAll((blood) -> {
            bloodUnits.addAll(blood);
            blood.forEach((unit) -> {
                if (!"UN".equals(unit.getType())) {
                    int index = VERIFIED_BLOOD_TYPES.indexOf(unit.getType());
                    unitTypeCount[index]++;
                    bloodCounts[index] = new Blood(unitTypeCount[index], VERIFIED_BLOOD_TYPES.get(index));
                }
            });
            bloodCountList.setAll(bloodCounts);
            bloodCountList.retainAll(bloodCountList.stream().filter(
                    (unit) -> unit != null).collect(Collectors.toList())
            );
        });

        VERIFIED_BLOOD_TYPES.forEach((type) -> {
            int count = unitTypeCount[VERIFIED_BLOOD_TYPES.indexOf(type)];
            if (count > 0) {
                bloodData.add(new Data(type, count));
            }
        });

        bloodData.forEach((chartdata) -> {
            int value = (int) chartdata.pieValueProperty().get();
            chartdata.nameProperty().bind(Bindings.concat(
                    chartdata.getName(), " ", value,
                    " Unit" + (value > 1 | value <= 0 ? "s" : "")
            ));
        });
    }
}
