package com.example.addallfullinformation;

import com.example.addallstartinfo.AddAllForStartApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AddAllFullInformationApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AddAllForStartApplication.class.getResource("/create-full-information.fxml"));
        stage.setTitle("Full Information");
        stage.setScene( new Scene(fxmlLoader.load(), 1400, 620));
        stage.show();
    }
}
