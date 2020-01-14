/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.model;

import beta.blood.Handler;
import static beta.blood.database.DatabaseService.service;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elsa
 */
public class Recipient {

    public static int DEFAULT_ID = -1;
    private final int recipientId;
    private final String name, address, telephone, email;

    public Recipient(int recipientId, String name, String address, String telephone, String email) {
        this.recipientId = recipientId;
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.email = email;
    }

    public int getRecipientID() {
        return recipientId;
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

    private static Recipient resultToRecipient(ResultSet result) {
        try {
            if (result.next()) {
                return new Recipient(
                        result.getInt("RecipientID"),
                        result.getString("Name"),
                        result.getString("Address"),
                        result.getString("Telephone"),
                        result.getString("Email")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static ArrayList<Recipient> resultToEmployees(ResultSet result) {
        ArrayList<Recipient> employees = new ArrayList();
        try {
            while (!result.isAfterLast()) {
                employees.add(resultToRecipient(result));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Recipient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employees;
    }

    public static void getById(int recipientId, Handler.Function<Recipient> function) {
        String query = String.format(
                "SELECT * FROM `recipient` WHERE `RecipientID`='%s'", recipientId
        );
        service().executeResultQuery(query, (result) -> {
            function.cb(resultToRecipient(result));
        });
    }

    public static void getAll(Handler.Function<ArrayList<Recipient>> function) {
        String query = "SELECT * FROM `recipient`";
        service().executeResultQuery(query, (result) -> {
            function.cb(resultToEmployees(result));
        });
    }

    public static void insert(Recipient recipient) {
        String query = String.format(
                "INSERT INTO `recipient` (`Name`, `Address`, `Telephone`, `Email`) "
                + "VALUES ('%s','%s','%s','%s')",
                recipient.name,
                recipient.address,
                recipient.telephone,
                recipient.email
        );
        service().executeUpdateQuery(query, null);
    }

    public static void delete(int recipientId) {
        String query = String.format(
                "DELETE FROM `recipient` WHERE `RecipientID` = %d ", recipientId);
        service().executeUpdateQuery(query, null);
    }

    public static void update(int recipientID) {
        String query = String.format(
                "UPDATE `recipient` SET `RecipientID`=%d,`Name`= %s,`Address`=%s,"
                + "`Telephone`=%d,`Email`=%s WHERE `RecipientID`= %d", recipientID);

        service().executeUpdateQuery(query, null);

    }
}
