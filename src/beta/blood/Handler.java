package beta.blood;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
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

    public static Stage getWindow() {
        return Main.window;
    }

    public static void setScene(Class<?> clazz, String title, String path) {
        getWindow().setScene(new Scene(loadFxml(clazz, path)));
        getWindow().setTitle(title);
        getWindow().show();
    }
}
