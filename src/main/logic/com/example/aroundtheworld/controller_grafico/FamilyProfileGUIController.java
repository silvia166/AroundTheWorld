package com.example.aroundtheworld.controller_grafico;

import com.example.aroundtheworld.bean.AnimalBean;
import com.example.aroundtheworld.bean.FamilyBean;
import com.example.aroundtheworld.bean.FamilyMemberBean;
import com.example.aroundtheworld.engineering.Session;
import com.example.aroundtheworld.exception.NotFoundException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class FamilyProfileGUIController {
    Scene scene;
    @FXML
    private Label address;

    @FXML
    private Label name;

    @FXML
    private Label animals;

    @FXML
    private Label food;

    @FXML
    private Label hobbies;

    @FXML
    private Label house;

    @FXML
    private Label compatibilityL;

    @FXML
    private ImageView imgFamily;

    @FXML
    private TableView<FamilyMemberBean> tableViewMembers;

    @FXML
    private TableColumn<FamilyMemberBean, String> nameColumn;

    @FXML
    private TableColumn<FamilyMemberBean, Integer> ageColumn;

    @FXML
    private TableColumn<FamilyMemberBean, String> parenthoodColumn;

    @FXML
    private Label phone;

    public void toRequestFamily() throws IOException, NotFoundException {
        FamilyGUIController familyGUIController = new FamilyGUIController();
        familyGUIController.toRequestFamily();
    }

    public void toHomepageFamily() throws IOException {
        FamilyGUIController familyGUIController = new FamilyGUIController();
        familyGUIController.toHomepageFamily();
    }
    public void initializeProfile() {

        FamilyBean familyBean = Session.getCurrentSession().getFamilyBean();

        name.setText(familyBean.getName());
        address.setText(familyBean.getAddress());
        house.setText(familyBean.getHouse());
        phone.setText(familyBean.getPhone());

        if (familyBean.getImgSrc() != null){
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(familyBean.getImgSrc())));
            imgFamily.setImage(image);
        }
        String listAnimal = checkAnimals(familyBean.getAnimals());
        animals.setText(listAnimal);
        String listFood = checkFood(familyBean.getVegetarian(), familyBean.getVegan());
        food.setText(listFood);
        String listHobby = checkHobbies(familyBean.getTravels(),familyBean.getBooks(), familyBean.getFilm(), familyBean.getVideoGames(), familyBean.getNature(), familyBean.getCooking(), familyBean.getSport());
        hobbies.setText(listHobby);
        setTableViewMembers(familyBean.getMembers());
    }

    public void displaySelectedProfile(FamilyBean familyBean, float compatibility) {

        name.setText(familyBean.getName());
        address.setText(familyBean.getAddress());
        compatibilityL.setText(compatibility +"%");
        house.setText(familyBean.getHouse());
        phone.setText(familyBean.getPhone());

        String listAnimal = checkAnimals(familyBean.getAnimals());
        animals.setText(listAnimal);
        String listFood = checkFood(familyBean.getVegetarian(), familyBean.getVegan());
        food.setText(listFood);
        String listHobby = checkHobbies(familyBean.getTravels(),familyBean.getBooks(), familyBean.getFilm(), familyBean.getVideoGames(), familyBean.getNature(), familyBean.getCooking(), familyBean.getSport());
        hobbies.setText(listHobby);
        setTableViewMembers(familyBean.getMembers());

        if (familyBean.getImgSrc() != null){
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(familyBean.getImgSrc())));
            imgFamily.setImage(image);
        }
    }

    private void setTableViewMembers(List<FamilyMemberBean> members) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("Age"));
        parenthoodColumn.setCellValueFactory(new PropertyValueFactory<>("Parenthood"));

        Iterator<FamilyMemberBean> iteratormember = members.iterator();

        ObservableList<FamilyMemberBean> familyMembers = tableViewMembers.getItems();

        while(iteratormember.hasNext()){
            FamilyMemberBean member = iteratormember.next();
            familyMembers.add(member);
        }
        tableViewMembers.setItems(familyMembers);
    }

    public void backToFamilyList(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    private String checkAnimals(List<AnimalBean> animals) {

        String listAnimal = "";
        String animalQuantity;
        String animalType;
        int count = 0;

        for (AnimalBean animal : animals) {
            animalQuantity = String.valueOf(animal.getQuantity());
            animalType = animal.getType();

            if (count == 0) {
                listAnimal = listAnimal.concat(animalQuantity);
                listAnimal = listAnimal.concat(" ");
                listAnimal = listAnimal.concat(animalType);
                count = 1;
            } else {
                listAnimal = listAnimal.concat(", ");
                listAnimal = listAnimal.concat(animalQuantity);
                listAnimal = listAnimal.concat(" ");
                listAnimal = listAnimal.concat(animalType);
            }
        }

        if (animals.isEmpty()) {
            listAnimal = listAnimal.concat("No animals");
        }
        return listAnimal;
    }

    private static String checkHobbies(int travels, int books, int film, int videoGames, int nature, int cooking, int sport) {

        List<String> hobbies = new ArrayList<>();

        if (travels == 1){
            hobbies.add("Travels");
        }
        if (books == 1){
            hobbies.add("Books");
        }
        if (film == 1){
            hobbies.add("Film");
        }
        if (videoGames == 1){
            hobbies.add("Video Games");
        }
        if (nature == 1){
            hobbies.add("Nature");
        }
        if (cooking == 1){
            hobbies.add("Cooking");
        }
        if (sport == 1){
            hobbies.add("Sport");
        }

        int count = 0;
        String listHobby = null;

        Iterator<String> iteratorhobby = hobbies.iterator();

        while(iteratorhobby.hasNext()){
            String hobby = iteratorhobby.next();

            if(count==0){
                listHobby = hobby;
                count = 1;
            }else{
                listHobby = listHobby.concat(", ");
                listHobby = listHobby.concat(hobby);
            }
        }
        return listHobby;
    }

    private static String checkFood(int vegetarian, int vegan) {
        List<String> food = new ArrayList<>();

        if (vegetarian == 1) {
            food.add("Vegetarian");
        }
        else if (vegan == 1) {
            food.add("Vegan");
        }
        else {
            food.add("No Preferences");
        }

        Iterator<String> iteratorfood = food.iterator();

        int count = 0;
        String listFood = null;

        while(iteratorfood.hasNext()){
            String foodPref = iteratorfood.next();

            if(count==0){
                listFood = foodPref;
                count = 1;
            }else{
                listFood = listFood.concat(", ");
                listFood = listFood.concat(foodPref);
            }
        }

        return listFood;
    }

    public void logout() throws IOException {
        LogoutGUIController logoutGUIController = new LogoutGUIController();
        logoutGUIController.logout();
    }

}
