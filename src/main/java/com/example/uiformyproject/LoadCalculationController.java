package com.example.uiformyproject;

import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.application.Platform;
import javafx.css.StyleableProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoadCalculationController implements Initializable {

    @FXML
    TextField startInformId;
    @FXML
    TextField name;
    @FXML
    TextField power;
    @FXML
    TextField amount;
    @FXML
    TextField ki;
    @FXML
    TextField cosf;
    @FXML
    TextField tgf;

    public void menuItemFileExitAction(ActionEvent actionEvent) {
        Platform.exit();
    }


    public void addToStartInformationDB(ActionEvent actionEvent) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        ForRequest forRequest = new ForRequest();

        forRequest.setStartInformId(Long.valueOf(startInformId.getText()));
        forRequest.setName(name.getText());
        forRequest.setPower(Double.valueOf(power.getText()));
        forRequest.setAmount(Integer.valueOf(amount.getText()));
        forRequest.setKi(Double.valueOf(ki.getText()));
        forRequest.setCosf(Double.valueOf(cosf.getText()));
        forRequest.setTgf(Double.valueOf(tgf.getText()));
        String value = objectMapper.writeValueAsString(forRequest);

        System.out.println(value);

        String request = "http://localhost:9999//startinformation/create";
        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection) new URL(request).openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(2000);
            connection.connect();
            connection.getRequestProperty(value);

        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}