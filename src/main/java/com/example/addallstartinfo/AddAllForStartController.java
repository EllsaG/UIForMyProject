package com.example.addallstartinfo;

import com.example.response.ErrorResponseMessage;
import com.example.response.StartInformationResponse;

import com.example.addallstartinfo.createstartinformation.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

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
    TableColumn<ForInsertInTableViewStartInfo, Long> colID;
    @FXML
    TableColumn<ForInsertInTableViewStartInfo, String> colName;
    @FXML
    TableColumn<ForInsertInTableViewStartInfo, Double> colPower;
    @FXML
    TableColumn<ForInsertInTableViewStartInfo, Integer> colAmount;
    @FXML
    TableColumn<ForInsertInTableViewStartInfo, Double> colKi;
    @FXML
    TableColumn<ForInsertInTableViewStartInfo, Double> colCosf;
    @FXML
    TableColumn<ForInsertInTableViewStartInfo, Double> colTgf;
    @FXML
    TableColumn<ForInsertInTableViewStartInfo, Double> colAvgDailyActivePower;
    @FXML
    TableColumn<ForInsertInTableViewStartInfo, Double> colAvgDailyReactivePower;

    @FXML
    Button addEquipment;
    @FXML
    Button refreshTable;
    @FXML
    Button updateEquipment;
    @FXML
    Button deleteEquipment;

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

    public void startInformation(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == addEquipment) {
            createEquipment();
        } else if (actionEvent.getSource() == updateEquipment) {
            updateEquipment();
        } else if (actionEvent.getSource() == refreshTable) {
            refreshTable();
        } else if (actionEvent.getSource() == deleteEquipment) {
            deleteEquipment();
        }
    }

    public void createEquipment() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        String value = makeRequestAsString(objectMapper, "create");


        HttpPost request = new HttpPost("http://localhost:9999//startinformation/create");
        request.addHeader("content-type", "application/json");
        request.setEntity(new StringEntity(value, StandardCharsets.UTF_8));
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            String responseEntity = EntityUtils.toString(entity);
            try {
                ErrorResponseMessage errorResponseMessage = objectMapper.readValue(responseEntity, ErrorResponseMessage.class);
                taMessage.setText(errorResponseMessage.getMessage());

            } catch (Exception e) {
                showInfo(objectMapper, responseEntity);
                taMessage.setText("Information about equipment " +
                        "\n with id № " + tfId.getText() + " is saved");
            }
        } catch (HttpHostConnectException e) {
            taMessage.setText("Unable to connect \n" + request.getURI());
        }
    }

    public void refreshTable() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        HttpGet get = new HttpGet("http://localhost:9999//startinformation/all");
        get.addHeader("content-type", "application/json");

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(get)) {
            HttpEntity entity = response.getEntity();
            String responseEntity = EntityUtils.toString(entity);
            showInfo(objectMapper, responseEntity);

            try {
                ErrorResponseMessage errorResponseMessage = objectMapper.readValue(responseEntity, ErrorResponseMessage.class);
                taMessage.setText(errorResponseMessage.getMessage());
            } catch (Exception e) {
                taMessage.setText("Table refreshed");
            }
        } catch (HttpHostConnectException e) {
            taMessage.setText("Unable to connect \n" + get.getURI());
        }
    }

    public void updateEquipment() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String value = makeRequestAsString(objectMapper, "update");

        HttpPost post = new HttpPost("http://localhost:9999//startinformation/update");
        post.addHeader("content-type", "application/json");
        post.setEntity(new StringEntity(value, StandardCharsets.UTF_8));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {
            HttpEntity entity = response.getEntity();
            String responseEntity = EntityUtils.toString(entity);
            try {
                ErrorResponseMessage errorResponseMessage = objectMapper.readValue(responseEntity, ErrorResponseMessage.class);
                taMessage.setText(errorResponseMessage.getMessage());

            } catch (Exception e) {
                showInfo(objectMapper, responseEntity);
                taMessage.setText("Information about equipment" +
                        "\n with id № " + tfId.getText() + " has been updated");
            }
        } catch (HttpHostConnectException e) {
            taMessage.setText("Unable to connect \n" + post.getURI());
        }
    }

    private void deleteEquipment() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String value = makeRequestAsString(objectMapper, "delete");

        HttpDelete request = new HttpDelete("http://localhost:9999//startinformation/delete" + "/" + value);
        request.addHeader("content-type", "application/json");
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            String responseEntity = EntityUtils.toString(entity);
            try {
                ErrorResponseMessage errorResponseMessage = objectMapper.readValue(responseEntity, ErrorResponseMessage.class);
                taMessage.setText(errorResponseMessage.getMessage());

            } catch (Exception e) {
                showInfo(objectMapper, responseEntity);
                taMessage.setText("Information about equipment " +
                        "\n with id №" + tfId.getText() + " has been deleted");
            }
        } catch (HttpHostConnectException e) {
            taMessage.setText("Unable to connect \n" + request.getURI());

        }
    }


    public void handleMouseAction(MouseEvent mouseEvent) {
        StartInformationResponse selectedItem  = tvEquipments.getSelectionModel().getSelectedItem();

        tfId.setText(String.valueOf(selectedItem.getStartInformId()));
        tfName.setText(selectedItem.getName());
        tfPower.setText(String.valueOf(selectedItem.getPower()));
        tfAmount.setText(String.valueOf(selectedItem.getAmount()));
        tfKi.setText(String.valueOf(selectedItem.getKi()));
        tfCosf.setText(String.valueOf(selectedItem.getCosf()));
        tfTgf.setText(String.valueOf(selectedItem.getTgf()));
    }















    public String makeRequestAsString(ObjectMapper objectMapper, String requestType) {
        try {
            if (requestType.equals("delete")) {
                return tfId.getText();
            } else {
                ForRequestStartInformation forRequestStartInformation = new ForRequestStartInformation();

                forRequestStartInformation.setStartInformId(Long.valueOf(tfId.getText()));
                forRequestStartInformation.setName(tfName.getText().trim());
                forRequestStartInformation.setPower(Double.valueOf(tfPower.getText()));
                forRequestStartInformation.setAmount(Integer.valueOf(tfAmount.getText()));
                forRequestStartInformation.setKi(Double.valueOf(tfKi.getText()));
                forRequestStartInformation.setCosf(Double.valueOf(tfCosf.getText()));
                forRequestStartInformation.setTgf(Double.valueOf(tfTgf.getText()));

                return objectMapper.writeValueAsString(forRequestStartInformation);
            }

        } catch (Exception e) {
            taMessage.setText("Write values in all fields");
            throw new RuntimeException("Write values in all fields");
        }

    }

    public ObservableList<StartInformationResponse> getStartInformationList(ObjectMapper objectMapper, String responseEntity) throws JsonProcessingException {

        ObservableList<StartInformationResponse> observableList = FXCollections.observableArrayList();

        ForInsertInTableViewStartInfo forInsertInTableViewStartInfo = objectMapper.readValue(responseEntity, ForInsertInTableViewStartInfo.class);

        for (int i = 0; i < forInsertInTableViewStartInfo.getList().size(); i++) {
            observableList.add(forInsertInTableViewStartInfo.getList().get(i));
        }
        return observableList;
    }

    public void showInfo(ObjectMapper objectMapper, String responseEntity) throws JsonProcessingException {
        ObservableList<StartInformationResponse> list = getStartInformationList(objectMapper, responseEntity);

        colID.setCellValueFactory(new PropertyValueFactory<ForInsertInTableViewStartInfo, Long>("startInformId"));
        colName.setCellValueFactory(new PropertyValueFactory<ForInsertInTableViewStartInfo, String>("name"));
        colPower.setCellValueFactory(new PropertyValueFactory<ForInsertInTableViewStartInfo, Double>("power"));
        colAmount.setCellValueFactory(new PropertyValueFactory<ForInsertInTableViewStartInfo, Integer>("amount"));
        colKi.setCellValueFactory(new PropertyValueFactory<ForInsertInTableViewStartInfo, Double>("ki"));
        colCosf.setCellValueFactory(new PropertyValueFactory<ForInsertInTableViewStartInfo, Double>("cosf"));
        colTgf.setCellValueFactory(new PropertyValueFactory<ForInsertInTableViewStartInfo, Double>("tgf"));
        colAvgDailyActivePower.setCellValueFactory(new PropertyValueFactory<ForInsertInTableViewStartInfo, Double>("avgDailyActivePower"));
        colAvgDailyReactivePower.setCellValueFactory(new PropertyValueFactory<ForInsertInTableViewStartInfo, Double>("avgDailyReactivePower"));

        tvEquipments.setItems(list);

    }



}
