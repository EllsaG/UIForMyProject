module com.example.uiformyproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires com.fasterxml.jackson.databind;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;


    exports com.example.addallstartinfo.createstartinformation;
    opens com.example.addallstartinfo.createstartinformation to javafx.fxml;
    exports com.example.startpage;
    opens com.example.startpage to javafx.fxml;
    exports com.example.response;
    opens com.example.response to javafx.fxml;
    exports com.example.addallstartinfo;
    opens com.example.addallstartinfo to javafx.fxml;
    exports com.example.addallfullinformation;
    opens com.example.addallfullinformation to javafx.fxml;

}