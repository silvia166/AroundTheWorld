package com.example.aroundtheworld.controller_grafico;

import com.example.aroundtheworld.engineering.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LogoutGraphicControllerJavaFX {

    public void backToAccess(ActionEvent event) throws IOException {
        Session.closeSession();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("accessPage.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
