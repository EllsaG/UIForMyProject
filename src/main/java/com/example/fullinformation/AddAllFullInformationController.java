package com.example.fullinformation;

import com.example.fullinformation.createfullinformation.ForInsertInTableViewFullInfo;
import com.example.fullinformation.createfullinformation.ForRequestFullInformation;
import com.example.fullinformation.createfullinformation.ForRequestFullInformationMainBusbar;
import com.example.fullinformation.createfullinformation.ListInputEquipment;
import com.example.startinfo.AddAllStartInformationApplication;
import com.example.response.ErrorResponseMessage;
import com.example.response.FullInformationResponse;
import com.example.response.ListInputEquipmentResponse;
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
import java.util.ArrayList;
import java.util.List;

public class AddAllFullInformationController {


    @FXML
    private Button addEquipment;
    @FXML
    private Button deleteEquipment;
    @FXML
    private Button refreshTable;
    @FXML
    private TextArea taMessage;
    @FXML
    private TextField tfAmount1;
    @FXML
    private TextField tfAmount2;
    @FXML
    private TextField tfAmount3;
    @FXML
    private TextField tfAmount4;
    @FXML
    private TextField tfAmount5;
    @FXML
    private TextField tfAmount6;
    @FXML
    private TextField tfAmount7;
    @FXML
    private TextField tfAmount8;
    @FXML
    private TextField tfId;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfNumber1;
    @FXML
    private TextField tfNumber2;
    @FXML
    private TextField tfNumber3;
    @FXML
    private TextField tfNumber4;
    @FXML
    private TextField tfNumber5;
    @FXML
    private TextField tfNumber6;
    @FXML
    private TextField tfNumber7;
    @FXML
    private TextField tfNumber8;
    @FXML
    private Button updateEquipment;

    @FXML
    TableView<FullInformationResponse> tvEquipments;
    @FXML
    TableColumn<ForInsertInTableViewFullInfo, Long> colID;
    @FXML
    TableColumn<ForInsertInTableViewFullInfo, String> colName;
    @FXML
    TableColumn<ForInsertInTableViewFullInfo, Integer> colAmount;
    @FXML
    TableColumn<ForInsertInTableViewFullInfo, Double> colPowerOfOne;
    @FXML
    TableColumn<ForInsertInTableViewFullInfo, Double> colPowerOfGroup;
    @FXML
    TableColumn<ForInsertInTableViewFullInfo, Double> colModule;
    @FXML
    TableColumn<ForInsertInTableViewFullInfo, Double> colKi;
    @FXML
    TableColumn<ForInsertInTableViewFullInfo, Double> colCosf;
    @FXML
    TableColumn<ForInsertInTableViewFullInfo, Double> colTgf;
    @FXML
    TableColumn<ForInsertInTableViewFullInfo, Double> colAvgDailyActivePower;
    @FXML
    TableColumn<ForInsertInTableViewFullInfo, Double> colAvgDailyReactivePower;
    @FXML
    TableColumn<ForInsertInTableViewFullInfo, Double> colEffectiveAmountOfEquipment;
    @FXML
    TableColumn<ForInsertInTableViewFullInfo, Double> colKmax;
    @FXML
    TableColumn<ForInsertInTableViewFullInfo, Double> colActivePower;
    @FXML
    TableColumn<ForInsertInTableViewFullInfo, Double> colReactivePower;
    @FXML
    TableColumn<ForInsertInTableViewFullInfo, Double> colFullPower;
    @FXML
    TableColumn<ForInsertInTableViewFullInfo, Double> colMaxCurrent;
    @FXML
    public CheckBox cbForChoosingInMainBusbar;


    public void menuItemFileExitAction(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void backToStartPage(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) tfId.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/start-page.fxml"));
        stage.setTitle("Start Page");
        stage.setScene(new Scene(root));
    }


    public void openStartTable(ActionEvent actionEvent) throws IOException {
        AddAllStartInformationApplication addAllStartInformationApplication = new AddAllStartInformationApplication();
        addAllStartInformationApplication.start(new Stage());
    }

