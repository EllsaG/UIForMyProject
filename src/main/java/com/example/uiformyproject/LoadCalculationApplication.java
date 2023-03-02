package com.example.uiformyproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoadCalculationApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoadCalculationApplication.class.getResource("/load-calculation.fxml"));
        stage.setTitle("Load Calculation");
        stage.setScene( new Scene(fxmlLoader.load(), 1024, 600));
        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }
}