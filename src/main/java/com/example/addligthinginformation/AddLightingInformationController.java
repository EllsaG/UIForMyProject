package com.example.addligthinginformation;


import com.example.addligthinginformation.chooseluminaire.ChooseLuminaireApplication;
import com.example.addligthinginformation.createligthinformation.ForInsertInTableViewLightingInfo;
import com.example.addligthinginformation.createligthinformation.ForRequestCreateNewLighting;
import com.example.response.ErrorResponseMessage;
import com.example.response.LightingCreateNewResponse;
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

public class AddLightingInformationController {

    @FXML
    public TextField tfModelOfLuminaire;
    @FXML
    public TextField tfModelOfLamp;
    @FXML
    public TextField tfAmountOfLampsInOneLuminaire;
    @FXML
    public TextField tfLightFluxOneLamp;
    @FXML
    public TextField tfActivePowerOneLamp;
    @FXML
    public TextField tfId;
    @FXML
    public Button addChooseLuminaire;
    @FXML
    public Button deleteLighting;
    @FXML
    public Button createNewLighting;
    @FXML
    public Button updateLighting;
    public Button refreshTable;
    @FXML
    public Button openLuminaireTable;
    @FXML
    private TextArea taMessage;
    @FXML
    private TableView<LightingCreateNewResponse> tvLighting;
    @FXML
    public TableColumn<LightingCreateNewResponse, Long> colID;
    @FXML
    public TableColumn<LightingCreateNewResponse, String> colModelOfLuminaire;
    @FXML
    public TableColumn<LightingCreateNewResponse, String> colModelOfLamp;
    @FXML
    public TableColumn<LightingCreateNewResponse, Double> colPowerOfOneLamp;
    @FXML
    public TableColumn<LightingCreateNewResponse, Double> colLightFluxOfOneLamp;
    @FXML
    public TableColumn<LightingCreateNewResponse, Integer> colAmountOfLuminairesTotal;
    @FXML
    public TableColumn<LightingCreateNewResponse, Integer> colAmountLuminairesPerLength;
    @FXML
    public TableColumn<LightingCreateNewResponse, Integer> colAmountLuminairesPerWidth;
    @FXML
    public TableColumn<LightingCreateNewResponse, Double> colDistanceBetweenRowsOfLamps;
    @FXML
    public TableColumn<LightingCreateNewResponse, Double> colDistanceBetweenWallAndFirstRowOfLamps;
    @FXML
    public TableColumn<LightingCreateNewResponse, Double> colActivePower;
    @FXML
    public TableColumn<LightingCreateNewResponse, Double> colReactivePower;
    @FXML
    public TableColumn<LightingCreateNewResponse, Double> colFullPower;
    @FXML
    public TableColumn<LightingCreateNewResponse, Double> colElectricCurrentFull;
    @FXML
    public TableColumn<LightingCreateNewResponse, Double> colElectricCurrentOfOneRow;
    @FXML
    public TableColumn<LightingCreateNewResponse, Double> colCosf;
    @FXML
    public TableColumn<LightingCreateNewResponse, Double> colTgf;


    @FXML
    void backToStartPage(ActionEvent event) throws IOException {
        Stage stage = (Stage) taMessage.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/start-page.fxml"));
        stage.setTitle("Start Page");
        stage.setScene(new Scene(root));
    }

    @FXML
    void menuItemFileExitAction(ActionEvent event) {
        Platform.exit();
    }

