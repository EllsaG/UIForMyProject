package com.example.addligthinginformation.chooseluminaire;

import com.example.addligthinginformation.chooseluminaire.createluminaire.ForInsertInTableViewForChooseLuminaires;
import com.example.addligthinginformation.chooseluminaire.createluminaire.ForRequestChooseLuminaire;
import com.example.response.ErrorResponseMessage;
import com.example.response.LightChooseLuminaireResponse;
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

public class ChooseLuminaireController {
    @FXML
    public TextField tfChooseLuminareId;
    @FXML
    public TextField tfHeightOfHall;
    @FXML
    public TextField tfWidthOfHall;
    @FXML
    public TextField tfLengthOfHall;
    @FXML
    public TableView<LightChooseLuminaireResponse> tvHallResponse;
    @FXML
    public TableColumn<LightChooseLuminaireResponse, Long> colIdForChoose;
    @FXML
    public TableColumn<LightChooseLuminaireResponse, Integer> colAmountLampsInLuminaire;
    @FXML
    public TableColumn<LightChooseLuminaireResponse, Double> colMin;
    @FXML
    public TableColumn<LightChooseLuminaireResponse, Double> colMax;
    @FXML
    public TextArea taMessage;
    @FXML
    public Button createLuminaire;
    @FXML
    public Button updateLuminaire;
    @FXML
    public Button deleteLuminaire;
    @FXML
    public Button refreshTable;


    @FXML
    void backToPreviousPage(ActionEvent event) throws IOException {
        Stage stage = (Stage) tfChooseLuminareId.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/create-lighting-information.fxml"));
        stage.setTitle("Lighting Information");
        stage.setScene(new Scene(root));
    }

    @FXML
    void menuItemFileExitAction(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void handleMouseAction(MouseEvent event) {
        LightChooseLuminaireResponse selectedItem = tvHallResponse.getSelectionModel().getSelectedItem();
        tfChooseLuminareId.setText(String.valueOf(selectedItem.getId()));
    }


    public void chooseLuminare(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == createLuminaire) {
            createLuminaire();
        }else if (actionEvent.getSource() == refreshTable){
            refreshTable ();
        }else if (actionEvent.getSource() == deleteLuminaire){
            deleteLuminaire ();
        }else if (actionEvent.getSource() == updateLuminaire){
            updateLuminaire ();
        }

    }

    public void updateLuminaire() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String value = makeRequestAsString(objectMapper, "create");

        HttpPost post = new HttpPost("http://localhost:9999//lightinformation/update/forchooseluminaries");
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
                taMessage.setText("Information about production hall" +
                        "\n with id № " + tfChooseLuminareId.getText() +
                        "\nhas been updated");
            }
        } catch (HttpHostConnectException e) {
            taMessage.setText("Unable to connect \n" + post.getURI());
        }
    }



    public void deleteLuminaire() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String value = makeRequestAsString(objectMapper, "delete");
        HttpDelete post = new HttpDelete("http://localhost:9999//lightinformatiion/delete/forchooseluminaires/" + value);
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
                taMessage.setText("Information about production hall" +
                        "\n with id № " + tfChooseLuminareId.getText() +
                        "\nhas been deleted");
            }
        } catch (HttpHostConnectException e) {
            taMessage.setText("Unable to connect \n" + post.getURI());
        }
    }





    public void refreshTable() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        HttpGet get = new HttpGet("http://localhost:9999//lightinformation/forchooseluminaires/all");
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
    public void createLuminaire() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String value = makeRequestAsString(objectMapper, "create");


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
                showInfo(objectMapper, responseEntity);
                taMessage.setText("Information about new  " +
                        "\n production hall " +
                        "\nis saved");
            }
        } catch (HttpHostConnectException e) {
            taMessage.setText("Unable to connect \n" + request.getURI());
        }
    }

    public String makeRequestAsString(ObjectMapper objectMapper, String requestType) {

        try {
            if (requestType.equals("delete")) {
                return tfChooseLuminareId.getText();
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


    }

    public void showInfo(ObjectMapper objectMapper, String responseEntity) throws JsonProcessingException {

        ObservableList<LightChooseLuminaireResponse> list = getChooseLuminaireList(objectMapper, responseEntity);

        colIdForChoose.setCellValueFactory(new PropertyValueFactory<>("id"));
        colAmountLampsInLuminaire.setCellValueFactory(new PropertyValueFactory<>("amountOfLampsInOneLuminaire"));
        colMin.setCellValueFactory(new PropertyValueFactory<>("minLightFluxForChooseLuminaire"));
        colMax.setCellValueFactory(new PropertyValueFactory<>("maxLightFluxForChooseLuminaire"));

        tvHallResponse.setItems(list);

    }

    public ObservableList<LightChooseLuminaireResponse> getChooseLuminaireList(ObjectMapper objectMapper, String responseEntity) throws JsonProcessingException {

        ObservableList<LightChooseLuminaireResponse> observableList = FXCollections.observableArrayList();
        ForInsertInTableViewForChooseLuminaires forInsertInTableViewForChooseLuminaires = objectMapper.readValue(responseEntity, ForInsertInTableViewForChooseLuminaires.class);
        List<LightChooseLuminaireResponse> lightFluxAtAmountOfLamps = forInsertInTableViewForChooseLuminaires.getLightFluxAtAmountOfLampsList();
        for (int i = 0; i < lightFluxAtAmountOfLamps.size(); i++) {
            LightChooseLuminaireResponse lightChooseLuminaireResponse = new LightChooseLuminaireResponse();

            lightChooseLuminaireResponse.setId(lightFluxAtAmountOfLamps.get(i).getId());
            lightChooseLuminaireResponse.setAmountOfLampsInOneLuminaire(lightFluxAtAmountOfLamps.get(i).getAmountOfLampsInOneLuminaire());
            lightChooseLuminaireResponse.setMinLightFluxForChooseLuminaire(lightFluxAtAmountOfLamps.get(i).getMinLightFluxForChooseLuminaire());
            lightChooseLuminaireResponse.setMaxLightFluxForChooseLuminaire(lightFluxAtAmountOfLamps.get(i).getMaxLightFluxForChooseLuminaire());

            observableList.add(lightChooseLuminaireResponse);
        }
        return observableList;
    }




}
