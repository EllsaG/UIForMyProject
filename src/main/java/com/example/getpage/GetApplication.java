package com.example.getpage;

import com.example.uiformyproject.LoadCalculationApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GetApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GetApplication.class.getResource("/get-start-information.fxml"));
        stage.setTitle("Get Start Information");
        stage.setScene( new Scene(fxmlLoader.load(), 1024, 600));
        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }
}
