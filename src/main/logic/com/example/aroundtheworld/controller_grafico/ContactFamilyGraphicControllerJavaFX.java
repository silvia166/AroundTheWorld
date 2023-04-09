package com.example.aroundtheworld.controller_grafico;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ContactFamilyGraphicControllerJavaFX implements Initializable{

    Stage stage;
    Scene scene;

    @FXML
    private ToggleGroup animals;

    @FXML
    private ToggleGroup food;

    @FXML
    private ToggleGroup house;

    @FXML
    private ToggleGroup siblings;

    @FXML
    ChoiceBox cityBox = new ChoiceBox();

    @FXML
    ObservableList cityList = FXCollections.observableArrayList("London","Rome","Paris","New York","Valencia");

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle){
        cityBox.setItems(cityList);
    }

    public void backButton(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();

    }

    public void toResults(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("familyList.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

    }

}
