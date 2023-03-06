module com.example.uiformyproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires com.fasterxml.jackson.databind;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;

    opens com.example.uiformyproject to javafx.fxml;
    exports com.example.uiformyproject;
    exports com.example.uiformyproject.createstartinformation;
    opens com.example.uiformyproject.createstartinformation to javafx.fxml;
    exports com.example.startpage;
    opens com.example.startpage to javafx.fxml;
    exports com.example.getpage;
    opens com.example.getpage to javafx.fxml;
    exports com.example.response;
    opens com.example.response to javafx.fxml;

}