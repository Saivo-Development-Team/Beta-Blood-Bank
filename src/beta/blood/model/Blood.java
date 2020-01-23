/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.model;

import beta.blood.Handler;
import beta.blood.Handler.Function;
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
public class Blood {

    private final Long offeredBy;
    private final int bloodID, quantity;
    private final String type, verifiedBy;
    public static final int DEFULT_ID = -1;
    public static final int DEFULT_QUANTITY = 500;
    public static final String DEFULT_TYPE = "UN";

    public Blood(int bloodID, int quantity, Long offeredBy, String type, String verifiedBy) {
        this.bloodID = bloodID;
        this.quantity = quantity;
        this.offeredBy = offeredBy;
        this.type = type;
        this.verifiedBy = verifiedBy;
    }
    
    public Blood(int quantity, String type) {
        this.bloodID = -1;
        this.quantity = quantity;
        this.offeredBy = - Long.MAX_VALUE;
        this.type = type;
        this.verifiedBy = "";
    }

    public int getBloodID() {
        return bloodID;
    }

    public int getQuantity() {
        return quantity;
    }

    public Long getOfferedBy() {
        return offeredBy;
    }

    public String getType() {
        return type;
    }

    public String getVerifiedBy() {
        return verifiedBy;
    }

    public static Blood resultToBlood(ResultSet result) {
        try {
            if (result.next()) {
                return new Blood(
                        result.getInt("BloodID"),
                        result.getInt("Quantity"),
                        result.getLong("OfferedBy"),
                        result.getString("Type"),
                        result.getString("VerifiedBy")
                );

            }
        } catch (SQLException ex) {
            Logger.getLogger(Answers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ArrayList<Blood> resultToList(ResultSet result) {
        ArrayList<Blood> blood = new ArrayList();
        try {
            while (result.next()) {
                blood.add(new Blood(
                        result.getInt("BloodID"),
                        result.getInt("Quantity"),
                        result.getLong("OfferedBy"),
                        result.getString("Type"),
                        result.getString("VerifiedBy")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return blood;
    }

    public static void getById(Long bloodId, Handler.Function<Blood> function) {
        String query = String.format(
                "SELECT * FROM `blood` WHERE `BloodID` = %d", bloodId
        );
        service().executeResultQuery(query, (result) -> {
            function.cb(resultToBlood(result));
        });
    }

    public static void getAll(Handler.Function<ArrayList<Blood>> function) {
        String query = "SELECT * FROM `blood`";
        service().executeResultQuery(query, (result) -> {
            function.cb(resultToList(result));
        });
    }

    public static void getByQuery(String query, Handler.QueryType type, Function function) {
        service().executeQuery(query, type, function);
    }

    public static void getLastInserted(Handler.Function<Blood> function) {
        String query = String.format(
                "SELECT * FROM `blood` ORDER BY `BloodID` DESC LIMIT 1"
        );
        service().executeResultQuery(query, (Function<ResultSet>) (result) -> {
            function.cb(resultToBlood(result));
        });
    }

    public static void insert(Blood blood) {
        String query = String.format(
                "INSERT INTO `blood`(`BloodID`, `Quantity`, `Type`, `OfferedBy`, `VerifiedBy`)"
                + " VALUES (NULL,'%d','%s','%d','%s')",
                blood.quantity,
                blood.type,
                blood.offeredBy,
                blood.verifiedBy);

        service().executeUpdateQuery(query, null);
    }

    public static void delete(int bloodId) {
        String query = String.format(
                "DELETE FROM `blood` WHERE `BloodID` = %d", bloodId
        );
        service().executeUpdateQuery(query, null);
    }

    public static void update(int bloodID) {
        String query = String.format(
                "UPDATE `blood` SET `BloodID`=%d`Quantity`=%d,`Type`=%s,`OfferedBy`=%d,`VerifiedBy`=%s WHERE `BloodID= %d", bloodID);
        service().executeUpdateQuery(query, null);
    }
}
