package com.example.protectiveequpment;


import com.example.protectiveequpment.createpowertransformer.ChoosePowerTransformerForResponse;
import com.example.protectiveequpment.createpowertransformer.ForInsertInTableViewForChoosePowerTransformer;
import com.example.protectiveequpment.createpowertransformer.ForInsertInTableViewPowerTransformer;
import com.example.protectiveequpment.createpowertransformer.ForRequestPowerTransformer;
import com.example.response.ErrorResponseMessage;
import com.example.response.PowerTransformerResponse;
import com.example.utils.WrapTextTableCell;
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
import java.util.List;

public class PowerTransformerController {

    @FXML
    public TextField tfId;
    @FXML
    public TextField tfModelOfTransformer;
    @FXML
    public TextField tfFullPowerOfTransformer;
    @FXML
    public TextField tfShortCircuitVoltage;
    @FXML
    public TextField tfIdleLossesOfTransformer;
    @FXML
    public TextField tfLowSideVoltage;
    @FXML
    public TextField tfHighSideVoltage;
    @FXML
    public TextField tfShortCircuitLosses;
    @FXML
    public TextField tfIdleCurrent;


    @FXML
    private TableView<PowerTransformerForResponse> tvChoosenPowerTransformer;
    @FXML
    public TableColumn<ForInsertInTableViewPowerTransformer, Long> colIdOfChoosen;
    @FXML
    public TableColumn<ForInsertInTableViewPowerTransformer, String> colModelOfTransformer;
    @FXML
    public TableColumn <ForInsertInTableViewPowerTransformer, Double>colFullPowerOfTransformer;
    @FXML
    public TableColumn<ForInsertInTableViewPowerTransformer, Double> colShortCircuitVoltage;
    @FXML
    public TableColumn <ForInsertInTableViewPowerTransformer, Double>colIdleLossesOfTransformer;
    @FXML
    public TableColumn<ForInsertInTableViewPowerTransformer,Double > colHighSideVoltage;
    @FXML
    public TableColumn <ForInsertInTableViewPowerTransformer, Double>colLowSideVoltage;
    @FXML
    public TableColumn<ForInsertInTableViewPowerTransformer, Double> colShortCircuitLosses;
    @FXML
    public TableColumn<ForInsertInTableViewPowerTransformer, Double> colIdleCurrent;


    @FXML
    public TableView<ChoosePowerTransformerForResponse> tvForChoosePowerTransformer;
    @FXML
    public TableColumn<ForInsertInTableViewForChoosePowerTransformer, Long> colIdForChoose;
    @FXML
    public TableColumn <ForInsertInTableViewForChoosePowerTransformer, Double>colMinPowerForChoose;

    @FXML
    private Button createCompensationDevice;
    @FXML
    private Button updateCompensationDevice;
    @FXML
    private Button deleteCompensationDevice;

    @FXML
    private Button refreshAllTables;

    @FXML
    private TextArea taMessage;


    @FXML
    void backToStartPage(ActionEvent event) throws IOException {
        Stage stage = (Stage) tfId.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/start-page.fxml"));
        stage.setTitle("Start Page");
        stage.setScene(new Scene(root));
    }

    @FXML
    void menuItemFileExitAction(ActionEvent event) {
        Platform.exit();
    }

