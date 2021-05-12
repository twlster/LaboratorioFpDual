package edu.fpdualjavafx.ejemplofx;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"));
        this.stage = stage;
        this.stage.setTitle("Login");
        this.stage.setScene(scene);
        this.stage.show();
    }

    static void setRoot(String fxml, String title) throws IOException {
        Parent root = loadFXML(fxml);
    	scene.setRoot(root);
    	stage.setTitle(title);
    	stage.sizeToScene();
    	
    }

    static void close() throws IOException {
    	scene.getWindow().hide();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/edu/fpdualjavafx/ejemplofx/frames/"+fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}