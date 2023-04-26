package com.example.ligthinginformation.chooseluminaire;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChooseLuminaireApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ChooseLuminaireApplication.class.getResource("/choose-luminaire.fxml"));
        stage.setTitle("Choose Luminaire");
        stage.setScene( new Scene(fxmlLoader.load(), 530, 440));
        stage.show();
    }

}
