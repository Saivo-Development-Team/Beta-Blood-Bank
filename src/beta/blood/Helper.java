/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood;

import beta.blood.nurse.DonorQuestionController.Question;
import beta.blood.nurse.DonorQuestionController.Questionnaire;
import static beta.blood.nurse.DonorQuestionController.setQuestionBox;
import static beta.blood.nurse.DonorQuestionController.setQuestionnaireBox;
import java.lang.reflect.Field;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
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

    public static Optional<ButtonType> alertMessage(String message, String title, AlertType option, ButtonType... elements) {
        Alert alert = new Alert(option);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.getButtonTypes().setAll(elements);
        return alert.showAndWait();
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
        column.setCellValueFactory((new PropertyValueFactory<>(field.getName())));
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

    public static Question questionBox(String title, String question, String left, String right, String answer) {
        return setQuestionBox(title, question, left, right, answer);
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
            questionnaireBox("Health Questionnaire")
        };

        public static final Question[][] QUESTION_BOX = {
            {questionBox("", "Do you consider your blood safe to be transfused to a patient?", "Yes", "No", "Yes"),
                questionBox("", "Have you or your partner ever used recreational/street drugs by nose, mouth or injection needle?", "Yes", "No", "No"),
                questionBox("In the past six months have you:", "Had a tattoo, body piercing or permanent makeup applied?", "Yes", "No", "No"),
                questionBox("", "Had raatib, ritual scarring, ritual piercing, ritual circumcision, blood sharing or been stabbed?", "Yes", "No", "No"),
                questionBox("For health care workers and their partners only, in the last six months have you:", "Have you or your sexual partner had a needle stick or skin penetrating injury; or had skin, eye or mouth contact with another person’s blood?", "Yes", "No", "No"),
                questionBox("Questions of a sexual nature", "Do you have AIDS or are you HIV positive?", "Yes", "No", "No"),
                questionBox("", "Have you ever had sexual contact with anyone who has AIDS or is HIV positive?", "Yes", "No", "No"),},
            {questionBox("Health Questions", "In the past 4 hours have you had something to eat and drink?", "Yes", "No", "Yes"),
                questionBox("", "Have you had a cold, flu, sore throat, fever, infection or allergies?", "Yes", "No", "No"),
                questionBox("", "Have you taken any medication, injections or tablets?", "Yes", "No", "No"),
                questionBox("", "Do you have any diseases", "Yes", "No", "No"),
                questionBox("", "Have you ever injected or been injected with illegal steroids?", "Yes", "No", "No")}};

        public static final Questionnaire[] QUESTIONNAIRE_ARRAY = {
            new Questionnaire(QUESTIONNAIRE_BOX[0], QUESTION_BOX[0]),
            new Questionnaire(QUESTIONNAIRE_BOX[1], QUESTION_BOX[1])
        };
    }

}
