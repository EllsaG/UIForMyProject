package com.example.startpage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartPageApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(StartPageApplication.class.getResource("/start-page.fxml"));
        primaryStage.setTitle("Start Page");
        primaryStage.setScene( new Scene(fxmlLoader.load(), 1024, 600));
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch();
    }
}
