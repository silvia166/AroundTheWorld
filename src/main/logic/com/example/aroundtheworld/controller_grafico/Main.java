package com.example.aroundtheworld.controller_grafico;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Stage stage;
    private static Stage secondaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("accessPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        setStage(stage);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Main.stage = stage;
    }

    public static Stage getSecondaryStage() {
        return secondaryStage;
    }

    public static void setSecondaryStage(Stage secondaryStage) {
        Main.secondaryStage = secondaryStage;
    }
}