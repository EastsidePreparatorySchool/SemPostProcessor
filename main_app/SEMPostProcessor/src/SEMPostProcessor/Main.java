/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SEMPostProcessor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author jfellows
 */
public class Main extends Application {
    private static Stage stage;
    
     private void setStage(Stage stage) {
        Main.stage = stage;
    }

    static public Stage getStage() {
        return Main.stage;
    }
    
    @Override
    public void start(Stage stage) throws Exception {  
        setStage(stage);
        Parent root = FXMLLoader.load(getClass().getResource("layout.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());

        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setTitle("SEM Post Processor");

        stage.show();
    }

    /**
     * @return 
     */
    
    public static void main(String[] args) {
        launch(args);
    }

}
