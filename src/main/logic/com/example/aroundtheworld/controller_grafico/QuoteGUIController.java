package com.example.aroundtheworld.controller_grafico;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;

public class QuoteGUIController {
    @FXML
    ChoiceBox cityBox = new ChoiceBox();

    @FXML
    ObservableList cityList = FXCollections.observableArrayList("London","Rome","Paris","New York","Valencia");

    @FXML
    ChoiceBox accomodationBox = new ChoiceBox();

    @FXML
    ObservableList accomodationList = FXCollections.observableArrayList("Host Family","Residence");

    @FXML
    public void initialize(){
        accomodationBox.setItems(accomodationList);
        cityBox.setItems(cityList);
    }

    public void backButton(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
}
