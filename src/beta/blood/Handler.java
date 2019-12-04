package beta.blood;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Handler {

    public static Parent loadFxml(Class<?> clazz, String path) {
        try {
            return FXMLLoader.load(clazz.getResource(path));
        } catch (IOException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Stage getStage(ActionEvent event) {
        return (Stage) ((Node) event.getSource()).getScene().getWindow();
    }

    public static void changeScene(Class<?> clazz, ActionEvent event, String title, String path) {
        getStage(event).setScene(new Scene(Handler.loadFxml(clazz, path)));
        getStage(event).setTitle(title);
        getStage(event).show();
    }
}
