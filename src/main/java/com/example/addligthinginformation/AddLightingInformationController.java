package com.example.addligthinginformation;


import com.example.addallfullinformation.createfullinformation.ForInsertInTableViewFullInfo;
import com.example.addallfullinformation.createfullinformation.ForRequestFullInformation;
import com.example.addallfullinformation.createfullinformation.ListInputEquipment;
import com.example.addligthinginformation.createligthinformation.ForInsertInTableViewForChooseLuminaires;
import com.example.addligthinginformation.createligthinformation.ForInsertInTableViewLightingInfo;
import com.example.addligthinginformation.createligthinformation.ForRequestLightingInfo;
import com.example.response.ErrorResponseMessage;
import com.example.response.FullInformationResponse;
import com.example.response.LightInformationResponse;
import com.example.response.ListInputEquipmentResponse;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AddLightingInformationController {

    @FXML
    private Button addEquipment;

    @FXML
    private TableColumn<?, ?> colCosf;

    @FXML
    private TableColumn<?, ?> colTgf;


    @FXML
    private Button deleteEquipment;

    @FXML
    private Button openStartTable;

    @FXML
    private Button refreshTable;

    @FXML
    private TextArea taMessage;

    @FXML
    private TextField tfHeightOfHall;

    @FXML
    private TextField tfLengthOfHall;

    @FXML
    private TextField tfWidthOfHall;

    @FXML
    private TableView<?> tvLighting;

    @FXML
    private TableView<ForInsertInTableViewForChooseLuminaires> tvResponse;

    @FXML
    private TableColumn<ForInsertInTableViewForChooseLuminaires, Integer> colAmountLampsInLuminaire;

    @FXML
    private TableColumn<ForInsertInTableViewForChooseLuminaires, Double> colMin;

    @FXML
    private TableColumn<ForInsertInTableViewForChooseLuminaires, Double> colMax;



    @FXML
    private Button updateEquipment;

    @FXML
    void backToStartPage(ActionEvent event) throws IOException {
        Stage stage = (Stage) tfHeightOfHall.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/start-page.fxml"));
        stage.setTitle("Start Page");
        stage.setScene(new Scene(root));
    }

    public void fullInformation(ActionEvent actionEvent) {
    }

    @FXML
    void handleMouseAction(MouseEvent event) {

    }

    @FXML
    void menuItemFileExitAction(ActionEvent event) {
        Platform.exit();
    }

    public void chooseLuminare(ActionEvent actionEvent) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String value = makeRequestAsString(objectMapper, "create");


        HttpPost request = new HttpPost("http://localhost:9999//lightinformation/create/forchooseluminaries");
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
                taMessage.setText("Information about amount " +
                        "\n and light flux of lamps "+
                        "\n for choose luminaires");
            }
        } catch (HttpHostConnectException e) {
            taMessage.setText("Unable to connect \n" + request.getURI());
        }
    }

    public String makeRequestAsString(ObjectMapper objectMapper, String requestType) {
        try {
            if (requestType.equals("delete")) {
                return tfHeightOfHall.getText();
            } else {
                ForRequestLightingInfo forRequestLightingInfo = new ForRequestLightingInfo();
                forRequestLightingInfo.setHeightProductionHall(Double.valueOf(tfHeightOfHall.getText()));
                forRequestLightingInfo.setLengthProductionHall(Double.valueOf(tfLengthOfHall.getText()));
                forRequestLightingInfo.setWidthProductionHall(Double.valueOf(tfWidthOfHall.getText()));

                return objectMapper.writeValueAsString(forRequestLightingInfo);
            }
        } catch (Exception e) {
            taMessage.setText("Write values in all fields");
            throw new RuntimeException("Write values in all fields");
        }
    }

    public void showInfo(ObjectMapper objectMapper, String responseEntity) throws JsonProcessingException {
        ObservableList<ForInsertInTableViewForChooseLuminaires> list = getFullInformationList(objectMapper, responseEntity);

        colAmountLampsInLuminaire.setCellValueFactory(new PropertyValueFactory<>("amountLamps"));
        colMin.setCellValueFactory(new PropertyValueFactory<>("minLightFluxForChooseLuminaire"));
        colMax.setCellValueFactory(new PropertyValueFactory<>("maxLightFluxForChooseLuminaire"));

        tvResponse.setItems(list);

    }

    public ObservableList<ForInsertInTableViewForChooseLuminaires> getFullInformationList(ObjectMapper objectMapper, String responseEntity) throws JsonProcessingException {

        ObservableList<ForInsertInTableViewForChooseLuminaires> observableList = FXCollections.observableArrayList();

        LightInformationResponse lightInformationResponse = objectMapper.readValue(responseEntity, LightInformationResponse.class);


        for (int i = 1; i <= lightInformationResponse.getLightFluxAtAmountOfLamps().size(); i++) {
            ForInsertInTableViewForChooseLuminaires forInsertInTableViewLightingInfo = new ForInsertInTableViewForChooseLuminaires();
            Set<Double> keys = lightInformationResponse.getLightFluxAtAmountOfLamps().get(i).keySet();
            forInsertInTableViewLightingInfo.setAmountLamps(i);
            double key = keys.iterator().next().doubleValue();
            forInsertInTableViewLightingInfo.setMinLightFluxForChooseLuminaire(key);
            forInsertInTableViewLightingInfo.setMaxLightFluxForChooseLuminaire(lightInformationResponse.getLightFluxAtAmountOfLamps().get(i).get(key));
            observableList.add(forInsertInTableViewLightingInfo);
        }

        return observableList;
    }


}
