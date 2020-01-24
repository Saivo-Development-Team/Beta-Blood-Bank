/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.admin;

import static beta.blood.Helper.isNotEmpty;
import beta.blood.model.Donor;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class AdminRequestReportController implements Initializable {

    @FXML
    LineChart<String, Number> donorsChart;

    ObservableList<String> months = FXCollections.observableArrayList();
    int[] monthCount = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

    ObservableList<Data> monthData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        XYChart.Series<String, Number> donorChart = new XYChart.Series<>();

        months.addAll("January", "February", "March", "April", "May", "June", "July", "August", "September", "October",
                "November", "December");

        Donor.getAll((donors) -> {
            donors.forEach((donor) -> {
                if (donor != null) {
                    String month = donor.getMonth() != null ? donor.getMonth() : "";
                    if (isNotEmpty(month)) {
                        int index = months.indexOf(month);
                        monthCount[index]++;
                    }
                }
            });
        });

        months.forEach((month) -> {
            donorChart.getData().add(new XYChart.Data(month, monthCount[months.indexOf(month)]));
        });

        donorChart.setName("Donations for 2020");

        donorsChart.getData().add(donorChart);
        donorsChart.setScaleY(1);

        System.out.println(Arrays.toString(monthCount));

    }

}