    public void lightingInformation(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == createNewLighting) {
            createNewLighting();
        } else if (actionEvent.getSource() == updateLighting) {
            updateLighting();
        } else if (actionEvent.getSource() == refreshTable) {
            refreshTable();
        } else if (actionEvent.getSource() == deleteLighting) {
            deleteLighting();
        }
    }

    @FXML
    void handleMouseAction(MouseEvent event) {
        LightingCreateNewResponse selectedItem = tvLighting.getSelectionModel().getSelectedItem();
        tfId.setText(String.valueOf(selectedItem.getId()));
        tfModelOfLuminaire.setText(selectedItem.getModelOfLuminaire());
        tfModelOfLamp.setText(selectedItem.getModelOfLamp());
        tfAmountOfLampsInOneLuminaire.setText(String.valueOf(selectedItem.getAmountOfLampsInOneLuminaire()));
        tfLightFluxOneLamp.setText(String.valueOf(selectedItem.getLightFluxOfOneLamp()));
        tfActivePowerOneLamp.setText(String.valueOf(selectedItem.getPowerOfOneLamp()));
    }


    public void createNewLighting() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String value = makeRequestAsString(objectMapper, "create", "createNewLighting");


        HttpPost request = new HttpPost("http://localhost:9999//lightinformation/create/createnewlighting");
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
                taMessage.setText("Information about lighting " +
                        "\n with id № " + tfId.getText() +
                        "\nis saved");
            }
        } catch (HttpHostConnectException e) {
            taMessage.setText("Unable to connect \n" + request.getURI());
        }
    }

    public void refreshTable() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        HttpGet get = new HttpGet("http://localhost:9999//lightinformation/lightinformation/all");
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

    public void updateLighting() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String value = makeRequestAsString(objectMapper, "create", "updateLighting");

        HttpPost post = new HttpPost("http://localhost:9999//lightinformation/update/insertnewluminaries");
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
                taMessage.setText("Information about lighting" +
                        "\n with id № " + tfId.getText() +
                        "\nhas been updated");
            }
        } catch (HttpHostConnectException e) {
            taMessage.setText("Unable to connect \n" + post.getURI());
        }
    }

    public void deleteLighting() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String value = makeRequestAsString(objectMapper, "delete", "deleteLighting");

        HttpDelete post = new HttpDelete("http://localhost:9999//lightinformatiion/delete/" + value);
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
                taMessage.setText("Information about lighting" +
                        "\n with id № " + tfId.getText() +
                        "\nhas been updated");
            }
        } catch (HttpHostConnectException e) {
            taMessage.setText("Unable to connect \n" + post.getURI());
        }
    }


    public String makeRequestAsString(ObjectMapper objectMapper, String requestType, String reasonForRequest) {

        try {
            if (requestType.equals("delete")) {
                return tfId.getText();
            } else {
                ForRequestCreateNewLighting forRequestCreateNewLighting = new ForRequestCreateNewLighting();

                forRequestCreateNewLighting.setLightingId(Long.valueOf(tfId.getText()));
                forRequestCreateNewLighting.setModelOfLuminaire(tfModelOfLuminaire.getText());
                forRequestCreateNewLighting.setModelOfLamp(tfModelOfLamp.getText());
                forRequestCreateNewLighting.setAmountOfLampsInOneLuminaire(Integer.valueOf(tfAmountOfLampsInOneLuminaire.getText()));
                forRequestCreateNewLighting.setLightFluxOneLamp(Double.valueOf(tfLightFluxOneLamp.getText()));
                forRequestCreateNewLighting.setActivePowerOneLamp(Double.valueOf(tfActivePowerOneLamp.getText()));

                return objectMapper.writeValueAsString(forRequestCreateNewLighting);
            }
        } catch (Exception e) {
            taMessage.setText("Write values in all fields");
            throw new RuntimeException("Write values in all fields");
        }

    }

    public void showInfo(ObjectMapper objectMapper, String responseEntity) throws JsonProcessingException {

        ObservableList<LightingCreateNewResponse> list = getCreateNewLightingInfo(objectMapper, responseEntity);
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colModelOfLuminaire.setCellValueFactory(new PropertyValueFactory<>("modelOfLuminaire"));
        colModelOfLamp.setCellValueFactory(new PropertyValueFactory<>("modelOfLamp"));
        colPowerOfOneLamp.setCellValueFactory(new PropertyValueFactory<>("powerOfOneLamp"));
        colLightFluxOfOneLamp.setCellValueFactory(new PropertyValueFactory<>("lightFluxOfOneLamp"));
        colAmountOfLuminairesTotal.setCellValueFactory(new PropertyValueFactory<>("amountOfLuminaires"));
        colAmountLuminairesPerLength.setCellValueFactory(new PropertyValueFactory<>("amountLuminairesPerLength"));
        colAmountLuminairesPerWidth.setCellValueFactory(new PropertyValueFactory<>("amountLuminairesPerWidth"));
        colDistanceBetweenRowsOfLamps.setCellValueFactory(new PropertyValueFactory<>("distanceBetweenRowsOfLamps"));
        colDistanceBetweenWallAndFirstRowOfLamps.setCellValueFactory(new PropertyValueFactory<>("distanceBetweenWallAndFirstRowOfLamps"));
        colActivePower.setCellValueFactory(new PropertyValueFactory<>("activePower"));
        colReactivePower.setCellValueFactory(new PropertyValueFactory<>("reactivePower"));
        colFullPower.setCellValueFactory(new PropertyValueFactory<>("fullPower"));
        colElectricCurrentFull.setCellValueFactory(new PropertyValueFactory<>("electricCurrent"));
        colElectricCurrentOfOneRow.setCellValueFactory(new PropertyValueFactory<>("electricCurrentOfOneRowOfLuminaire"));
        colCosf.setCellValueFactory(new PropertyValueFactory<>("cosF"));
        colTgf.setCellValueFactory(new PropertyValueFactory<>("tgF"));

        colModelOfLamp.setCellFactory((param) -> new WrapTextTableCell());
        colModelOfLuminaire.setCellFactory((param) -> new WrapTextTableCell());


        tvLighting.setItems(list);

    }


    public ObservableList<LightingCreateNewResponse> getCreateNewLightingInfo(ObjectMapper objectMapper, String responseEntity) throws JsonProcessingException {
        ObservableList<LightingCreateNewResponse> observableList = FXCollections.observableArrayList();
        ForInsertInTableViewLightingInfo forInsertInTableViewLightingInfo = objectMapper.readValue(responseEntity, ForInsertInTableViewLightingInfo.class);

        for (int i = 0; i < forInsertInTableViewLightingInfo.getLightInformationList().size(); i++) {
            List<LightingCreateNewResponse> list = forInsertInTableViewLightingInfo.getLightInformationList();
            observableList.add(list.get(i));
        }
        return observableList;
    }

    public void openLuminaireTable(ActionEvent actionEvent) throws IOException {
        ChooseLuminaireApplication chooseLuminaireApplication = new ChooseLuminaireApplication();
        chooseLuminaireApplication.start(new Stage());
    }

}


