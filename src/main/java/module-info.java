module com.example.uiformyproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.uiformyproject to javafx.fxml;
    exports com.example.uiformyproject;
}