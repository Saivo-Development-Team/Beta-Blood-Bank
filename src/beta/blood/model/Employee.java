/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.model;

import beta.blood.Handler;
import beta.blood.Handler.Function;
import beta.blood.database.DatabaseService;
import static beta.blood.database.DatabaseService.service;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Elsa
 */
public class Employee {

    private final int position;
    private final String employeeId, name, surname, telephone, branch, password;
    public static final String EMPLOYEE_AD = "AD-", EMPLOYEE_NU = "NU-";
    public static final String ADMIN = "admin", NURSE = "nurse";

    public Employee(
            String employeeId, String name, String surname,
            String telephone, String branch, String password, int position
    ) {
        this.name = name;
        this.branch = branch;
        this.surname = surname;
        this.password = password;
        this.position = position;
        this.telephone = telephone;
        this.employeeId = employeeId;
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

    private static Employee resultToEmployee(ResultSet result) {
        try {
            if (result.next()) {
                return new Employee(
                        result.getString("EmployeeID"),
                        result.getString("Name"),
                        result.getString("Surname"),
                        result.getString("Telephone"),
                        result.getString("Branch"),
                        result.getString("Password"),
                        result.getInt("Position")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static ArrayList<Employee> resultToEmployees(ResultSet result) {
        ArrayList<Employee> employees = new ArrayList();
        try {
            while (!result.isAfterLast()) {
                employees.add(resultToEmployee(result));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employees;
    }

    public static void getById(String employeeId, Function<Employee> function) {
        String query = String.format(
                "SELECT * FROM `employee` WHERE `EmployeeID`='%s'", employeeId
        );
        service().executeResultQuery(query, (result) -> {
            function.cb(resultToEmployee(result));
        });
    }

    public static void getAll(Function<ArrayList<Employee>> function) {
        String query = "SELECT * FROM `employee`";
        service().executeResultQuery(query, (result) -> {
            function.cb(resultToEmployees(result));
        });
    }

    public static void insert(Employee employee) {
        String query = String.format(
                "INSERT INTO `employee` "
                + "(`EmployeeID`,`Name`,`Surname`,`Telephone`,`Branch`,`Position`,`Password`) "
                + "VALUES ('%s','%s','%s','%s','%s','%d','%s')",
                employee.employeeId,
                employee.name,
                employee.surname,
                employee.telephone,
                employee.branch,
                employee.position,
                employee.password
        );
        service().executeUpdateQuery(query, null);
    }

    public static void delete(int employeeID) {
        String query = String.format(
                "DELETE FROM `employee` WHERE `EmployeeID` = %d ", employeeID
        );
    }

    public static void update(int employeeId, Employee employee) {
        String query = String.format(
                "UPDATE `employee` "
                + "SET `EmployeeID`=%s,`Name`=%s,`Surname`=%s,`Telephone`=%s,`Branch`=%s,`Position`=%d,`Password`=%s "
                + "WHERE `EmployeeID`= %s",
                employee.employeeId,
                employee.name,
                employee.surname,
                employee.telephone,
                employee.branch,
                employee.position,
                employee.password,
                employeeId
        );
        service().executeUpdateQuery(query, null);
    }
}
