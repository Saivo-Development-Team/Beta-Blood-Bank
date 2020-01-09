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
public class Recipient {

    public static int DEFAULT_ID = -1;
    private final int recipientID;
    private final String name, address, telephone, email;

    public Recipient(int recipientID, String name, String address, String telephone, String email) {
        this.recipientID = recipientID;
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.email = email;
    }

    public int getRecipientID() {
        return recipientID;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public static void insert(Recipient recipient) {
        String query = String.format(
                "INSERT INTO `recipient`(`RecipientID`, `Name`, `Address`, `Telephone`, `Email`)"
                + "('%s','%s','%s','%s')",
                recipient.name,
                recipient.address,
                recipient.telephone,
                recipient.email
        );
        DatabaseService.service().executeUpdateQuery(query);
    }
    public static void delete(int recipientID){
        String query = String.format(
                "DELETE FROM `recipient` WHERE `RecipientID` = %d ", recipientID);
         DatabaseService.service().executeUpdateQuery(query);
    }
    public static void update( int recipientID){
        String query = String.format(
                "UPDATE `recipient` SET `RecipientID`=%d,`Name`= %s,`Address`=%s,"
                        + "`Telephone`=%d,`Email`=%s WHERE `RecipientID`= %d", recipientID);
                
           DatabaseService.service().executeUpdateQuery(query);     
        
    } 
}
