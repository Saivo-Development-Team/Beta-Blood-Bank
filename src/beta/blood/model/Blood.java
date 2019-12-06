/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.model;

/**
 *
 * @author Elsa
 */
public class Blood {

    private int bloodID, quantity, offeredBy;
    private String type, verifiedBy;

    public Blood(int bloodID, int quantity, int offeredBy, String type, String verifiedBy) {
        this.bloodID = bloodID;
        this.quantity = quantity;
        this.offeredBy = offeredBy;
        this.type = type;
        this.verifiedBy = verifiedBy;
    }

    public int getBloodID() {
        return bloodID;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getOfferedBy() {
        return offeredBy;
    }

    public String getType() {
        return type;
    }

    public String getVerifiedBy() {
        return verifiedBy;
    }
    
    
}
