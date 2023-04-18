module com.example.aroundtheworld {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    exports com.example.aroundtheworld.controller_grafico;
    opens com.example.aroundtheworld.controller_grafico to javafx.fxml;
    exports com.example.aroundtheworld.model;
    opens com.example.aroundtheworld.model to javafx.fxml;
    exports com.example.aroundtheworld.exception;
    opens com.example.aroundtheworld.exception to javafx.fxml;

}