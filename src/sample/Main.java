package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Settings settings = new Settings();
        settings.initializeSettings();

        Parent root = FXMLLoader.load(getClass().getResource("windows/mainwindow.fxml"));
        primaryStage.setTitle("Java Core - финальное задание");
        Scene scene = new Scene(root, 900, 650);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
