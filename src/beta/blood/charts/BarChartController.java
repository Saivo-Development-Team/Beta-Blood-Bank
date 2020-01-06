/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.charts;

import beta.blood.Handler;
import beta.blood.database.DatabaseService;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

/**
 * FXML Controller class
 *
 * @author aaron
 */
public class BarChartController implements Initializable {

    @FXML
    BarChart barChart;
    ObservableList data = FXCollections.observableArrayList();
    
    
    public void buildBarData() throws SQLException {
    String sql = "SELECT type, quantity FROM blood";
    ResultSet rs = DatabaseService.service().executeQuery(sql);
    
    XYChart.Series<String, Double> series = new XYChart.Series<>();
    
    while(rs.next()) {
        series.getData().add(new XYChart.Data<>(rs.getString("type"), rs.getDouble(2)));
        
        }
    barChart.getData().add(series);
    
    }
    
    @FXML
    public void back() {
        Handler.setScene(getClass(), "Request System Report", "/beta/blood/admin/AdminRequestReport.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            buildBarData();
        } catch (SQLException ex) {
            Logger.getLogger(BarChartController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }    
    
}
