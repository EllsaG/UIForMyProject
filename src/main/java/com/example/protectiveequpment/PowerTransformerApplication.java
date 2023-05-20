package com.example.protectiveequpment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PowerTransformerApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PowerTransformerApplication.class.getResource("/create-power-transformer.fxml"));
        stage.setTitle("Power Transformer");
        stage.setScene( new Scene(fxmlLoader.load(), 1280, 620));
        stage.show();
    }
}
