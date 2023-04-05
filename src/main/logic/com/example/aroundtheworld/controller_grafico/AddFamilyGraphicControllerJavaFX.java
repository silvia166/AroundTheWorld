package com.example.aroundtheworld.controller_grafico;

import com.example.aroundtheworld.model.FamilyMember;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddFamilyGraphicControllerJavaFX implements Initializable{

    @FXML
    private TableView<FamilyMember> tableViewMembers;

    @FXML
    private TableColumn<FamilyMember, String> nameColumn;

    @FXML
    private TableColumn<FamilyMember, Integer> ageColumn;

    @FXML
    private TableColumn<FamilyMember, String> parenthoodColumn;

    @FXML
    private TextField nameInput;

    @FXML
    private TextField ageInput;

    @FXML
    private TextField parenthoodInput;

    @FXML
    ChoiceBox dogBox = new ChoiceBox();

    @FXML
    ChoiceBox catBox = new ChoiceBox();

    @FXML
    ChoiceBox otherBox = new ChoiceBox();

    @FXML
    ObservableList animalList = FXCollections.observableArrayList("0","1","2","3","4+");

    public void initialize(URL url, ResourceBundle resourceBundle) {

        dogBox.setItems(animalList);
        catBox.setItems(animalList);
        otherBox.setItems(animalList);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("Age"));
        parenthoodColumn.setCellValueFactory(new PropertyValueFactory<>("Parenthood"));
    }

    @FXML
    public void addMember(ActionEvent event) {
        FamilyMember familyMember = new FamilyMember(nameInput.getText(),Integer.parseInt(ageInput.getText()),parenthoodInput.getText());
        ObservableList<FamilyMember> familyMembers = tableViewMembers.getItems();
        familyMembers.add(familyMember);
        tableViewMembers.setItems(familyMembers);
        nameInput.setText("");
        ageInput.setText("");
        parenthoodInput.setText("");
    }

    @FXML
    public void removeMember(ActionEvent event){
        int selectedID = tableViewMembers.getSelectionModel().getSelectedIndex();
        tableViewMembers.getItems().remove(selectedID);
    }

    public void backButton(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

}
