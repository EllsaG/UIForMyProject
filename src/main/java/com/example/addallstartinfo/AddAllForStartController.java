package com.example.addallstartinfo;

import com.example.response.ErrorResponseMessage;
import com.example.response.StartInformationResponse;

import com.example.addallstartinfo.createstartinformation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.impl.PropertyValue;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
import java.util.Collections;
import java.util.List;

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
    TableView<StartInformationResponse> tvEquipments;
    @FXML
    TableColumn<ForInsertInTableView, Long> colID;
    @FXML
    TableColumn<ForInsertInTableView, String> colName;
    @FXML
    TableColumn<ForInsertInTableView, Double> colPower;
    @FXML
    TableColumn<ForInsertInTableView, Integer> colAmount;
    @FXML
    TableColumn<ForInsertInTableView, Double> colKi;
    @FXML
    TableColumn<ForInsertInTableView, Double> colCosf;
    @FXML
    TableColumn<ForInsertInTableView, Double> colTgf;
    @FXML
    TableColumn<ForInsertInTableView, Double> colAvgDailyActivePower;
    @FXML
    TableColumn<ForInsertInTableView, Double> colAvgDailyReactivePower;

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
        try {

            forRequestStartInformation.setStartInformId(Long.valueOf(tfId.getText()));
            forRequestStartInformation.setName(tfName.getText().trim());
            forRequestStartInformation.setPower(Double.valueOf(tfPower.getText()));
            forRequestStartInformation.setAmount(Integer.valueOf(tfAmount.getText()));
            forRequestStartInformation.setKi(Double.valueOf(tfKi.getText()));
            forRequestStartInformation.setCosf(Double.valueOf(tfCosf.getText()));
            forRequestStartInformation.setTgf(Double.valueOf(tfTgf.getText()));
        } catch (Exception e) {
            taMessage.setText("Write values in all fields");
            throw new RuntimeException("Write values in all fields");
        }

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
                ObservableList<StartInformationResponse> startInformationResponses = refreshTable(objectMapper, responseEntity);
                tvEquipments.setItems(startInformationResponses);

                taMessage.setText("Information about new equipment with id № " + forRequestStartInformation.getStartInformId() +
                        "\n  name: " + forRequestStartInformation.getName() +
                        "\n  power: " + forRequestStartInformation.getPower() +
                        "\n  amount: " + forRequestStartInformation.getAmount() +
                        "\n  ki: " + forRequestStartInformation.getKi() +
                        "\n  cosf: " + forRequestStartInformation.getCosf() +
                        "\n  tgf: " + forRequestStartInformation.getTgf() +
                        "\nis saved");
            }
        } catch (HttpHostConnectException e) {
            taMessage.setText("Unable to connect \n" + post.getURI());
        }

    }

    public void getStartInformation(ActionEvent actionEvent) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        HttpGet get = new HttpGet("http://localhost:9999//startinformation/all");
        get.addHeader("content-type", "application/json");

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(get)) {
            HttpEntity entity = response.getEntity();
            String responseEntity = EntityUtils.toString(entity);
            ObservableList<StartInformationResponse> startInformationResponses = refreshTable(objectMapper, responseEntity);
            tvEquipments.setItems(startInformationResponses);
            try {
                taMessage.setText("Table refreshed");
            } catch (Exception e) {
                ErrorResponseMessage errorResponseMessage = objectMapper.readValue(responseEntity, ErrorResponseMessage.class);
                taMessage.setText(errorResponseMessage.getMessage());
            }
        } catch (HttpHostConnectException e) {
            taMessage.setText("Unable to connect \n" + get.getURI());
        }
    }

    public ObservableList<StartInformationResponse> refreshTable(ObjectMapper objectMapper, String responseEntity) throws JsonProcessingException {

        colID.setCellValueFactory(new PropertyValueFactory<ForInsertInTableView, Long>("startInformId"));
        colName.setCellValueFactory(new PropertyValueFactory<ForInsertInTableView, String>("name"));
        colPower.setCellValueFactory(new PropertyValueFactory<ForInsertInTableView, Double>("power"));
        colAmount.setCellValueFactory(new PropertyValueFactory<ForInsertInTableView, Integer>("amount"));
        colKi.setCellValueFactory(new PropertyValueFactory<ForInsertInTableView, Double>("ki"));
        colCosf.setCellValueFactory(new PropertyValueFactory<ForInsertInTableView, Double>("cosf"));
        colTgf.setCellValueFactory(new PropertyValueFactory<ForInsertInTableView, Double>("tgf"));
        colAvgDailyActivePower.setCellValueFactory(new PropertyValueFactory<ForInsertInTableView, Double>("avgDailyActivePower"));
        colAvgDailyReactivePower.setCellValueFactory(new PropertyValueFactory<ForInsertInTableView, Double>("avgDailyReactivePower"));

        ObservableList<StartInformationResponse> observableList = FXCollections.observableArrayList();

        ForInsertInTableView forInsertInTableView = objectMapper.readValue(responseEntity, ForInsertInTableView.class);

        for (int i = 0; i < forInsertInTableView.getList().size(); i++) {
            observableList.add(forInsertInTableView.getList().get(i));
        }
        return observableList;

    }
}
