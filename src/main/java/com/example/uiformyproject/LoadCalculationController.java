package com.example.uiformyproject;

import com.example.uiformyproject.createstartinformation.ForRequestStartInformation;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Platform;
import javafx.event.ActionEvent;


import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class LoadCalculationController {
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
    public void backToStartPage(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) startInformId.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/start-page.fxml"));
        stage.setTitle("Start Page");
        stage.setScene(new Scene(root));
    }

    public void addToStartInformationDB(ActionEvent actionEvent) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ForRequestStartInformation forRequestStartInformation = new ForRequestStartInformation();

        forRequestStartInformation.setStartInformId(Long.valueOf(startInformId.getText()));
        forRequestStartInformation.setName(name.getText());
        forRequestStartInformation.setPower(Double.valueOf(power.getText()));
        forRequestStartInformation.setAmount(Integer.valueOf(amount.getText()));
        forRequestStartInformation.setKi(Double.valueOf(ki.getText()));
        forRequestStartInformation.setCosf(Double.valueOf(cosf.getText()));
        forRequestStartInformation.setTgf(Double.valueOf(tgf.getText()));

        String value = objectMapper.writeValueAsString(forRequestStartInformation);

        String result = "";
        HttpPost post = new HttpPost("http://localhost:9999//startinformation/create");
        post.addHeader("content-type", "application/json");
        post.setEntity(new StringEntity(value));
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {
            result = EntityUtils.toString(response.getEntity());
        } catch (HttpHostConnectException e) {
            throw new RuntimeException("Unable to connect " + post.getURI());
        }
        System.out.println(result);
    }



}