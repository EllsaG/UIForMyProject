package com.example.addallstartinfo;

import com.example.response.ErrorResponseMessage;
import com.example.response.StartInformationResponse;

import com.example.addallstartinfo.createstartinformation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class AddAllForStartController {
    @FXML
    TextField tfId;
    @FXML
    TextField tfName;
    @FXML
    TextField tfPower;
    @FXML
    TextField tfAmount;
    @FXML
    TextField tfKi;
    @FXML
    TextField tfCosf;
    @FXML
    TextField tfTgf;

    @FXML
    TableColumn <?,?> colID;
    @FXML
    TableColumn <?,?> colName;
    @FXML
    TableColumn <?,?> colPower;
    @FXML
    TableColumn <?,?> colAmount;
    @FXML
    TableColumn <?,?> colKi;
    @FXML
    TableColumn <?,?> colCosf;
    @FXML
    TableColumn <?,?> colTgf;
    @FXML
    TableColumn <?,?> colAvgDailyActivePower;
    @FXML
    TableColumn <?,?> colAvgDailyReactivePower;

    @FXML
    TextArea taMessage;

    public void menuItemFileExitAction(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void backToStartPage(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) tfId.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/start-page.fxml"));
        stage.setTitle("Start Page");
        stage.setScene(new Scene(root));
    }

    public void addEquipment(ActionEvent actionEvent) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ForRequestStartInformation forRequestStartInformation = new ForRequestStartInformation();

        forRequestStartInformation.setStartInformId(Long.valueOf(tfId.getText()));
        forRequestStartInformation.setName(tfName.getText());
        forRequestStartInformation.setPower(Double.valueOf(tfPower.getText()));
        forRequestStartInformation.setAmount(Integer.valueOf(tfAmount.getText()));
        forRequestStartInformation.setKi(Double.valueOf(tfKi.getText()));
        forRequestStartInformation.setCosf(Double.valueOf(tfCosf.getText()));
        forRequestStartInformation.setTgf(Double.valueOf(tfTgf.getText()));

        String value = objectMapper.writeValueAsString(forRequestStartInformation);

        HttpPost post = new HttpPost("http://localhost:9999//startinformation/create");
        post.addHeader("content-type", "application/json");
        post.setEntity(new StringEntity(value));
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {
            HttpEntity entity = response.getEntity();
            String responseEntity = EntityUtils.toString(entity);
            try {
                ErrorResponseMessage errorResponseMessage = objectMapper.readValue(responseEntity, ErrorResponseMessage.class);
                taMessage.setText(errorResponseMessage.getMessage());

            } catch (Exception e) {
                StartInformationResponse startIR = objectMapper.readValue(responseEntity, StartInformationResponse.class);
                colID.setText("" + startIR.getStartInformId());
                colName.setText(startIR.getName());
                colPower.setText("" + startIR.getPower());
                colAmount.setText("" + startIR.getAmount());
                colKi.setText("" + startIR.getKi());
                colCosf.setText("" + startIR.getCosf());
                colTgf.setText("" + startIR.getTgf());

                taMessage.setText(responseEntity);
            }
        } catch (HttpHostConnectException e) {
            taMessage.setText("Unable to connect \n" + post.getURI());
        }

    }

    public void getStartInformation(ActionEvent actionEvent) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Long aLong = Long.valueOf(tfId.getText());

        HttpGet get = new HttpGet("http://localhost:9999//startinformation/" + aLong);

        get.addHeader("content-type", "application/json");


        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(get)) {
            HttpEntity entity = response.getEntity();
            String responseEntity = EntityUtils.toString(entity);
            try {
                StartInformationResponse startIR = objectMapper.readValue(responseEntity, StartInformationResponse.class);

                taMessage.setText("Information about equipment № " + startIR.getStartInformId() +
                        "\n  name: " + startIR.getName() +
                        "\n  power: " + startIR.getPower() +
                        "\n  amount: " + startIR.getAmount() +
                        "\n  ki: " + startIR.getKi() +
                        "\n  cosf: " + startIR.getCosf() +
                        "\n  tgf: " + startIR.getTgf());
            } catch (Exception e) {
                ErrorResponseMessage errorResponseMessage = objectMapper.readValue(responseEntity, ErrorResponseMessage.class);
                taMessage.setText(errorResponseMessage.getMessage());
            }
        } catch (HttpHostConnectException e) {
            taMessage.setText("Unable to connect \n" + get.getURI());
        }
    }
}
