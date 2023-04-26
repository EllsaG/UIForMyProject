module com.example.uiformyproject {
    requires javafx.controls;
    requires javafx.fxml;


    requires org.kordamp.bootstrapfx.core;
    requires com.fasterxml.jackson.databind;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;
    requires static lombok;


    exports com.example.startinfo.createstartinformation;
    opens com.example.startinfo.createstartinformation to javafx.fxml;
    exports com.example.startpage;
    opens com.example.startpage to javafx.fxml;
    exports com.example.response;
    opens com.example.response to javafx.fxml;
    exports com.example.startinfo;
    opens com.example.startinfo to javafx.fxml;
    exports com.example.fullinformation;
    opens com.example.fullinformation to javafx.fxml;
    exports com.example.fullinformation.createfullinformation;
    opens com.example.fullinformation.createfullinformation to javafx.fxml;
    exports com.example.ligthinginformation;
    opens com.example.ligthinginformation to javafx.fxml;
    exports com.example.ligthinginformation.createligthinformation;
    opens com.example.ligthinginformation.createligthinformation to javafx.fxml;
    exports com.example.ligthinginformation.chooseluminaire;
    opens com.example.ligthinginformation.chooseluminaire to javafx.fxml;
    exports com.example.ligthinginformation.chooseluminaire.createluminaire;
    opens com.example.ligthinginformation.chooseluminaire.createluminaire to javafx.fxml;
    exports com.example.compensationdevice;
    opens com.example.compensationdevice to javafx.fxml;
    exports com.example.compensationdevice.createcompensationdevice;
    opens com.example.compensationdevice.createcompensationdevice to javafx.fxml;
    exports com.example.powertransformer;
    opens com.example.powertransformer to javafx.fxml;
    exports com.example.powertransformer.createpowertransformer;
    opens com.example.powertransformer.createpowertransformer to javafx.fxml;

    exports com.example.utils;
    opens com.example.utils to javafx.fxml;


}