/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.admin;

import beta.blood.Handler;
import static beta.blood.Helper.*;
import static beta.blood.Helper.StaticData.BRANCH_OPTIONS;
import beta.blood.model.Employee;
import static beta.blood.model.Employee.*;
import beta.blood.model.Recipient;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class AdminAddUserController implements Initializable {

    @FXML
    ComboBox<String> branchComboBox;

    @FXML
    ComboBox<String> employeeTypeComboBox;

    private int position;
    private String name,
            branch,
            surname,
            password,
            telephone,
            employeeId;

    @FXML
    TextField recipientName,
            recipientEmail,
            recipientPhone,
            employeeNameTextField,
            employeeSurnameTextField;

    @FXML
    TextArea recipientAddress;

    @FXML
    PasswordField employeePasswordField;

    @FXML
    Label employeeIdLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        employeeTypeComboBox.setItems(
                FXCollections.observableArrayList("Admin", "Nurse")
        );
        branchComboBox.setItems(BRANCH_OPTIONS);
    }

    @FXML
    private void back() {
        Handler.setScene(getClass(), "Admin Home", "/beta/blood/admin/AdminHome.fxml");
    }

    @FXML
    private void employeeTypeSelected() {
        String type = employeeTypeComboBox.getSelectionModel().getSelectedItem();
        if (type != null) {
            switch (type.toLowerCase()) {
                case ADMIN:
                    employeeIdLabel.setText(EMPLOYEE_AD + randomInt(10000));
                    position = 0;
                    break;
                case NURSE:
                    employeeIdLabel.setText(EMPLOYEE_NU + randomInt(10000));
                    position = 1;
                    break;
            }
            employeeIdLabel.setDisable(false);
        }
    }

    @FXML
    private void addEmployee() {
        if (isEmployeeFormCompleted() & isInfoCorret()) {
            Employee.insert(
                    new Employee(
                            employeeId,
                            name,
                            surname,
                            telephone != null ? telephone : "",
                            branch,
                            password,
                            position
                    )
            );
            clearEmployeeForm();
        }
    }

    @FXML
    private void addRecipient() {
        if (isRecipientFormCompleted() & isInfoCorret()) {
            Recipient.insert(new Recipient("-1",
                    getRecipientName(),
                    getRecipientAddress(),
                    getRecipientTelephone(),
                    getRecipientEmail())
            );
            clearRecipientForm();
        }
    }

    private String getRecipientName() {
        return recipientName.getText();
    }

    private String getRecipientAddress() {
        return recipientAddress.getText();
    }

    private String getRecipientTelephone() {
        return recipientPhone.getText();
    }

    private String getRecipientEmail() {
        return recipientEmail.getText();
    }

    private boolean isEmployeeFormCompleted() {
        return (setId()
                & setName()
                & setSurname()
                & setBranch()
                & setPassword());
    }

    private boolean isRecipientFormCompleted() {
        return isNotEmpty(
                getRecipientName(),
                getRecipientAddress(),
                getRecipientTelephone(),
                getRecipientEmail()
        );
    }

    private boolean setId() {
        employeeId = employeeIdLabel.getText();
        return employeeId.matches("(?i)((ad)|(nu))-\\d+");
    }

    private boolean setName() {
        name = employeeNameTextField.getText();
        return isNotEmpty(name);
    }

    private boolean setSurname() {
        surname = employeeSurnameTextField.getText();
        return isNotEmpty(surname);
    }

    private boolean setBranch() {
        branch = branchComboBox.getSelectionModel().getSelectedItem();
        return branch != null & BRANCH_OPTIONS.contains(branch);
    }

    private boolean setPassword() {
        password = employeePasswordField.getText();
        return isNotEmpty(password);
    }

    private void clearEmployeeForm() {
        employeeTypeComboBox.getSelectionModel().clearSelection();
        branchComboBox.getSelectionModel().clearSelection();
        employeeIdLabel.setDisable(true);
        employeeIdLabel.setText("[EmployeeID]");
        employeeNameTextField.setText("");
        employeeSurnameTextField.setText("");
        employeePasswordField.setText("");

    }

    private void clearRecipientForm() {
        recipientName.setText("");
        recipientEmail.setText("");
        recipientPhone.setText("");
        recipientAddress.setText("");
    }
}
