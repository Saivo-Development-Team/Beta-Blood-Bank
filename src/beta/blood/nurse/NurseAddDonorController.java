/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.nurse;

import beta.blood.Handler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;

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
    Button add;

    @FXML
    RadioButton fsex, msex,
            q1y, q1n,
            q2y, q2n,
            q3y, q3n,
            q4y, q4n,
            q5y, q5n,
            q6y, q6n,
            q7y, q7n,
            q8y, q8n,
            q9y, q9n,
            q10y, q10n,
            q11y, q11n,
            q12y, q12n,
            q13y, q13n,
            q14y, q14n;

    @FXML
    RadioButton[] radios = {};

    ObservableList<String> options = FXCollections.observableArrayList(
            "MR",
            "MS",
            "MRS"
    );

    @FXML
    private void back() {
        Handler.setScene(getClass(), "Nurse Home", "NurseHome.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //cant get scroll pane to show donor questionnaire without an error
        ToggleGroup toggle = new ToggleGroup();
        msex.setToggleGroup(toggle);
        fsex.setToggleGroup(toggle);

        scrollpane.setContent(Handler.loadFxml(getClass(), "DonorQuestionnaire.fxml"));
        combobox.setItems(options);
    }

}
