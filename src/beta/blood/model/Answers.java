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
public class Answers {

    private int answersId;
    private String text;

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
    
    
}