/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.model;

import java.sql.Date;

/**
 *
 * @author Elsa
 */
public class Order {

    private int orderId, orderedBy;
    private Date date;
    private String processedBy;

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
    
   
}
