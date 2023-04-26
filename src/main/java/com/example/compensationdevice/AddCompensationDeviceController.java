package com.example.compensationdevice;


import com.example.compensationdevice.createcompensationdevice.*;
import com.example.response.CompensationDeviceResponse;
import com.example.response.ErrorResponseMessage;
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

public class AddCompensationDeviceController {

    @FXML
    public TextField tfId;
    @FXML
    public TextField tfModelOfCompensationDevice;
    @FXML
    public TextField tfReactivePowerOfCompensationDevice;

    @FXML
    private TableView<CompensationDeviceForResponse> tvChoosenCompensationDevice;
    @FXML
    public TableColumn<ForInsertInTableViewCompensationDevice, Long> colIdOfChoosen;
    @FXML
    public TableColumn<ForInsertInTableViewCompensationDevice, String> colCompensationDeviceModel;
    @FXML
    public TableColumn<ForInsertInTableViewCompensationDevice, Double> colCompensationDeviceReactivePower;
    @FXML
    public TableView<ChooseCompensationDeviceForResponse> tvForChooseCompensationDevice;
    @FXML
    public TableColumn<ForInsertInTableViewForChooseCompensationDevice, Long> colIdForChoose;
    @FXML
    public TableColumn<ForInsertInTableViewForChooseCompensationDevice, String> colParentBusbarForChoose;
    @FXML
    public TableColumn<ForInsertInTableViewForChooseCompensationDevice, Double> colMinForChoose;
    @FXML
    public TableColumn<ForInsertInTableViewForChooseCompensationDevice, Double> colMaxForChoose;

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
            createNewCompensationDevice();
        } else if (actionEvent.getSource() == updateCompensationDevice) {
            updateCompensationDevice();
        } else if (actionEvent.getSource() == refreshAllTables) {
            refreshAllTables();
        } else if (actionEvent.getSource() == deleteCompensationDevice) {
            deleteCompensationDevice();
        }
    }

    @FXML
    void handleMouseAction(MouseEvent event) {
        CompensationDeviceForResponse selectedItem = tvChoosenCompensationDevice.getSelectionModel().getSelectedItem();
        tfId.setText(String.valueOf(selectedItem.getId()));
        tfModelOfCompensationDevice.setText(selectedItem.getModelOfCompensationDevice());
        tfReactivePowerOfCompensationDevice.setText(String.valueOf(selectedItem.getReactivePowerOfCompensationDevice()));

    }

    public void refreshAllTables() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        HttpGet get = new HttpGet("http://localhost:9999//compensationdevice/all");
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

    public void createNewCompensationDevice() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String value = makeRequestAsString(objectMapper, "create");


        HttpPost request = new HttpPost("http://localhost:9999//compensationdevice/create");
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
                taMessage.setText("Information about compensation device " +
                        "\n with id № " + tfId.getText() +
                        "\nis saved");
            }
        } catch (HttpHostConnectException e) {
            taMessage.setText("Unable to connect \n" + request.getURI());
        }
    }


    public void updateCompensationDevice() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String value = makeRequestAsString(objectMapper, "create");

        HttpPost post = new HttpPost("http://localhost:9999//compensationdevice/update");
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
                taMessage.setText("Information about compensation device" +
                        "\n with id № " + tfId.getText() +
                        "\nhas been updated");
            }
        } catch (HttpHostConnectException e) {
            taMessage.setText("Unable to connect \n" + post.getURI());
        }
    }

    public void deleteCompensationDevice() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String value = makeRequestAsString(objectMapper, "delete");

        HttpDelete post = new HttpDelete("http://localhost:9999//compensationdevice/delete/" + value);
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
                taMessage.setText("Information about compensation device" +
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
                ForRequestCompensationDevice forRequestCompensationDevice = new ForRequestCompensationDevice();

                forRequestCompensationDevice.setId(Long.parseLong(tfId.getText()));
                forRequestCompensationDevice.setNameOfCompensationDevice(tfModelOfCompensationDevice.getText());
                forRequestCompensationDevice.setPowerOfCompensatingDevice(Double.parseDouble(tfReactivePowerOfCompensationDevice.getText()));

                return objectMapper.writeValueAsString(forRequestCompensationDevice);
            }
        } catch (Exception e) {
            taMessage.setText("Write values in all fields");
            throw new RuntimeException("Write values in all fields");
        }

    }

    public void showInfo(ObjectMapper objectMapper, String responseEntity) throws JsonProcessingException {

        ObservableList<CompensationDeviceForResponse> chooseCompensationDeviceList = getCompensationDeviceList(objectMapper, responseEntity);

        colIdOfChoosen.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCompensationDeviceModel.setCellValueFactory(new PropertyValueFactory<>("modelOfCompensationDevice"));
        colCompensationDeviceReactivePower.setCellValueFactory(new PropertyValueFactory<>("reactivePowerOfCompensationDevice"));

        tvChoosenCompensationDevice.setItems(chooseCompensationDeviceList);

        ObservableList<ChooseCompensationDeviceForResponse> compensationDeviceForChooseList = getCompensationDeviceForChooseList(objectMapper, responseEntity);
        colIdForChoose.setCellValueFactory(new PropertyValueFactory<>("id"));
        colParentBusbarForChoose.setCellValueFactory(new PropertyValueFactory<>(""));
        colMinForChoose.setCellValueFactory(new PropertyValueFactory<>("minPowerOfCompensatingDevice"));
        colMaxForChoose.setCellValueFactory(new PropertyValueFactory<>("maxPowerOfCompensatingDevice"));

        colParentBusbarForChoose.setCellFactory((param) -> new WrapTextTableCell());

        tvForChooseCompensationDevice.setItems(compensationDeviceForChooseList);

    }

    public ObservableList<CompensationDeviceForResponse> getCompensationDeviceList(ObjectMapper objectMapper, String responseEntity) throws JsonProcessingException {

        ObservableList<CompensationDeviceForResponse> observableList = FXCollections.observableArrayList();
        CompensationDeviceResponse compensationDeviceResponse = objectMapper.readValue(responseEntity, CompensationDeviceResponse.class);
        List<CompensationDeviceForResponse> compensationDeviceList = compensationDeviceResponse.getCompensationDeviceList();
        observableList.addAll(compensationDeviceList);

        return observableList;
    }

    public ObservableList<ChooseCompensationDeviceForResponse> getCompensationDeviceForChooseList(ObjectMapper objectMapper, String responseEntity) throws JsonProcessingException {
        ObservableList<ChooseCompensationDeviceForResponse> observableList = FXCollections.observableArrayList();
        CompensationDeviceResponse compensationDeviceResponse = objectMapper.readValue(responseEntity, CompensationDeviceResponse.class);
        List<ChooseCompensationDeviceForResponse> forChooseCompensationDeviceList = compensationDeviceResponse.getForChooseCompensationDeviceList();
        observableList.addAll(forChooseCompensationDeviceList);
        return observableList;
    }


}


