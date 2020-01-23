/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood;

import beta.blood.model.Employee;
import beta.blood.nurse.DonorQuestionController;
import beta.blood.nurse.DonorQuestionController.Question;
import beta.blood.nurse.DonorQuestionController.Questionnaire;
import static beta.blood.nurse.DonorQuestionController.setQuestionBox;
import static beta.blood.nurse.DonorQuestionController.setQuestionnaireBox;
import java.lang.reflect.Field;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.OK_CANCEL_OPTION;

/**
 *
 * @author Ian Mubangizi
 * @param <T>
 */
public class Helper<T> {

    public static boolean isNotEmpty(String... text) {
        for (String s : text) {
            if (!isNotEmpty(s)) {
                return false;
            }
        }
        return true;
    }

    public static void inputControl(TextInputControl input, Check check) {
        input.addEventFilter(KeyEvent.KEY_TYPED, (KeyEvent event) -> {
            check.cb(input, event);
        });
    }

    public interface Check<T extends Object> {

        void cb(TextInputControl input, KeyEvent event);
    }

    public static boolean isNotEmpty(String text) {
        return !text.isEmpty();
    }

    public static boolean isInfoCorret() {
        int result = JOptionPane.showConfirmDialog(
                null, "Please ensure information is correct", "Alert",
                OK_CANCEL_OPTION
        );
        return result == JOptionPane.OK_OPTION;
    }

    public static int randomInt(int size) {
        return (int) (Math.random() * size);
    }

    public static void setAnchorPaneConstraints(Node node, AnchorPaneConstraints anc) {
        AnchorPane.setTopAnchor(node, anc.top);
        AnchorPane.setBottomAnchor(node, anc.bottom);
        AnchorPane.setLeftAnchor(node, anc.left);
        AnchorPane.setRightAnchor(node, anc.right);
    }

    public static AnchorPaneConstraints getAnchorPaneConstraints(Node node) {
        return new AnchorPaneConstraints(AnchorPane.getTopAnchor(node),
                AnchorPane.getBottomAnchor(node),
                AnchorPane.getLeftAnchor(node),
                AnchorPane.getRightAnchor(node));
    }

    public static TableColumn createTableColumn(Field field) {
        TableColumn column = new TableColumn();
        column.setText(field.getName().toUpperCase());
        column.setCellValueFactory((new PropertyValueFactory(field.getName())));
        return column;
    }

    public static class AnchorPaneConstraints {

        Double top, bottom, left, right;

        public AnchorPaneConstraints(Double top, Double bottom, Double left, Double right) {
            this.top = top;
            this.bottom = bottom;
            this.left = left;
            this.right = right;
        }

    }

    public static Question questionBox(String title, String question, String left, String right) {
        return setQuestionBox(title, question, left, right);
    }

    public static Node questionnaireBox(String title) {
        return setQuestionnaireBox(title);
    }

    public static class cast<T> {

        public T to(Object obj) {
            return (T) obj;
        }
    }

    public static class StaticData {

        public static final ObservableList<String> TITLE_OPTIONS = FXCollections
                .observableArrayList(
                        "MR",
                        "MS",
                        "MRS"
                );

        public static final ObservableList<String> BRANCH_OPTIONS = FXCollections
                .observableArrayList(
                        "Cape Town", "Durban", "Johanessburg", "Langebaan",
                        "Port Elizabeth", "Pretoria"
                );
        
        public static final ObservableList<String> MONTH_OPTIONS = FXCollections
                .observableArrayList(
                        "January", "February", "March", "April","May", "June", "July", "August", "September", "October", "November", "December"
                );

        public static final ObservableList<String> BLOOD_TYPES = FXCollections
                .observableArrayList(
                        "A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"
                );

        public static final Node[] QUESTIONNAIRE_BOX = {
            questionnaireBox("Lifestyle Questionnaire (YES/NO)"),
            questionnaireBox("More...")
        };

        public static final Question[][] QUESTION_BOX = {
            {questionBox("Title 1", "Question 1", "Yes", "No"),
                questionBox("", "Question 2", "Yes", "No"),
                questionBox("", "Question 3", "Yes", "No"),
                questionBox("", "Question 4", "Yes", "No"),
                questionBox("Title 2", "Question 5", "Yes", "No"),
                questionBox("", "Question 6", "Yes", "No"),
                questionBox("", "Question 7", "Yes", "No"),
                questionBox("", "Question 8", "Yes", "No"),
                questionBox("", "Question 9", "Yes", "No"),
                questionBox("Title 3", "Question 10", "Yes", "No"),
                questionBox("", "Question 11", "Yes", "No"),
                questionBox("", "Question 12", "Yes", "No"),
                questionBox("", "Question 13", "Yes", "No")},
            {questionBox("Title 4", "Question 1", "Yes", "No"),
                questionBox("", "Question 2", "Yes", "No"),
                questionBox("", "Question 3", "Yes", "No"),
                questionBox("", "Question 4", "Yes", "No")}};

        public static final Questionnaire[] QUESTIONNAIRE_ARRAY = {
            new Questionnaire(QUESTIONNAIRE_BOX[0], QUESTION_BOX[0]),
            new Questionnaire(QUESTIONNAIRE_BOX[1], QUESTION_BOX[1])
        };
    }

}
