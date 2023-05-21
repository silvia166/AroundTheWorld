module com.example.aroundtheworld {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    exports com.example.aroundtheworld.graphiccontroller;
    opens com.example.aroundtheworld.graphiccontroller to javafx.fxml;
    exports com.example.aroundtheworld.model;
    opens com.example.aroundtheworld.model to javafx.fxml;
    exports com.example.aroundtheworld.exception;
    opens com.example.aroundtheworld.exception to javafx.fxml;
    exports com.example.aroundtheworld.bean;
    opens com.example.aroundtheworld.bean to javafx.fxml;
    exports com.example.aroundtheworld.engineering;
    opens com.example.aroundtheworld.engineering to javafx.fxml;
    exports com.example.aroundtheworld.appcontroller;
    exports com.example.aroundtheworld.dao;

}