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

/**
 *
 * @author Elsa
 */
public class Answers {

    private final int answersId;
    private final String text;

    public Answers(int answersId, String text) {
        this.answersId = answersId;
        this.text = text;
    }

    public int getAnswersId() {
        return answersId;
    }

    public String getText() {
        return text;
    }

    private static Answers resultToAnswers(ResultSet result) {
        try {
            if (result.next()) {
                return new Answers(
                        result.getInt("AnswersID"),
                        result.getString("Answers")
                );

            }
        } catch (SQLException ex) {
            Logger.getLogger(Answers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void getById(int answersId, Function<Answers> function) {
        String query = String.format(
                "SELECT * FROM `answers` "
                + "WHERE  WHERE `AnswersID` = %d", answersId
        );
        service().executeResultQuery(query, (Function<ResultSet>) (result) -> {
            function.cb(resultToAnswers(result));
        });
    }

    public static void getLastInserted(Function<Answers> function) {
        String query = String.format(
                "SELECT * FROM `answers` ORDER BY `AnswersID` DESC LIMIT 1"
        );
        service().executeResultQuery(query, (Function<ResultSet>) (result) -> {
            function.cb(resultToAnswers(result));
        });
    }

    public static void insert(Answers answers) {
        String query = String.format(
                "INSERT INTO `answers` (`AnswersID`, `Answers`) "
                + "VALUES (NULL, '%s')", answers.text
        );
        service().executeUpdateQuery(query, null);
    }

    public static void delete(int answersID) {
        String query = String.format(
                "DELETE FROM `answers` WHERE `AnswersID` = %d ", answersID
        );
        service().executeUpdateQuery(query, null);
    }

    public static void update(int answersId, Answers answers) {
        String query = String.format(
                "UPDATE `answers` SET `AnswersID` = %d,`Answers`= %s "
                + "WHERE `AnswersID`= %d", answers.answersId, answers.text, answersId
        );
        service().executeUpdateQuery(query, null);
    }
}
