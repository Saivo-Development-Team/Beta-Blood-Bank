/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.model;

import beta.blood.database.DatabaseService;
import static beta.blood.database.DatabaseService.service;

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
                "INSERT INTO `request`(`BloodID`, `OrderID`) "
                + "VALUES ('%d','%d')",
                request.bloodId,
                request.orderId);
        service().executeUpdateQuery(query, null);
    }

    public static void delete(int bloodID, int orderID) {
        String query = String.format(
                "DELETE FROM `request` "
                + "WHERE `BloodID`= %d AND `OrderID`= %d", bloodID, orderID);
        service().executeUpdateQuery(query, null);
    }

    public static void update(int bloodID, int orderID) {
        String query = String.format(
                "UPDATE `request` SET `BloodID`=%d,`OrderID`=%d "
                + "WHERE `BloodID`= %d AND `OrderID`= %d", bloodID, orderID);

        service().executeUpdateQuery(query, null);
    }
}
