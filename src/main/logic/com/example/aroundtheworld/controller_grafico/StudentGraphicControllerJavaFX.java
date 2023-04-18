package com.example.aroundtheworld.controller_grafico;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class StudentGraphicControllerJavaFX extends LogoutGraphicControllerJavaFX {
    Stage stage;
    Stage dialog;
    Scene scene;

    public void toContactFamily() throws IOException {

        /*FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("contactFamilyForm.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        stage1 = new Stage();
        stage1.initModality(Modality.APPLICATION_MODAL);
        stage1.initStyle(StageStyle.UNDECORATED);
        stage1.setScene(new Scene(root1,918,560));
        stage1.centerOnScreen();
        stage1.show();*/
        dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UNDECORATED);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("contactFamilyForm.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        dialog.setScene(new Scene(root1,918,560));
        dialog.centerOnScreen();
        dialog.show();
    }

    public void toBookingResidence() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("residenceForm.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.setScene(new Scene(root1));
        dialog.centerOnScreen();
        dialog.show();
    }

    public void toBookingFamily(ActionEvent event) throws IOException {
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("bookFamily.fxml")));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toTravelStudent(ActionEvent event) throws IOException {
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("travelStudent.fxml")));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toProfileStudent(ActionEvent event) throws IOException {
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("profileStudent.fxml")));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toRequestStudent(ActionEvent event) throws IOException {
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("requestStudent.fxml")));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toHomepageStudent(ActionEvent event) throws IOException {
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("homepageStudent.fxml")));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



}
