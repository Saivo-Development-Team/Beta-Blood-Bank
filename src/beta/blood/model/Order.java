/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.model;

import beta.blood.database.DatabaseService;
import java.sql.Date;

/**
 *
 * @author Elsa
 */
public class Order {

    private final int orderId;
    private final int orderedBy;
    private final Date date;
    private final String processedBy;

    public Order(int orderId, int orderedBy, Date date, String processedBy) {
        this.orderId = orderId;
        this.orderedBy = orderedBy;
        this.date = date;
        this.processedBy = processedBy;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getOrderedBy() {
        return orderedBy;
    }

    public Date getDate() {
        return date;
    }

    public String getProcessedBy() {
        return processedBy;
    }

    public static void insert(Order order) {
        String query = String.format(
                "INSERT INTO `order` "
                + "(`OrderID`, `Date`, `ProcessedBy`, `OrderedBy`) "
                + "VALUES ('%d','%d','%s','%d')",
                order.orderId,
                order.date,
                order.processedBy,
                order.orderedBy);
        DatabaseService.service().executeUpdateQuery(query);
    }
public static void delete(int orderID){
    String query = String.format(
            "DELETE FROM `order` WHERE `OrderID` = %d ", orderID);
    
    DatabaseService.service().executeUpdateQuery(query);
}
public static void update(int orderID){
    String query = String.format(
            "UPDATE `order` SET `OrderID`= %d,`Date`= %d,`ProcessedBy`= %s,`OrderedBy`= %d WHERE `OrderID`= %d",orderID);
            
             DatabaseService.service().executeUpdateQuery(query);
}
}
