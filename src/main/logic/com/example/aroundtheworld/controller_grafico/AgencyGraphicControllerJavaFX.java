package com.example.aroundtheworld.controller_grafico;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class AgencyGraphicControllerJavaFX extends LogoutGraphicControllerJavaFX {
    Stage stage;
    Stage stage1;
    Scene scene;

    public void toAddFamily() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("newFamilyForm.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        stage1 = new Stage();
        stage1.initModality(Modality.APPLICATION_MODAL);
        stage1.initStyle(StageStyle.UNDECORATED);
        stage1.setScene(new Scene(root1,918,560));
        stage1.centerOnScreen();
        stage1.show();
    }

    public void toRequestAgency(ActionEvent event) throws IOException {
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("requestAgency.fxml")));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toHomepageAgency(ActionEvent event) throws IOException {
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("homepageAgency.fxml")));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
