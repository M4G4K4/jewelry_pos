package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFXApplication5 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
	Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml")); // Ecra login 
	
	Scene scene = new Scene(root);
	stage.setTitle("Login");
        
        
        stage.setScene(scene);
	stage.show();
        stage.setResizable(false);
    }

    public static void main(String[] args) {
	launch(args);
        
        
        
        
    }
    
}
