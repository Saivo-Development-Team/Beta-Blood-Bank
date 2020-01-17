package beta.blood;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
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

    public static class ResultHolder<T> {

        public T result;
    }

    public static SQLExceptionHandler sqlExceptionHandler() {
        return (statement, type, query, funcation) -> {
            try {
                switch (type) {
                    case RESULT:
                        funcation.cb(statement.executeQuery(query));
                        break;
                    case INSERT:
                    case UPDATE:
                    case DELETE:
                        funcation.cb(statement.executeUpdate(query));
                        break;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
            }
        };
    }

    public enum QueryType {
        RESULT, INSERT, UPDATE, DELETE
    }

    public interface SQLExceptionHandler {

        void runCatching(Statement statement, QueryType type, String query, Function function);
    }

    public interface Function<T> {

        void cb(T result);
    }
}
