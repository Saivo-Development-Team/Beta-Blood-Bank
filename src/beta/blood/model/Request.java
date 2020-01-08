/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.model;

import beta.blood.database.DatabaseService;

/**
 *
 * @author Elsa
 */
public class Request {

    private final int bloodId, orderId;

    public Request(int bloodId, int orderId) {
        this.bloodId = bloodId;
        this.orderId = orderId;
    }

    public int getBloodId() {
        return bloodId;
    }

    public int getOrderId() {
        return orderId;
    }

    public static void insert(Request request) {
        String query = String.format(
                "INSERT INTO `request`(`BloodID`, `OrderID`) VALUES ('%d','%d')",
                request.bloodId,
                request.orderId);
        DatabaseService.service().executeUpdateQuery(query);
    }

}
