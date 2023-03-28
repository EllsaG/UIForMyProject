package com.example.addligthinginformation;


import com.example.addligthinginformation.createligthinformation.ForInsertInTableViewForChooseLuminaires;
import com.example.addligthinginformation.createligthinformation.ForInsertInTableViewLightingInfo;
import com.example.addligthinginformation.createligthinformation.ForRequestChooseLuminaire;
import com.example.addligthinginformation.createligthinformation.ForRequestCreateNewLighting;
import com.example.response.ErrorResponseMessage;
import com.example.response.LightChooseLuminaireResponse;
import com.example.response.LightingCreateNewResponse;
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
import java.util.List;
import java.util.Set;

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
    private Button addEquipment;

    @FXML
    private Button deleteEquipment;

    @FXML
    private Button openStartTable;

    @FXML
    private Button refreshTable;
    @FXML
    private Button updateEquipment;

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
    public TableColumn colID;
    @FXML
    public TableColumn colModelOfLuminaire;
    @FXML
    public TableColumn colModelOfLamp;
    @FXML
    public TableColumn colPowerOfOneLamp;
    @FXML
    public TableColumn colLightFluxOfOneLamp;
    @FXML
    public TableColumn colAmountOfLuminairesTotal;
    @FXML
    public TableColumn colAmountLuminairesPerLength;
    @FXML
    public TableColumn colAmountLuminairesPerWidth;
    @FXML
    public TableColumn colDistanceBetweenRowsOfLamps;
    @FXML
    public TableColumn colDistanceBetweenWallAndFirstRowOfLamps;
    @FXML
    public TableColumn colActivePower;
    @FXML
    public TableColumn colReactivePower;
    @FXML
    public TableColumn colFullPower;
    @FXML
    public TableColumn colElectricCurrentFull;
    @FXML
    public TableColumn colElectricCurrentOfOneRow;
    @FXML
    public TableColumn colCosf;
    @FXML
    public TableColumn colTgf;


    @FXML
    void backToStartPage(ActionEvent event) throws IOException {
        Stage stage = (Stage) tfHeightOfHall.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/start-page.fxml"));
        stage.setTitle("Start Page");
        stage.setScene(new Scene(root));
    }


    public void refreshTable(ActionEvent actionEvent) {
    }

    @FXML
    void handleMouseAction(MouseEvent event) {

    }

    public void updateLighting(ActionEvent actionEvent) {
    }


    public void deleteLighting(ActionEvent actionEvent) {
    }

    @FXML
    void menuItemFileExitAction(ActionEvent event) {
        Platform.exit();
    }


    public void createNewLighting(ActionEvent actionEvent) throws IOException {
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
                taMessage.setText("Information about amount " +
                        "\n and light flux of lamps " +
                        "\n for choose luminaires");
            }
        } catch (HttpHostConnectException e) {
            taMessage.setText("Unable to connect \n" + request.getURI());
        }
    }


    public void chooseLuminaire(ActionEvent actionEvent) throws IOException {
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

    public String makeRequestAsString(ObjectMapper objectMapper, String requestType, String reasonForRequest) {
        if (reasonForRequest.equals("chooseLuminaire")) {
            try {
                if (requestType.equals("delete")) {
                    return tfHeightOfHall.getText();
                } else {
                    ForRequestChooseLuminaire forRequestChooseLuminaire = new ForRequestChooseLuminaire();
                    forRequestChooseLuminaire.setHeightProductionHall(Double.valueOf(tfHeightOfHall.getText()));
                    forRequestChooseLuminaire.setLengthProductionHall(Double.valueOf(tfLengthOfHall.getText()));
                    forRequestChooseLuminaire.setWidthProductionHall(Double.valueOf(tfWidthOfHall.getText()));

                    return objectMapper.writeValueAsString(forRequestChooseLuminaire);
                }
            } catch (Exception e) {
                taMessage.setText("Write values in all fields");
                throw new RuntimeException("Write values in all fields");
            }
        } else if (reasonForRequest.equals("createNewLighting")) {
            try {
                if (requestType.equals("delete")) {
                    return tfModelOfLuminaire.getText();
                } else {
                    ForRequestCreateNewLighting forRequestCreateNewLighting = new ForRequestCreateNewLighting();

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
        return "";
    }

    public void showInfo(ObjectMapper objectMapper, String responseEntity, String reasonForRequest) throws JsonProcessingException {
        if (reasonForRequest.equals("chooseLuminaire")) {
            ObservableList<ForInsertInTableViewForChooseLuminaires> list = getChooseLuminaireList(objectMapper, responseEntity);

            colAmountLampsInLuminaire.setCellValueFactory(new PropertyValueFactory<>("amountLamps"));
            colMin.setCellValueFactory(new PropertyValueFactory<>("minLightFluxForChooseLuminaire"));
            colMax.setCellValueFactory(new PropertyValueFactory<>("maxLightFluxForChooseLuminaire"));

            tvResponse.setItems(list);
        } else if (reasonForRequest.equals("createNewLighting")) {
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
            double key = keys.iterator().next().doubleValue();
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

    @Override
    public String toString() {
        return "AddLightingInformationController{" +
                "addEquipment=" + addEquipment +
                ", deleteEquipment=" + deleteEquipment +
                ", openStartTable=" + openStartTable +
                ", refreshTable=" + refreshTable +
                ", updateEquipment=" + updateEquipment +
                ", taMessage=" + taMessage +
                ", tfHeightOfHall=" + tfHeightOfHall +
                ", tfLengthOfHall=" + tfLengthOfHall +
                ", tfWidthOfHall=" + tfWidthOfHall +
                ", tvResponse=" + tvResponse +
                ", colAmountLampsInLuminaire=" + colAmountLampsInLuminaire +
                ", colMin=" + colMin +
                ", colMax=" + colMax +
                ", tvLighting=" + tvLighting +
                ", colID=" + colID +
                ", colModelOfLuminaire=" + colModelOfLuminaire +
                ", colModelOfLamp=" + colModelOfLamp +
                ", colPowerOfOneLamp=" + colPowerOfOneLamp +
                ", colLightFluxOfOneLamp=" + colLightFluxOfOneLamp +
                ", colAmountOfLuminairesTotal=" + colAmountOfLuminairesTotal +
                ", colAmountLuminairesPerLength=" + colAmountLuminairesPerLength +
                ", colAmountLuminairesPerWidth=" + colAmountLuminairesPerWidth +
                ", colDistanceBetweenRowsOfLamps=" + colDistanceBetweenRowsOfLamps +
                ", colDistanceBetweenWallAndFirstRowOfLamps=" + colDistanceBetweenWallAndFirstRowOfLamps +
                ", colActivePower=" + colActivePower +
                ", colReactivePower=" + colReactivePower +
                ", colFullPower=" + colFullPower +
                ", colElectricCurrentFull=" + colElectricCurrentFull +
                ", colElectricCurrentOfOneRow=" + colElectricCurrentOfOneRow +
                ", colCosf=" + colCosf +
                ", colTgf=" + colTgf +
                '}';
    }
}
