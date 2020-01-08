/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.model;

import beta.blood.database.DatabaseService;


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elsa
 */
public class Blood {

    private final int bloodID, quantity, offeredBy;
    private final String type, verifiedBy;

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

    public int getQuantity(String type) {
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
    
    public static void insert(Blood blood){
        String query = String.format("INSERT INTO `blood`(`BloodID`, `Quantity`, `Type`, `OfferedBy`, `VerifiedBy`) VALUES ('%d','%d','%s','%d','%s')",
        blood.bloodID, 
        blood.quantity, 
        blood.type, 
        blood.offeredBy,
        blood.verifiedBy);
        
         DatabaseService.service().executeUpdateQuery(query);
    }
           
}
