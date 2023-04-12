package com.example.aroundtheworld.controller_grafico;

import com.example.aroundtheworld.bean.FamilyBean;
import com.example.aroundtheworld.controller_applicativo.AddFamilyController;
import com.example.aroundtheworld.engineering.ImageConverterSupport;
import com.example.aroundtheworld.exception.FormEmptyException;
import com.example.aroundtheworld.exception.PhoneFormatException;
import com.example.aroundtheworld.model.Animal;
import com.example.aroundtheworld.model.FamilyMember;
import com.example.aroundtheworld.model.FamilyPreferences;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import java.util.*;

import static java.lang.Integer.parseInt;

public class AddFamilyGraphicControllerJavaFX {

    @FXML
    private TextField addressField;

    @FXML
    private ChoiceBox<?> birdsBox;

    @FXML
    private CheckBox booksBox;

    @FXML
    private ChoiceBox<?> catBox;

    @FXML
    private ChoiceBox<?> cityBox;

    @FXML
    private CheckBox cookingBox;

    @FXML
    private ChoiceBox<?> dogBox;

    @FXML
    private ImageView familyImg;

    @FXML
    private CheckBox filmBox;

    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneField;

    @FXML
    private CheckBox natureBox;

    @FXML
    private RadioButton noPrefRB;

    @FXML
    private ChoiceBox<?> otherBox;

    @FXML
    private ChoiceBox<?> reptilesBox;

    @FXML
    private ChoiceBox<?> rodensBox;

    @FXML
    private RadioButton sharedRB;

    @FXML
    private RadioButton singleRB;

    @FXML
    private CheckBox sportBox;

    @FXML
    private CheckBox travelsBox;

    @FXML
    private RadioButton veganRB;

    @FXML
    private RadioButton vegetarianRB;

    @FXML
    private CheckBox videogamesBox;

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
    private ChoiceBox<?> parenthoodInput;

    @FXML
    ObservableList animalList = FXCollections.observableArrayList("1","2","3","4+");

    @FXML
    ObservableList parenthoodList = FXCollections.observableArrayList("Father","Mother","Sister","Brother", "Grandmother", "Grandfather", "Uncle", "Aunt", "Cousin");
    @FXML
    ObservableList cityList = FXCollections.observableArrayList("London","Rome","Paris","New York","Valencia");

