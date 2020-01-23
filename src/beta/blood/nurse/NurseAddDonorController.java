/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.nurse;

import static beta.blood.Handler.setScene;
import static beta.blood.Helper.StaticData.QUESTIONNAIRE_ARRAY;
import static beta.blood.Helper.StaticData.MONTH_OPTIONS;
import static beta.blood.Helper.StaticData.TITLE_OPTIONS;
import static beta.blood.Helper.isNotEmpty;
import beta.blood.model.Answers;
import beta.blood.model.Donor;
import static beta.blood.nurse.DonorQuestionController.LEFT_RADIO_BUTTON;
import static beta.blood.nurse.DonorQuestionController.QUESTIONNAIRE;
import beta.blood.nurse.DonorQuestionController.Question;
import beta.blood.nurse.DonorQuestionController.Questionnaire;
import static beta.blood.nurse.DonorQuestionController.RIGHT_RADIO_BUTTON;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ListChangeListener.Change;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import static beta.blood.Helper.inputControl;
import static beta.blood.auth.LoginService.getCurrentUser;
import beta.blood.model.Blood;
import static beta.blood.model.Blood.DEFULT_ID;
import static beta.blood.model.Blood.DEFULT_QUANTITY;
import static beta.blood.model.Blood.DEFULT_TYPE;


/**
 * FXML Controller class
 *
 * @author ethan
 */
public class NurseAddDonorController implements Initializable {

    @FXML
    ScrollPane scrollpane;
    @FXML
    ComboBox<String> combobox;
    @FXML
    TextField donorIdTextField;
    @FXML
    TextField nameTextField;
    @FXML
    TextField surnameTextField;
    @FXML
    TextField ageTextField;
    @FXML
    TextField emailTextField;
    @FXML
    TextField telephoneTextField;
    @FXML
    TextField cellhoneTextField;
    @FXML
    TextArea homeAddressTextArea;
    @FXML
    DatePicker dataOfBirthDatePicker;
    @FXML
    RadioButton maleRadioButton;
    @FXML
    RadioButton femaleRadioButton;
    @FXML 
    ComboBox month;
    
    
    
            

    Long donorId;
    char gender = 'N';
    String name, surname, age, email, telephone, cellphone, homeAddress;
    String answers = "";
    String donorMonth;

    VBox mainVBox = new VBox();
    ToggleGroup genderToggle = new ToggleGroup();

    @FXML
    private void back() {
        setScene(getClass(), "Nurse Home", "NurseHome.fxml");
    }

    @FXML
    private void addDonor() {
        donorId = Long.parseLong(isNotEmpty(donorIdTextField.getText())
                ? donorIdTextField.getText()
                : "");

        name = nameTextField.getText();
        surname = surnameTextField.getText();
        age = ageTextField.getText();
        email = emailTextField.getText();
        telephone = telephoneTextField.getText();
        cellphone = cellhoneTextField.getText();
        homeAddress = homeAddressTextArea.getText();
        donorMonth = month.getValue().toString();

        Answers.insert(new Answers(-1, answers));

        Answers.getLastInserted((answer) -> {
            Donor.insert(new Donor(
                    donorId,
                    Integer.parseInt(age),
                    answer.getAnswersId(),
                    name, surname, homeAddress, gender, donorMonth
            ));
        });

        Donor.getLastInserted((donor) -> {
            Blood.insert(new Blood(
                    DEFULT_ID,
                    DEFULT_QUANTITY,
                    donor.getDonorID(),
                    DEFULT_TYPE,
                    getCurrentUser().getEmployeeId()
            ));
        });

    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        inputControl(ageTextField, (input, event) -> {
            if (input.getText().matches("^[0-9]{3}$")) {
                event.consume();
            } else if (!event.getCharacter().matches("^[0-9]{1}$")) {
                event.consume();
            }
        });

        inputControl(donorIdTextField, (input, event) -> {
            if (input.getText().matches("^[0-9]{13}$")) {
                event.consume();
            } else if (!event.getCharacter().matches("^[0-9]{1}$")) {
                event.consume();
            }
        });

        initializeQuestionnaire();
        initializeDonorIdTextField();

        genderToggle.selectedToggleProperty().addListener((a, b, value) -> {
            RadioButton selected = (RadioButton) value;
            switch (selected.getText().toLowerCase()) {
                case "male":
                    gender = 'M';
                    break;
                case "female":
                    gender = 'F';
                    break;
            }
        });
        month.setItems(MONTH_OPTIONS);
        combobox.setItems(TITLE_OPTIONS);
        scrollpane.setContent(mainVBox);
        maleRadioButton.setToggleGroup(genderToggle);
        femaleRadioButton.setToggleGroup(genderToggle);
    }

    private void initializeQuestionnaire() {
        for (Questionnaire questionnaire : QUESTIONNAIRE_ARRAY) {
            VBox holder = (VBox) questionnaire.getHolder().lookup("#" + QUESTIONNAIRE);
            holder.getChildren().addAll(questionnaire.getQuestionNodes());
            mainVBox.getChildren().add(questionnaire.getHolder());

            for (Question question : questionnaire.getQuestions()) {
                RadioButton left = (RadioButton) question.view.lookup("#" + LEFT_RADIO_BUTTON);
                ToggleGroup toggle = left.getToggleGroup();
                toggle.selectedToggleProperty().addListener((a, b, value) -> {
                    RadioButton selected = (RadioButton) value;
                    switch (selected.getId()) {
                        case LEFT_RADIO_BUTTON:
                            questionnaire.submitAnswer(question, selected.getText());
                            break;
                        case RIGHT_RADIO_BUTTON:
                            questionnaire.submitAnswer(question, selected.getText());
                            break;
                    }
                });
            }

            questionnaire.getAnswers().addListener((Change<? extends String> change) -> {
                List<String> list = (List<String>) change.getList();
                for (int i = 0; i < list.size(); i++) {
                    answers += i + 2 > list.size()
                            ? "[" + list.get(i) + "]"
                            : ",[" + list.get(i) + "]";
                }
            });
        }
    }

    private void initializeDonorIdTextField() {
        donorIdTextField.textProperty().addListener((a, b, value) -> {
            if (value.length() == 6) {
                int y = 0, m = 0, d = 0;
                String ds = "";
                char[] array = value.toCharArray();

                for (int i = 0; i < array.length; i++) {
                    if (i % 2 == 0) {
                        ds = "";
                    }
                    ds = ds + array[i];
                    switch (i + 1) {
                        case 2:
                            y = Integer.parseInt(ds);
                            y += y <= 50 ? 2000 : 1900;
                            break;
                        case 4:
                            m = Integer.parseInt(ds);
                            break;
                        case 6:
                            d = Integer.parseInt(ds);
                            break;
                    }
                }
                dataOfBirthDatePicker.setValue(LocalDate.of(y, m, d));
            } else if (value.length() < 6) {
                dataOfBirthDatePicker.editorProperty().get().clear();
            }
        });
    }

}
