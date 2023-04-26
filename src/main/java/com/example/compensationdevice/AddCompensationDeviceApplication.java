package com.example.compensationdevice;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AddCompensationDeviceApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AddCompensationDeviceApplication.class.getResource("/create-compensation-device.fxml"));
        stage.setTitle("Compensation Device");
        stage.setScene( new Scene(fxmlLoader.load(), 1280, 620));
        stage.show();
    }
}
