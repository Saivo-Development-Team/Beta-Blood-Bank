/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.auth;

import beta.blood.model.Employee;

/**
 *
 * @author Ian Mubangizi
 */
public class LoginService {

    private static Employee employee;

    public static Employee getCurrentUser() {
        return employee;
    }

    public static int checkDetails(String Id, String password) {
        Employee.getById(Id, e -> employee = e);
        if (employee == null) {
            return -1;
        } else {
            if (employee.getPassword().equals(password)) {
                return employee.getPosition();
            }
        }
        return 2;
    }
}
