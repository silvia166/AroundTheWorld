package com.example.aroundtheworld.controller_grafico;

import com.example.aroundtheworld.bean.FamilyBean;
import com.example.aroundtheworld.bean.NewStudentBean;
import com.example.aroundtheworld.controller_applicativo.AddFamilyController;
import com.example.aroundtheworld.controller_applicativo.CreateAccountController;
import com.example.aroundtheworld.exception.EmailFormatException;
import com.example.aroundtheworld.exception.FormEmptyException;
import com.example.aroundtheworld.exception.PhoneFormatException;
import com.example.aroundtheworld.model.Animal;
import com.example.aroundtheworld.model.FamilyMember;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.io.IOException;
import java.lang.reflect.Member;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AddFamilyGraphicControllerJavaFX {

    @FXML
    private Button addPhotoBtn;

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
    private CheckBox filmBox;

    @FXML
    private ToggleGroup food;

    @FXML
    private ToggleGroup food1;

    @FXML
    private RadioButton glutenFreeRB;

    @FXML
    private ToggleGroup house;

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
    private TextField parenthoodInput;

    @FXML
    ObservableList animalList = FXCollections.observableArrayList("1","2","3","4+");

    @FXML
    ObservableList cityList = FXCollections.observableArrayList("London","Rome","Paris","New York","Valencia");

    public void initialize() {

        dogBox.setItems(animalList);
        catBox.setItems(animalList);
        birdsBox.setItems(animalList);
        rodensBox.setItems(animalList);
        reptilesBox.setItems(animalList);
        otherBox.setItems(animalList);

        cityBox.setItems(cityList);

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

    @FXML
    void addFamily(ActionEvent event) throws IOException, FormEmptyException, PhoneFormatException{

        String room;

        List<Animal> animals;
        List<String> hobbies;
        List<String> food;
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
            if(!vegetarianRB.isSelected() && !veganRB.isSelected() && !glutenFreeRB.isSelected() && !noPrefRB.isSelected())
                throw new FormEmptyException("Food Diet Preferencese");
            if(!singleRB.isSelected() && !sharedRB.isSelected())
                throw new FormEmptyException("House");

            if(singleRB.isSelected() == true)
                room = "Single Room";
            else
                room = "Shared Room";

            animals = getAnimalList();
            hobbies = getHobbies();
            food = getFoodPref();
            membersList = getMembers();

            String email = nameField.getText();
            email = email.concat("@gmail.com");

            FamilyBean familyBean = new FamilyBean(nameField.getText(), (String) cityBox.getValue(), addressField.getText(), room, phoneField.getText(), email);
            familyBean.setAnimals(animals);
            familyBean.setHoobies(hobbies);
            familyBean.setFood(food);
            familyBean.setMembers(membersList);


            AddFamilyController addFamilyController = new AddFamilyController();
            addFamilyController.createFamily(familyBean);

            ((Node)event.getSource()).getScene().getWindow().hide();

        }catch(FormEmptyException | PhoneFormatException e ){
            e.printStackTrace();
        }
    }

    public List<FamilyMember> getMembers() {
        List<FamilyMember> members = null;
        FamilyMember member;

        ObservableList<FamilyMember> familyMembers = tableViewMembers.getItems();
        Iterator<FamilyMember> iterator = familyMembers.iterator();

        while(iterator.hasNext()){
            member = iterator.next();
            members.add(member);
        }

        return members;
    }

    public List<String> getFoodPref() {
        List<String> food = new ArrayList<>();

        if(vegetarianRB.isSelected()){
            food.add("Vegetarian");
        }
        else if(veganRB.isSelected()){
            food.add("Vegan");
        }

        if(glutenFreeRB.isSelected()){
            food.add("Gluten Free");
        }

        if(noPrefRB.isSelected()){
            food.add("No Preferences");
        }

        return food;
    }

    public List<String> getHobbies() {
        List<String> hobbies = new ArrayList<>();

        if(travelsBox.isSelected()){
            hobbies.add("Travels");
        }
        if(sportBox.isSelected()){
            hobbies.add("Sport");
        }
        if(cookingBox.isSelected()){
            hobbies.add("Cooking");
        }
        if(videogamesBox.isSelected()){
            hobbies.add("Video Games");
        }
        if(filmBox.isSelected()){
            hobbies.add("Film");
        }
        if(booksBox.isSelected()){
            hobbies.add("Books");
        }
        if(natureBox.isSelected()){
            hobbies.add("Nature");
        }

        return hobbies;
    }

    public List<Animal> getAnimalList() {
        List<Animal> animals = null;
        Animal animal;

        if(dogBox.getValue() != null){
            animal = new Animal("Dog", (int) dogBox.getValue());
            animals.add(animal);
        }
        if(catBox.getValue() != null){
            animal = new Animal("Cat", (int) catBox.getValue());
            animals.add(animal);
        }
        if(birdsBox.getValue() != null){
            animal = new Animal("Bird", (int) birdsBox.getValue());
            animals.add(animal);
        }
        if(rodensBox.getValue() != null){
            animal = new Animal("Roden", (int) rodensBox.getValue());
            animals.add(animal);
        }
        if(reptilesBox.getValue() != null){
            animal = new Animal("Reptile", (int) reptilesBox.getValue());
            animals.add(animal);
        }
        if(otherBox.getValue() != null){
            animal = new Animal("Other", (int) otherBox.getValue());
            animals.add(animal);
        }

        return animals;
    }
}
