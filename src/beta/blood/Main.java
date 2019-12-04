/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beta.blood;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Aaron
 * @author Ian Mubangizi
 */
public class Main extends Application {
    protected static Stage window;

    @Override
    public void start(Stage stage) throws Exception {
        Main.window = stage;
        Handler.setScene(getClass(), "Beta Blood", "/beta/blood/auth/Login.fxml");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