    public void compensationDevice(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == createCompensationDevice) {
            createNewPowerTransformer();
        } else if (actionEvent.getSource() == updateCompensationDevice) {
            updatePowerTransformer();
        } else if (actionEvent.getSource() == refreshAllTables) {
            refreshAllTables();
        } else if (actionEvent.getSource() == deleteCompensationDevice) {
            deletePowerTransformer();
        }
    }

    @FXML
    void handleMouseAction(MouseEvent event) {
        PowerTransformerForResponse selectedItem = tvChoosenPowerTransformer.getSelectionModel().getSelectedItem();
        tfId.setText(String.valueOf(selectedItem.getId()));
        tfModelOfTransformer.setText(selectedItem.getModelOfTransformer());
        tfFullPowerOfTransformer.setText(String.valueOf(selectedItem.getFullPowerOfTransformer()));
        tfShortCircuitVoltage.setText(String.valueOf(selectedItem.getShortCircuitVoltage()));
        tfIdleLossesOfTransformer.setText(String.valueOf(selectedItem.getIdleLossesOfTransformer()));
         tfLowSideVoltage.setText(String.valueOf(selectedItem.getLowSideVoltage()));
         tfHighSideVoltage.setText(String.valueOf(selectedItem.getHighSideVoltage()));
        tfShortCircuitLosses.setText(String.valueOf(selectedItem.getShortCircuitLosses()));
         tfIdleCurrent.setText(String.valueOf(selectedItem.getIdleCurrent()));
    }

    public void refreshAllTables() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        HttpGet get = new HttpGet("http://localhost:9999//powertransformer/all");
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
                taMessage.setText("Tables refreshed");
            }
        } catch (HttpHostConnectException e) {
            taMessage.setText("Unable to connect \n" + get.getURI());
        }
    }

    public void createNewPowerTransformer() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String value = makeRequestAsString(objectMapper, "create");


        HttpPost request = new HttpPost("http://localhost:9999//powertransformer/create");
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
                taMessage.setText("Information about power transformer " +
                        "\n with id № " + tfId.getText() +
                        "\nis saved");
            }
        } catch (HttpHostConnectException e) {
            taMessage.setText("Unable to connect \n" + request.getURI());
        }
    }


    public void updatePowerTransformer() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String value = makeRequestAsString(objectMapper, "update");

        HttpPost post = new HttpPost("http://localhost:9999//powertransformer/update");
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
                taMessage.setText("Information about power transformer " +
                        "\n with id № " + tfId.getText() +
                        "\nhas been updated");
            }
        } catch (HttpHostConnectException e) {
            taMessage.setText("Unable to connect \n" + post.getURI());
        }
    }

    public void deletePowerTransformer() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String value = makeRequestAsString(objectMapper, "delete");

        HttpDelete post = new HttpDelete("http://localhost:9999//powertransformer/delete/" + value);
        post.addHeader("content-type", "application/json");

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {
            HttpEntity entity = response.getEntity();
            String responseEntity = EntityUtils.toString(entity);
            try {
                ErrorResponseMessage errorResponseMessage = objectMapper.readValue(responseEntity, ErrorResponseMessage.class);
                taMessage.setText(errorResponseMessage.getMessage());

            } catch (Exception e) {
                showInfo(objectMapper, responseEntity);
                taMessage.setText("Information about power transformer " +
                        "\n with id № " + tfId.getText() +
                        "\nhas been delete");
            }
        } catch (HttpHostConnectException e) {
            taMessage.setText("Unable to connect \n" + post.getURI());
        }
    }


    public String makeRequestAsString(ObjectMapper objectMapper, String requestType) {

        try {
            if (requestType.equals("delete")) {
                return tfId.getText();
            } else {
                ForRequestPowerTransformer forRequestPowerTransformer = new ForRequestPowerTransformer();

                forRequestPowerTransformer.setId(Long.parseLong(tfId.getText()));
                forRequestPowerTransformer.setModelOfTransformer(tfModelOfTransformer.getText());
                forRequestPowerTransformer.setFullPowerOfTransformer(Double.parseDouble(tfFullPowerOfTransformer.getText()));
                forRequestPowerTransformer.setShortCircuitVoltage(Double.parseDouble(tfShortCircuitVoltage.getText()));
                forRequestPowerTransformer.setIdleLossesOfTransformer(Double.parseDouble(tfIdleLossesOfTransformer.getText()));
                forRequestPowerTransformer.setHighSideVoltage(Double.parseDouble(tfHighSideVoltage.getText()));
                forRequestPowerTransformer.setLowSideVoltage(Double.parseDouble(tfLowSideVoltage.getText()));
                forRequestPowerTransformer.setShortCircuitLosses(Double.parseDouble(tfShortCircuitLosses.getText()));
                forRequestPowerTransformer.setIdleCurrent(Double.parseDouble(tfIdleCurrent.getText()));

                return objectMapper.writeValueAsString(forRequestPowerTransformer);
            }
        } catch (Exception e) {
            taMessage.setText("Write values in all fields");
            throw new RuntimeException("Write values in all fields");
        }

    }

    public void showInfo(ObjectMapper objectMapper, String responseEntity) throws JsonProcessingException {

        ObservableList<PowerTransformerForResponse> chooseCompensationDeviceList = getPowerTransformeList(objectMapper, responseEntity);

        colIdOfChoosen.setCellValueFactory(new PropertyValueFactory<>("id"));
        colModelOfTransformer.setCellValueFactory(new PropertyValueFactory<>("modelOfTransformer"));
        colFullPowerOfTransformer.setCellValueFactory(new PropertyValueFactory<>("fullPowerOfTransformer"));
        colShortCircuitVoltage.setCellValueFactory(new PropertyValueFactory<>("shortCircuitVoltage"));
        colIdleLossesOfTransformer.setCellValueFactory(new PropertyValueFactory<>("idleLossesOfTransformer"));
        colLowSideVoltage.setCellValueFactory(new PropertyValueFactory<>("lowSideVoltage"));
        colHighSideVoltage.setCellValueFactory(new PropertyValueFactory<>("highSideVoltage"));
        colShortCircuitLosses.setCellValueFactory(new PropertyValueFactory<>("shortCircuitLosses"));
        colIdleCurrent.setCellValueFactory(new PropertyValueFactory<>("idleCurrent"));

        tvChoosenPowerTransformer.setItems(chooseCompensationDeviceList);

        ObservableList<ChoosePowerTransformerForResponse> choosePowerTransformerForResponses = getPowerTransformerForChooseList(objectMapper, responseEntity);
        colIdForChoose.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMinPowerForChoose.setCellValueFactory(new PropertyValueFactory<>("ratedPowerForChoosingOfTransformer"));

        colModelOfTransformer.setCellFactory((param) -> new WrapTextTableCell());

        tvForChoosePowerTransformer.setItems(choosePowerTransformerForResponses);

    }

    public ObservableList<PowerTransformerForResponse> getPowerTransformeList(ObjectMapper objectMapper, String responseEntity) throws JsonProcessingException {

        ObservableList<PowerTransformerForResponse> observableList = FXCollections.observableArrayList();
        PowerTransformerResponse powerTransformerResponse = objectMapper.readValue(responseEntity, PowerTransformerResponse.class);
        List<PowerTransformerForResponse> compensationDeviceList = powerTransformerResponse.getPowerTransformersList();
        observableList.addAll(compensationDeviceList);

        return observableList;
    }

    public ObservableList<ChoosePowerTransformerForResponse> getPowerTransformerForChooseList(ObjectMapper objectMapper, String responseEntity) throws JsonProcessingException {
        ObservableList<ChoosePowerTransformerForResponse> observableList = FXCollections.observableArrayList();
        PowerTransformerResponse powerTransformerResponse = objectMapper.readValue(responseEntity, PowerTransformerResponse.class);
        List<ChoosePowerTransformerForResponse> forChooseTransformersList = powerTransformerResponse.getForChooseTransformersList();
        observableList.addAll(forChooseTransformersList);
        return observableList;
    }


}


