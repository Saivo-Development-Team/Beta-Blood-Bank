/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.nurse;

import beta.blood.Handler;
import static beta.blood.Handler.loadFxml;
import beta.blood.Helper.AnchorPaneConstraints;
import static beta.blood.Helper.isNotEmpty;
import static beta.blood.Helper.setAnchorPaneConstraints;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author perso
 */
public class DonorQuestionController implements Initializable {

    @FXML
    Label titleLabel;

    @FXML
    Label questionLabel;

    @FXML
    RadioButton leftRadioButton;

    @FXML
    RadioButton rightRadioButton;

    private static ToggleGroup toggle;
    public static final String LEFT_RADIO_BUTTON = "leftRadioButton",
            RIGHT_RADIO_BUTTON = "rightRadioButton",
            TITLE_LABEL = "titleLabel",
            QUESTIONNAIRE = "questionnaire",
            QUESTION_LABEL = "questionLabel";
    private static String titleText, questionText, leftButtonText, rightButtonText;

    public static Question setQuestionBox(String title, String question, String left, String right) {
        titleText = title;
        questionText = question;
        leftButtonText = left;
        rightButtonText = right;
        toggle = new ToggleGroup();
        return new Question();
    }

    public static Node setQuestionnaireBox(String title) {
        titleText = title;
        return Handler.loadFxml(DonorQuestionController.class, "DonorQuestionnaire.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (url.getPath().contains("DonorQuestion.fxml")) {
            loadQuestion();
        }
        if (url.getPath().contains("DonorQuestionnaire.fxml")) {
            titleLabel.setText(titleText);
        }
    }

    private void loadQuestion() {
        if (isNotEmpty(titleText)) {
            titleLabel.setVisible(true);
            titleLabel.setText(titleText);
            setAnchorPaneConstraints(titleLabel, new AnchorPaneConstraints(10.0, 50.0, 0.0, 0.0));
            setAnchorPaneConstraints(questionLabel, new AnchorPaneConstraints(25.0, 25.0, 0.0, 0.0));
            setAnchorPaneConstraints(leftRadioButton, new AnchorPaneConstraints(50.0, 0.0, 0.0, 70.0));
            setAnchorPaneConstraints(rightRadioButton, new AnchorPaneConstraints(50.0, 0.0, 70.0, 0.0));
        }

        questionLabel.setText(questionText);
        leftRadioButton.setText(leftButtonText);
        rightRadioButton.setText(rightButtonText);

        leftRadioButton.setToggleGroup(toggle);
        rightRadioButton.setToggleGroup(toggle);
    }

    public static class Questionnaire {

        private static int sections = 0;
        private final Node holder;
        private final ArrayList<Question> questions = new ArrayList();
        private final ObservableList<String> ANSWERS = FXCollections.observableArrayList();

        public Questionnaire(Node questionnaire, Question[] questions) {
            this.holder = questionnaire;
            this.questions.addAll(Arrays.asList(questions));
        }

        public void submitAnswer(Question question, String answer) {
            Label label = (Label) question.view.lookup("#" + TITLE_LABEL);
            String title = label.getText();
            if (!isNotEmpty(title)) {

            }
            String _answer = String.format(
                    "[section:%d],[title:%s],"
                    + "[index:%d],[question:%s],[answer:%s]",
                    question.section, title, question.index,
                    ((Label) question.view
                            .lookup("#" + QUESTION_LABEL))
                            .getText(), answer
            );
            ANSWERS.add(_answer);
        }

        public Node getHolder() {
            return holder;
        }

        public ArrayList<Question> getQuestions() {
            return questions;
        }

        public Node[] getQuestionNodes() {
            List<Node> list = questions.stream().map(question -> {
                return setQuestion(question);
            }).collect(Collectors.toList());
            Node[] nodes = new Node[list.size()];
            return list.toArray(nodes);
        }

        public ObservableList<String> getAnswers() {
            return ANSWERS;
        }

        private Node setQuestion(Question question) {
            question.index = questions.indexOf(question);

            Label label = ((Label) questions
                    .get(0).view
                    .lookup("#" + TITLE_LABEL));

            Label current = ((Label) questions
                    .get(question.index).view
                    .lookup("#" + TITLE_LABEL));

            if (isNotEmpty(current.getText()) & current.isVisible()) {
                sections++;
            } else {
                current.setText(label.getText());
            }

            question.section = sections;
            return question.view;
        }
    }

    public static class Question {

        Node view;
        int index;

        int section;

        public Question() {
            view = loadFxml(DonorQuestionController.class, "DonorQuestion.fxml");
        }

    }

}