    private File file = null;
    public void initialize() {

        dogBox.setItems(animalList);
        catBox.setItems(animalList);
        birdsBox.setItems(animalList);
        rodensBox.setItems(animalList);
        reptilesBox.setItems(animalList);
        otherBox.setItems(animalList);

        cityBox.setItems(cityList);

        parenthoodInput.setItems(parenthoodList);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("Age"));
        parenthoodColumn.setCellValueFactory(new PropertyValueFactory<>("Parenthood"));
    }

    @FXML
    public void addMember() {
        FamilyMember familyMember = new FamilyMember(nameInput.getText(), parseInt(ageInput.getText()), (String) parenthoodInput.getValue());
        ObservableList<FamilyMember> familyMembers = tableViewMembers.getItems();
        familyMembers.add(familyMember);
        tableViewMembers.setItems(familyMembers);
        nameInput.setText("");
        ageInput.setText("");
        parenthoodInput.getSelectionModel().clearSelection();
    }

    @FXML
    public void removeMember(){
        int selectedID = tableViewMembers.getSelectionModel().getSelectedIndex();
        tableViewMembers.getItems().remove(selectedID);
    }

    public void backButton(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    void addFamily(ActionEvent event) {

        List<Animal> animals;
        FamilyPreferences preferences;
        List<FamilyMember> membersList;

        try{
            if(nameField.getText() == null)
                throw new FormEmptyException("Name");
            if(cityBox.getValue() == null)
                throw new FormEmptyException("City");
            if(phoneField.getText() == null)
                throw new FormEmptyException("Phone Number");
            if(addressField.getText() == null)
                throw new FormEmptyException("Address");
            if(!vegetarianRB.isSelected() && !veganRB.isSelected() && !noPrefRB.isSelected())
                throw new FormEmptyException("Food Diet Preferencese");
            if(!singleRB.isSelected() && !sharedRB.isSelected())
                throw new FormEmptyException("House");


            animals = getAnimalList();
            preferences = getPreferences();
            membersList = getMembers();
            if(membersList == null){
                throw new FormEmptyException("Members");
            }

            String email = nameField.getText().toLowerCase();
            email = email.concat("@gmail.com");

            FamilyBean familyBean = new FamilyBean(nameField.getText(), (String) cityBox.getValue(), addressField.getText(), phoneField.getText(), email);
            familyBean.setAnimals(animals);
            familyBean.setMembers(membersList);
            familyBean.setFamilyPreferences(preferences);

            if(file != null) {
                String nameImg = "familyImg/";
                nameImg = nameImg.concat(file.getName());
                familyBean.setImgSrc(nameImg);
            }else{
                familyBean.setImgSrc("image/big-family.png");
            }

            AddFamilyController addFamilyController = new AddFamilyController();
            addFamilyController.createFamily(familyBean);

            ((Node)event.getSource()).getScene().getWindow().hide();

        }catch(FormEmptyException | PhoneFormatException e ){
            e.printStackTrace();
        }
    }

    private FamilyPreferences getPreferences() {

        String room;
        int vegetarian = 0;
        int vegan = 0;
        int travels = 0;
        int sport = 0;
        int books = 0;
        int nature = 0;
        int film = 0;
        int videoGames = 0;
        int cooking = 0;

        if(travelsBox.isSelected()){
            travels = 1;
        }
        if(sportBox.isSelected()){
            sport = 1;
        }
        if(cookingBox.isSelected()){
            cooking = 1;
        }
        if(videogamesBox.isSelected()){
            videoGames = 1;
        }
        if(filmBox.isSelected()){
            film = 1;
        }
        if(booksBox.isSelected()){
            books = 1;
        }
        if(natureBox.isSelected()){
            nature = 1;
        }

        if(vegetarianRB.isSelected()){
            vegetarian = 1;
        }
        else if(veganRB.isSelected()){
            vegan = 1;
        }

        if(singleRB.isSelected())
            room = "Single";
        else
            room = "Shared";

        FamilyPreferences familyPreferences = new FamilyPreferences();
        familyPreferences.setFood(vegetarian, vegan);
        familyPreferences.setHobbies(travels, sport, books, nature, film, videoGames, cooking);
        familyPreferences.setHouse(room);

        return familyPreferences;
    }

    public List<FamilyMember> getMembers() {
        List<FamilyMember> members = new ArrayList<>();
        FamilyMember member;

        ObservableList<FamilyMember> familyMembers = tableViewMembers.getItems();

        for (FamilyMember familyMember : familyMembers) {
            member = familyMember;
            members.add(member);
        }

        return members;
    }

    public List<Animal> getAnimalList() {
        List<Animal> animals = new ArrayList<>();
        int qt;
        String value;
        Animal animal;

        if(dogBox.getValue() != null){
            animal = new Animal("Dog", checkNumberAnimal(dogBox.getValue().toString()));
            animals.add(animal);
        }
        if(catBox.getValue() != null){
            animal = new Animal("Cat", checkNumberAnimal(dogBox.getValue().toString()));
            animals.add(animal);
        }
        if(birdsBox.getValue() != null){
            animal = new Animal("Bird", checkNumberAnimal(dogBox.getValue().toString()));
            animals.add(animal);
        }
        if(rodensBox.getValue() != null){
            animal = new Animal("Roden", checkNumberAnimal(rodensBox.getValue().toString()));
            animals.add(animal);
        }
        if(reptilesBox.getValue() != null){
            animal = new Animal("Reptile", checkNumberAnimal(reptilesBox.getValue().toString()));
            animals.add(animal);
        }
        if(otherBox.getValue() != null){
            animal = new Animal("Other", checkNumberAnimal(otherBox.getValue().toString()));
            animals.add(animal);
        }

        return animals;
    }

    public void loadImage(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FileChooser fileChooser=new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Imagine Files","*.png","*.jpg"));
        file = fileChooser.showOpenDialog(stage).getAbsoluteFile();
        familyImg.setImage(ImageConverterSupport.fromFileToImage(file));
    }

    public int checkNumberAnimal(String number){
        int quantity;
        if(Objects.equals(number, "4+")){
            quantity = 4;
        } else {
            quantity = parseInt(number);
        }
        return quantity;
    }
}
