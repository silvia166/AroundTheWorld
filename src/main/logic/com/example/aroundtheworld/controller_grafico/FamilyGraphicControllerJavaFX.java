package com.example.aroundtheworld.controller_grafico;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class FamilyGraphicControllerJavaFX extends LogoutGraphicControllerJavaFX {
    Scene scene;

    public void toFamilyProfile() throws IOException {
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("profileFamily.fxml")));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toRequestFamily() throws IOException {
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("requestFamily.fxml")));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toHomepageFamily() throws IOException {
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("homepageFamily.fxml")));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
