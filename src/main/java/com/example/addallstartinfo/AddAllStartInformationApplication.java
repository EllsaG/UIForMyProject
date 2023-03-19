package com.example.addallstartinfo;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AddAllStartInformationApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AddAllStartInformationApplication.class.getResource("/create-start-information.fxml"));
        stage.setTitle("Start Information");
        stage.setScene( new Scene(fxmlLoader.load(), 1024, 600));
        stage.show();
    }
}
