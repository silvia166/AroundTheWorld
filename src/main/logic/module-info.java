module com.example.aroundtheworld {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    exports com.example.aroundtheworld.controller_grafico;
    opens com.example.aroundtheworld.controller_grafico to javafx.fxml;
    exports com.example.aroundtheworld.model;
    opens com.example.aroundtheworld.model to javafx.fxml;
}