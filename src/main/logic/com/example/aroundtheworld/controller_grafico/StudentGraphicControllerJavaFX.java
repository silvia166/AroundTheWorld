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

public class StudentGraphicControllerJavaFX extends LogoutGraphicControllerJavaFX {
    Stage stage;
    Stage stage1;
    Scene scene;

    public void toContactFamily() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("contactFamilyForm.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        stage1 = new Stage();
        stage1.initModality(Modality.APPLICATION_MODAL);
        stage1.initStyle(StageStyle.UNDECORATED);
        stage1.setScene(new Scene(root1,918,560));
        stage1.centerOnScreen();
        stage1.show();
    }

    public void toBookingResidence() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("residenceForm.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        stage1 = new Stage();
        stage1.initModality(Modality.APPLICATION_MODAL);
        stage1.initStyle(StageStyle.UNDECORATED);
        stage1.setScene(new Scene(root1));
        stage1.centerOnScreen();
        stage1.show();
    }

    public void toBookingFamily(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("bookFamily.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toTravelStudent(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("travelStudent.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toProfileStudent(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("profileStudent.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toRequestStudent(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("requestStudent.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toHomepageStudent(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("homepageStudent.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



}
