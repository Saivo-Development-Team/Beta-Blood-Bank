/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.model;

import static beta.blood.database.DatabaseService.service;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import beta.blood.Handler.Function;
import beta.blood.Handler.QueryType;
import java.util.ArrayList;

/**
 *
 * @author Elsa
 */
public class Donor {

  
    private final String donorId, name, surname, address;
    char gender;
    private final int age, answers;

    public Donor(String donorID, String name, String surname, String address, char gender ,int age, int answers) {
        this.donorId = donorID;
        this.age = age;
        this.answers = answers;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.gender = gender;
    }

    public String getDonorId() {
        return donorId;
    }

    public int getAge() {
        return age;
    }

    public int getAnswers() {
        return answers;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public char getGender() {
        return gender;
    }

    private static Donor resultToDonor(ResultSet result) {
        try {
            if (result.next()) {
                return new Donor(
                        result.getString("DonorID"),
                        result.getString("Name"),
                        result.getString("Surname"),
                        result.getString("Address"),
                        result.getString("Gender").charAt(0),
                        result.getInt("Age"),
                        result.getInt("Answers")
         
                );

            }
        } catch (SQLException ex) {
            Logger.getLogger(Answers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static ArrayList<Donor> resultToDonors(ResultSet result) {
        ArrayList<Donor> donors = new ArrayList();
        try {
            while (result.next()) {
                donors.add(new Donor(
                        result.getString("DonorID"),
                        result.getString("Name"),
                        result.getString("Surname"),
                        result.getString("Address"),
                        result.getString("Gender").charAt(0),
                        result.getInt("Age"),
                        result.getInt("Answers")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return donors;
    }

    public static void getById(Long donorId, Function<Donor> function) {
        String query = String.format(
                "SELECT * FROM `donor` WHERE `DonorID` = %d", donorId
        );
        service().executeResultQuery(query, (result) -> {
            function.cb(resultToDonor(result));
        });
    }

    public static void getAll(Function<ArrayList<Donor>> function) {
        String query = "SELECT * FROM `donor`";
        service().executeResultQuery(query, (result) -> {
            function.cb(resultToDonors(result));
        });
    }

    public static void getByQuery(String query, QueryType type, Function function) {
        service().executeQuery(query, type, (result) -> {
            function.cb(result);
        });
    }

    public static void getLastInserted(Function<Donor> function) {
        String query = String.format(
                "SELECT * FROM `donor` ORDER BY `Answers` DESC LIMIT 1"
        );
        service().executeResultQuery(query, (Function<ResultSet>) (result) -> {
            function.cb(resultToDonor(result));
        });
    }

    public static void insert(Donor donor) {
        String query = String.format("INSERT INTO `donor` "
                + "(`DonorID`, `Name`, `Surname`, `Address`, `Gender`, `Age`, `Answers`)"
                + " VALUES ('%s','%s','%s','%s','%s','%d','%d')",
                donor.donorId,
                donor.name,
                donor.surname,
                donor.address,
                donor.gender,
                donor.age,
                donor.answers);
        service().executeUpdateQuery(query, null);
    }

    public static void delete(int donorID) {
        String query = String.format(
                "DELETE FROM `donor` WHERE `DonorID` = %d", donorID
        );
        service().executeUpdateQuery(query, null);
    }

    public static void update(int donorId, Donor donor) {
        String query = String.format("UPDATE `donor` SET `DonorID`=%d,`Name`=%s,`Surname`=%s,`Address`=%s,`Gender`=%s,`Age`=%d,`Answers`=%d WHERE `DonorID` = %d", donorId);
        service().executeUpdateQuery(query, null);
    }

}
