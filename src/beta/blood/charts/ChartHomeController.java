/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.charts;

import beta.blood.Handler;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


/**
 * FXML Controller class
 *
 * @author aaron
 */



public class ChartHomeController implements Initializable {   

@FXML
Pane pie;
@FXML
Pane bar;
@FXML
Button web;

@FXML
    public void openWebpage() {
        try {
            Desktop.getDesktop().browse(new URL("https://sanbs.org.za/").toURI());
        } catch (IOException | URISyntaxException e) {
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
            pie.getChildren().add(Handler.loadFxml(getClass(), "pieChart.fxml"));
            bar.getChildren().add(Handler.loadFxml(getClass(), "barChart.fxml"));
        }
        
}