    public void fullInformation(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == addEquipment) {
            createEquipment();
        } else if (actionEvent.getSource() == updateEquipment) {
            updateEquipment();
        } else if (actionEvent.getSource() == refreshTable) {
            refreshTable();
        } else if (actionEvent.getSource() == deleteEquipment) {
            deleteEquipment();
        } else if (actionEvent.getSource() == cbForChoosingInMainBusbar) {
            forChoosingInMainBusbar();
        }
    }


    public void forChoosingInMainBusbar() {
        if (cbForChoosingInMainBusbar.isSelected()) {
            tfAmount1.setVisible(false);
            tfAmount2.setVisible(false);
            tfAmount3.setVisible(false);
            tfAmount4.setVisible(false);
            tfAmount5.setVisible(false);
            tfAmount6.setVisible(false);
            tfAmount7.setVisible(false);
            tfAmount8.setVisible(false);
        } else {
            tfAmount1.setVisible(true);
            tfAmount2.setVisible(true);
            tfAmount3.setVisible(true);
            tfAmount4.setVisible(true);
            tfAmount5.setVisible(true);
            tfAmount6.setVisible(true);
            tfAmount7.setVisible(true);
            tfAmount8.setVisible(true);
        }
    }

    public void createEquipment() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestURI = "";
        boolean selected = cbForChoosingInMainBusbar.isSelected();
        if (selected){
            requestURI = "create/main" ;
        }else {
            requestURI = "create" ;
        }
        String value = makeRequestAsString(objectMapper, requestURI);

        HttpPost request = new HttpPost("http://localhost:9999//fullinformation/"+requestURI);
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

        HttpGet get = new HttpGet("http://localhost:9999//fullinformation/all");
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
        String requestURI = "";
        boolean selected = cbForChoosingInMainBusbar.isSelected();
        if (selected){
            requestURI = "update/main" ;
        }else {
            requestURI = "update" ;
        }
        String value = makeRequestAsString(objectMapper, requestURI);

        HttpPost post = new HttpPost("http://localhost:9999//fullinformation/"+requestURI);
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

        HttpDelete request = new HttpDelete("http://localhost:9999//fullinformation/delete/" + value);
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
        FullInformationResponse selectedItem = tvEquipments.getSelectionModel().getSelectedItem();
        List<ListInputEquipmentResponse> fullStartInformId = selectedItem.getFullStartInformId();
        tfId.setText(String.valueOf(selectedItem.getId()));
        tfName.setText(String.valueOf(selectedItem.getNameOfBusbar()));
        try {
            tfNumber1.setText(String.valueOf(fullStartInformId.get(0).getStartInformId()));
            tfNumber2.setText(String.valueOf(fullStartInformId.get(1).getStartInformId()));
            tfNumber3.setText(String.valueOf(fullStartInformId.get(2).getStartInformId()));
            tfNumber4.setText(String.valueOf(fullStartInformId.get(3).getStartInformId()));
            tfNumber5.setText(String.valueOf(fullStartInformId.get(4).getStartInformId()));
            tfNumber6.setText(String.valueOf(fullStartInformId.get(5).getStartInformId()));
            tfNumber7.setText(String.valueOf(fullStartInformId.get(6).getStartInformId()));
            tfNumber8.setText(String.valueOf(fullStartInformId.get(7).getStartInformId()));
        } catch (Exception e) {
        }

        try {
            tfAmount1.setText(String.valueOf(fullStartInformId.get(0).getAmount()));
            tfAmount2.setText(String.valueOf(fullStartInformId.get(1).getAmount()));
            tfAmount3.setText(String.valueOf(fullStartInformId.get(2).getAmount()));
            tfAmount4.setText(String.valueOf(fullStartInformId.get(3).getAmount()));
            tfAmount5.setText(String.valueOf(fullStartInformId.get(4).getAmount()));
            tfAmount6.setText(String.valueOf(fullStartInformId.get(5).getAmount()));
            tfAmount7.setText(String.valueOf(fullStartInformId.get(6).getAmount()));
            tfAmount8.setText(String.valueOf(fullStartInformId.get(7).getAmount()));
        } catch (Exception ignored) {
        }
    }

    public void showInfo(ObjectMapper objectMapper, String responseEntity) throws JsonProcessingException {
        ObservableList<FullInformationResponse> list = getFullInformationList(objectMapper, responseEntity);

        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("nameOfBusbar"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colPowerOfOne.setCellValueFactory(new PropertyValueFactory<>("power"));
        colPowerOfGroup.setCellValueFactory(new PropertyValueFactory<>("powerOfGroup"));
        colModule.setCellValueFactory(new PropertyValueFactory<>("module"));
        colKi.setCellValueFactory(new PropertyValueFactory<>("ki"));
        colCosf.setCellValueFactory(new PropertyValueFactory<>("cosF"));
        colTgf.setCellValueFactory(new PropertyValueFactory<>("tgF"));
        colAvgDailyActivePower.setCellValueFactory(new PropertyValueFactory<>("avgDailyActivePower"));
        colAvgDailyReactivePower.setCellValueFactory(new PropertyValueFactory<>("avgDailyReactivePower"));
        colEffectiveAmountOfEquipment.setCellValueFactory(new PropertyValueFactory<>("effectiveAmountOfEquipment"));
        colKmax.setCellValueFactory(new PropertyValueFactory<>("coefficientMax"));
        colActivePower.setCellValueFactory(new PropertyValueFactory<>("maxActivePower"));
        colReactivePower.setCellValueFactory(new PropertyValueFactory<>("maxReactivePower"));
        colFullPower.setCellValueFactory(new PropertyValueFactory<>("maxFullPower"));
        colMaxCurrent.setCellValueFactory(new PropertyValueFactory<>("maxElectricCurrent"));

        colName.setCellFactory((param) -> new WrapTextTableCell());


        tvEquipments.setItems(list);

    }

    public String makeRequestAsString(ObjectMapper objectMapper, String requestType) {
        try {
            if (requestType.equals("delete")) {
                return tfId.getText();
            } else if (requestType.equals("create/main") || requestType.equals("update/main")) {
                ForRequestFullInformationMainBusbar forRequestFullInformationMainBusbar = new ForRequestFullInformationMainBusbar();

                List<Long> list = new ArrayList<>();
                forRequestFullInformationMainBusbar.setId(Long.parseLong(tfId.getText()));
                forRequestFullInformationMainBusbar.setNameOfBusbar(tfName.getText().trim());

                try {
                    list.add(Long.valueOf(tfNumber1.getText()));
                    list.add(Long.valueOf(tfNumber2.getText()));
                    list.add(Long.valueOf(tfNumber3.getText()));
                    list.add(Long.valueOf(tfNumber4.getText()));
                    list.add(Long.valueOf(tfNumber5.getText()));
                    list.add(Long.valueOf(tfNumber6.getText()));
                    list.add(Long.valueOf(tfNumber7.getText()));
                    list.add(Long.valueOf(tfNumber8.getText()));
                } catch (Exception ignored) {
                }
                forRequestFullInformationMainBusbar.setNumbersBusbarsIncludedInMain(list);
                return objectMapper.writeValueAsString(forRequestFullInformationMainBusbar);
            } else {
                ForRequestFullInformation forRequestFullInformation = new ForRequestFullInformation();

                List<ListInputEquipment> list = new ArrayList<>();
                forRequestFullInformation.setId(Long.parseLong(tfId.getText()));
                forRequestFullInformation.setNameOfBusbar(tfName.getText().trim());
                Long id = Long.valueOf(tfId.getText());
                try {
                    list.add(new ListInputEquipment(id, Long.parseLong(tfNumber1.getText()), Integer.parseInt(tfAmount1.getText())));
                    list.add(new ListInputEquipment(id, Long.parseLong(tfNumber2.getText()), Integer.parseInt(tfAmount2.getText())));
                    list.add(new ListInputEquipment(id, Long.parseLong(tfNumber3.getText()), Integer.parseInt(tfAmount3.getText())));
                    list.add(new ListInputEquipment(id, Long.parseLong(tfNumber4.getText()), Integer.parseInt(tfAmount4.getText())));
                    list.add(new ListInputEquipment(id, Long.parseLong(tfNumber5.getText()), Integer.parseInt(tfAmount5.getText())));
                    list.add(new ListInputEquipment(id, Long.parseLong(tfNumber6.getText()), Integer.parseInt(tfAmount6.getText())));
                    list.add(new ListInputEquipment(id, Long.parseLong(tfNumber7.getText()), Integer.parseInt(tfAmount7.getText())));
                    list.add(new ListInputEquipment(id, Long.parseLong(tfNumber8.getText()), Integer.parseInt(tfAmount8.getText())));
                } catch (Exception ignored) {
                }
                forRequestFullInformation.setNumbersAndAmountOfEquipments(list);
                return objectMapper.writeValueAsString(forRequestFullInformation);
            }

        } catch (Exception e) {
            taMessage.setText("Write values in all fields");
            throw new RuntimeException("Write values in all fields");
        }
    }

    public ObservableList<FullInformationResponse> getFullInformationList(ObjectMapper objectMapper, String responseEntity) throws JsonProcessingException {

        ObservableList<FullInformationResponse> observableList = FXCollections.observableArrayList();

        ForInsertInTableViewFullInfo forInsertInTableViewFullInfo = objectMapper.readValue(responseEntity, ForInsertInTableViewFullInfo.class);

        for (int i = 0; i < forInsertInTableViewFullInfo.getList().size(); i++) {
            for (int j = 0; j < forInsertInTableViewFullInfo.getList().get(i).getFullStartInformId().size(); j++) {
                ListInputEquipmentResponse listInput = forInsertInTableViewFullInfo.getList().get(i).getFullStartInformId().get(j);
                FullInformationResponse fullInformationResponse = new FullInformationResponse();
                fullInformationResponse.setId(listInput.getStartInformId());
                fullInformationResponse.setNameOfBusbar(listInput.getName());
                fullInformationResponse.setAmount(listInput.getAmount());
                fullInformationResponse.setAvgDailyActivePower(listInput.getAvgDailyActivePower());
                fullInformationResponse.setAvgDailyReactivePower(listInput.getAvgDailyReactivePower());
                fullInformationResponse.setPowerOfGroup(listInput.getPowerOfGroup());
                fullInformationResponse.setPower(listInput.getPower());
                fullInformationResponse.setCosF(listInput.getCosf());
                fullInformationResponse.setTgF(listInput.getTgf());
                fullInformationResponse.setKi(listInput.getKi());

                observableList.add(fullInformationResponse);
            }
            observableList.add(forInsertInTableViewFullInfo.getList().get(i));
        }
        return observableList;
    }


}





