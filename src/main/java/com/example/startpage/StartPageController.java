package com.example.startpage;

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


    public void menuItemFileExitAction(ActionEvent actionEvent) {
        Platform.exit();
    }

    @FXML
    public void addEquipment(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) addEquipment.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/load-calculation.fxml"));
        stage.setTitle("Load Calculation");
        stage.setScene(new Scene(root));


    }
}
