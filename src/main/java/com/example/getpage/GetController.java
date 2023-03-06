package com.example.getpage;

import com.example.response.ErrorResponseMessage;
import com.example.response.StartInformationResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


public class GetController {
    @FXML
    TextField startInformId;
    @FXML
    Text responseGet;

    public void menuItemFileExitAction(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void backToStartPage(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) startInformId.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/start-page.fxml"));
        stage.setTitle("Start Page");
        stage.setScene(new Scene(root));
    }

    public void getStartInformation(ActionEvent actionEvent) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Long aLong = Long.valueOf(startInformId.getText());

        HttpGet get = new HttpGet("http://localhost:9999//startinformation/" + aLong);

        get.addHeader("content-type", "application/json");


        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(get)) {
            HttpEntity entity = response.getEntity();
            String responseEntity = EntityUtils.toString(entity);
            try {
                StartInformationResponse startIR = objectMapper.readValue(responseEntity, StartInformationResponse.class);
                responseGet.setText("Information about equipment № " + startIR.getStartInformId() +
                        "\n  name: " + startIR.getName() +
                        "\n  power: " + startIR.getPower() +
                        "\n  amount: " + startIR.getAmount() +
                        "\n  ki: " + startIR.getKi() +
                        "\n  cosf: " + startIR.getCosf() +
                        "\n  tgf: " + startIR.getTgf());
            } catch (Exception e) {
                ErrorResponseMessage errorResponseMessage = objectMapper.readValue(responseEntity, ErrorResponseMessage.class);
                responseGet.setText(errorResponseMessage.getMessage());
            }
        } catch (HttpHostConnectException e) {
            responseGet.setText("Unable to connect " + get.getURI());
        }
    }
}
