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
public class Donor {

    private final int donorID, age, answers;
    private final String name, surname, address, gender;

    public Donor(int donorID, int age, int answers, String name, String surname, String address, String gender) {
        this.donorID = donorID;
        this.age = age;
        this.answers = answers;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.gender = gender;
    }

    public static void insert() {

    }

    public int getDonorID() {
        return donorID;
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

    public String getGender() {
        return gender;
    }

    public static void insert(Donor donor) {
        String query = String.format(
                "INSERT INTO `donor` "
                + "(`DonorID`, `Name`, `Surname`, `Address`, `Gender`, `Age`, `Answers`)"
                + " VALUES ('%d','%s','%s','%s','%s','%d','%d')",
                donor.donorID,
                donor.name,
                donor.surname,
                donor.address,
                donor.gender,
                donor.age,
                donor.answers);
        DatabaseService.service().executeUpdateQuery(query);

    }
}
