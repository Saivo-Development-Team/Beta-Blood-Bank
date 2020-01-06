/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood.admin;

import beta.blood.Handler;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AdminHomeController implements Initializable {
    
    @FXML
    Tab dash;
    @FXML
    Tab add;
    @FXML
    Tab sysrep;
    @FXML
    Tab modrep;
    
    @FXML
    private void changeDetails() {
        Handler.setScene(getClass(), "Change Details", "AdminChangeDetails.fxml");
    }
    
    @FXML
    private void logout() throws IOException{
    int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out", "Logout", YES_NO_OPTION);
    if(result == JOptionPane.YES_OPTION){
        Handler.setScene(getClass(), "Beta Blood", "/beta/blood/auth/Login.fxml");
    }
    }
    
    @FXML
    private void goToGraph() {
        Handler.setScene(getClass(), "Graph", "/beta/blood/charts/pieChart.fxml");
    }
    
    @FXML
    private void goToGraph2() {
        Handler.setScene(getClass(), "Graph", "/beta/blood/charts/barChart.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        dash.setContent(Handler.loadFxml(getClass(), "/beta/blood/charts/chartHome.fxml"));
        add.setContent(Handler.loadFxml(getClass(), "AdminAddUser.fxml"));
        sysrep.setContent(Handler.loadFxml(getClass(), "AdminRequestReport.fxml"));
        modrep.setContent(Handler.loadFxml(getClass(), "AdminModifyRepos.fxml"));
    }
}
