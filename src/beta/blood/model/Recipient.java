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
public class Recipient {
    private int recipientID;
    private String name, address, telephone, email;

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
    
    
}
