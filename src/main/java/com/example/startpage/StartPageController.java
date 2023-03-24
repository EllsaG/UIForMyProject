package com.example.startpage;

import com.example.addallstartinfo.AddAllStartInformationApplication;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class StartPageController {
    @FXML
    private Button addEquipment;
    @FXML
    private Button calculation;


    public void menuItemFileExitAction(ActionEvent actionEvent) {
        Platform.exit();
    }

    @FXML
    public void addEquipment(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) addEquipment.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/create-start-information.fxml"));
        stage.setTitle("Start Information");
        stage.setScene(new Scene(root));

    }

    public void calculation(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) calculation.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/create-full-information.fxml"));
        stage.setTitle("Full Information");
        stage.setScene(new Scene(root));
    }


    public void lighting(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) calculation.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/create-lighting-information.fxml"));
        stage.setTitle("Lighting Information");
        stage.setScene(new Scene(root));
    }
}
