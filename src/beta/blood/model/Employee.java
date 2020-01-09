/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.model;

import beta.blood.auth.LoginService;
import beta.blood.database.DatabaseService;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elsa
 */
public class Employee {

    private final String employeeId, name, surname, telephone, branch, password;
    private final int position;

    public Employee(String employeeId, String name, String surname, String telephone, String branch, String password, int position) {
        this.employeeId = employeeId;
        this.name = name;
        this.surname = surname;
        this.telephone = telephone;
        this.branch = branch;
        this.password = password;
        this.position = position;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getBranch() {
        return branch;
    }

    public String getPassword() {
        return password;
    }

    public int getPosition() {
        return position;
    }
    
    public static String getEmployee() {
        
        String query = ("SELECT employeeID FROM employee");
        ResultSet result = DatabaseService.service().executeResultQuery(query);
           
        return null;
    }

    public static Employee getById(String employeeId) {
        try {
            String query = String.format(
                    "select * from employee where employeeId='%s'", employeeId
            );
            ResultSet result = DatabaseService.service().executeResultQuery(query);

            if (result.next()) {
                return new Employee(
                        result.getString("EmployeeId"),
                        result.getString("Name"),
                        result.getString("Surname"),
                        result.getString("Telephone"),
                        result.getString("Branch"),
                        result.getString("Password"),
                        result.getInt("Position")
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(
                    LoginService.class.getName()).log(Level.SEVERE, null, ex
            );
        }
        return null;
    }

    public static void insert(Employee employee) {
        String query = String.format(
                "INSERT INTO `employee`(`EmployeeID`, `Name`, `Surname`, `Telephone`, `Branch`, `Position`, `Password`)"
                + " values ('%s','%s','%s','%s','%s','%s','%d')",
                employee.employeeId,
                employee.name,
                employee.surname,
                employee.telephone,
                employee.branch,
                employee.password,
                employee.position
        );
        DatabaseService.service().executeUpdateQuery(query);
    }
    public static void delete(int employeeID){
        String query = String.format(
                "DELETE FROM `employee` WHERE `EmployeeID` = %d " , employeeID);
    }
    public static void update(int employeeID){
        String query = String.format(
              "UPDATE `employee` SET `EmployeeID`=%d,`Name`=%s,`Surname`=%s,`Telephone`=%d,`Branch`=%s,`Position`=%s,`Password`=%d WHERE `EmployeeID`= %d",employeeID);
                
                DatabaseService.service().executeUpdateQuery(query);
    }
}
