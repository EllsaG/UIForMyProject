package com.example.addcompensationdevice;


import com.example.addcompensationdevice.createcompensationdevice.ForInsertInTableViewForChooseLuminaires;
import com.example.addcompensationdevice.createcompensationdevice.ForRequestChooseLuminaire;
import com.example.addcompensationdevice.createcompensationdevice.ForRequestCreateNewLighting;
import com.example.addligthinginformation.createligthinformation.ForInsertInTableViewLightingInfo;
import com.example.response.ErrorResponseMessage;
import com.example.response.LightChooseLuminaireResponse;
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
import java.util.Set;

public class AddCompensationDeviceController {

    @FXML
    public TextField tfChooseLuminareId;
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
    private TextArea taMessage;

    @FXML
    private TextField tfHeightOfHall;

    @FXML
    private TextField tfLengthOfHall;

    @FXML
    private TextField tfWidthOfHall;


    @FXML
    private TableView<ForInsertInTableViewForChooseLuminaires> tvResponse;
    @FXML
    private TableColumn<ForInsertInTableViewForChooseLuminaires, Integer> colAmountLampsInLuminaire;
    @FXML
    private TableColumn<ForInsertInTableViewForChooseLuminaires, Double> colMin;
    @FXML
    private TableColumn<ForInsertInTableViewForChooseLuminaires, Double> colMax;
    @FXML
    TableView<LightingCreateNewResponse> tvLighting;
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
        Stage stage = (Stage) tfHeightOfHall.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/start-page.fxml"));
        stage.setTitle("Start Page");
        stage.setScene(new Scene(root));
    }

    @FXML
    void menuItemFileExitAction(ActionEvent event) {
        Platform.exit();
    }

    public void lightingInformation(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == addChooseLuminaire) {
            chooseLuminaire();
        }else if (actionEvent.getSource() == createNewLighting) {
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

    public void chooseLuminaire() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String value = makeRequestAsString(objectMapper, "create", "chooseLuminaire");


        HttpPost request = new HttpPost("http://localhost:9999//lightinformation/create/forchooseluminaires");
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
                showInfo(objectMapper, responseEntity, "chooseLuminaire");
                taMessage.setText("Information about amount " +
                        "\n and light flux of lamps " +
                        "\n for choose luminaires");
            }
        } catch (HttpHostConnectException e) {
            taMessage.setText("Unable to connect \n" + request.getURI());
        }
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
                showInfo(objectMapper, responseEntity, "createNewLighting");
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

        HttpGet get = new HttpGet("http://localhost:9999//lightinformation/all");
        get.addHeader("content-type", "application/json");

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(get)) {
            HttpEntity entity = response.getEntity();
            String responseEntity = EntityUtils.toString(entity);
            showInfo(objectMapper, responseEntity, "refreshTable");

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
                showInfo(objectMapper, responseEntity, "updateLighting");
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
                showInfo(objectMapper, responseEntity, "deleteLighting");
                taMessage.setText("Information about lighting" +
                        "\n with id № " + tfId.getText() +
                        "\nhas been updated");
            }
        } catch (HttpHostConnectException e) {
            taMessage.setText("Unable to connect \n" + post.getURI());
        }
    }


    public String makeRequestAsString(ObjectMapper objectMapper, String requestType, String reasonForRequest) {
        if (reasonForRequest.equals("chooseLuminaire")) {
            try {
                if (requestType.equals("delete")) {
                    return tfHeightOfHall.getText();
                } else {
                    ForRequestChooseLuminaire forRequestChooseLuminaire = new ForRequestChooseLuminaire();
                    forRequestChooseLuminaire.setLightingId(Long.valueOf(tfChooseLuminareId.getText()));
                    forRequestChooseLuminaire.setHeightProductionHall(Double.valueOf(tfHeightOfHall.getText()));
                    forRequestChooseLuminaire.setLengthProductionHall(Double.valueOf(tfLengthOfHall.getText()));
                    forRequestChooseLuminaire.setWidthProductionHall(Double.valueOf(tfWidthOfHall.getText()));

                    return objectMapper.writeValueAsString(forRequestChooseLuminaire);
                }
            } catch (Exception e) {
                taMessage.setText("Write values in all fields");
                throw new RuntimeException("Write values in all fields");
            }
        } else {
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
    }

    public void showInfo(ObjectMapper objectMapper, String responseEntity, String reasonForRequest) throws JsonProcessingException {
        if (reasonForRequest.equals("chooseLuminaire")) {
            ObservableList<ForInsertInTableViewForChooseLuminaires> list = getChooseLuminaireList(objectMapper, responseEntity);

            colAmountLampsInLuminaire.setCellValueFactory(new PropertyValueFactory<>("amountLamps"));
            colMin.setCellValueFactory(new PropertyValueFactory<>("minLightFluxForChooseLuminaire"));
            colMax.setCellValueFactory(new PropertyValueFactory<>("maxLightFluxForChooseLuminaire"));

            tvResponse.setItems(list);
        } else {
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
    }

    public ObservableList<ForInsertInTableViewForChooseLuminaires> getChooseLuminaireList(ObjectMapper objectMapper, String responseEntity) throws JsonProcessingException {

        ObservableList<ForInsertInTableViewForChooseLuminaires> observableList = FXCollections.observableArrayList();
        LightChooseLuminaireResponse lightChooseLuminaireResponse = objectMapper.readValue(responseEntity, LightChooseLuminaireResponse.class);

        for (int i = 1; i <= lightChooseLuminaireResponse.getLightFluxAtAmountOfLamps().size(); i++) {
            ForInsertInTableViewForChooseLuminaires forInsertInTableViewLightingInfo = new ForInsertInTableViewForChooseLuminaires();
            Set<Double> keys = lightChooseLuminaireResponse.getLightFluxAtAmountOfLamps().get(i).keySet();
            forInsertInTableViewLightingInfo.setAmountLamps(i);
            double key = keys.iterator().next();
            forInsertInTableViewLightingInfo.setMinLightFluxForChooseLuminaire(key);
            forInsertInTableViewLightingInfo.setMaxLightFluxForChooseLuminaire(lightChooseLuminaireResponse.getLightFluxAtAmountOfLamps().get(i).get(key));
            observableList.add(forInsertInTableViewLightingInfo);
        }
        return observableList;
    }

    public ObservableList<LightingCreateNewResponse> getCreateNewLightingInfo(ObjectMapper objectMapper, String responseEntity) throws JsonProcessingException {
        ObservableList<LightingCreateNewResponse> observableList = FXCollections.observableArrayList();
        ForInsertInTableViewLightingInfo forInsertInTableViewLightingInfo = objectMapper.readValue(responseEntity, ForInsertInTableViewLightingInfo.class);

        for (int i = 0; i < forInsertInTableViewLightingInfo.getList().size(); i++) {
            List<LightingCreateNewResponse> list = forInsertInTableViewLightingInfo.getList();
            observableList.add(list.get(i));
        }
        return observableList;
    }

}


