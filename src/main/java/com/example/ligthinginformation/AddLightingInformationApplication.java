package com.example.ligthinginformation;

import com.example.startinfo.AddAllStartInformationApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AddLightingInformationApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AddAllStartInformationApplication.class.getResource("/create-lighting-information.fxml"));
        stage.setTitle("Lighting Information");
        stage.setScene( new Scene(fxmlLoader.load(), 1280, 620));
        stage.show();
    }
}
