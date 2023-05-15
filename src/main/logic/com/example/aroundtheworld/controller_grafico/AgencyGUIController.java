package com.example.aroundtheworld.controller_grafico;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class AgencyGUIController {
    Scene scene;
    public void toAddFamily() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("newFamilyForm.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root1,918,560));
        stage.centerOnScreen();
        stage.show();
    }

    public void toRequestAgency() throws IOException {
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("requestAgency.fxml")));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toHomepageAgency() throws IOException {
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("homepageAgency.fxml")));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void logout() throws IOException {
        LogoutGUIController logoutGUIController = new LogoutGUIController();
        logoutGUIController.logout();
    }

}
