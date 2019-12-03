package beta.blood;

import javafx.stage.Stage;

public class ViewHandler{
    Stage stage;
    private static ViewHandler instance;
    
    private ViewHandler(){
        
    }
    
    public ViewHandler(Stage stage){
        this.stage = stage;
    }
}