/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.charts;

import beta.blood.Handler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author aaron
 */
public class PieChartController implements Initializable {

    /**
     * initialises the controller class.
     */
    @FXML
    PieChart pieChart;
    ObservableList data = FXCollections.observableArrayList();
    
//    public void buildPieData() throws SQLException{
//        String sql = "SELECT type, quantity FROM blood";
//        ResultSet rs = DatabaseService.service().executeResultQuery(sql);
//    
//        while(rs.next()) {
//        data.add(new PieChart.Data(rs.getString("type"), rs.getDouble(2)));
//        
//        }
//    }
    
    @FXML
    public void back() {
        Handler.setScene(getClass(), "Request System Report", "/beta/blood/admin/AdminRequestReport.fxml");
    }
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        try {
//            buildPieData();
//        } catch (SQLException ex) {
//            Logger.getLogger(PieChartController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        pieChart.getData().addAll(data);
    }    
    
}
