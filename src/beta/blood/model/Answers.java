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

    public static void insert(Answers answers) {
        String query = String.format(
                "INSERT INTO `answers`(`AnswersID`, `Answers`) "
                + "VALUES ('%d','%s')",
                answers.answersId,
                answers.answersId);

        DatabaseService.service().executeUpdateQuery(query);
    }

}
