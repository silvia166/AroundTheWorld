package com.example.aroundtheworld.controller_grafico;

import com.example.aroundtheworld.bean.CompatibleFamilyBean;
import com.example.aroundtheworld.bean.FamilyBean;
import com.example.aroundtheworld.bean.FamilyRequestBean;
import com.example.aroundtheworld.model.Animal;
import com.example.aroundtheworld.model.FamilyMember;
import com.example.aroundtheworld.model.FamilyPreferences;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;


public class FamilyListGraphicControllerJavaFX {

    Stage stage;
    @FXML
    private GridPane familyGrid = new GridPane();
    @FXML
    private List<CompatibleFamilyBean> families = new ArrayList<>();

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
    private ImageView imgFamily;

    @FXML
    private TableView<FamilyMember> tableViewMembers;

    @FXML
    private TableColumn<FamilyMember, String> nameColumn;

    @FXML
    private TableColumn<FamilyMember, Integer> ageColumn;

    @FXML
    private TableColumn<FamilyMember, String> parenthoodColumn;

    @FXML
    private Label phone;

    @FXML
    private Label compatibilityL;

    private String cityString;

    public void backToForm(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("contactFamilyForm.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root,918,560));
        stage.centerOnScreen();
        stage.show();
    }

    public int setList(List<CompatibleFamilyBean> compatibleFamilyBeans, FamilyRequestBean familyRequestBean) throws IOException {
        this.families = compatibleFamilyBeans;
        this.cityString = familyRequestBean.getCity();
        int column = 0;
        int row = 1;
        int count = 0;

        try {

            for (CompatibleFamilyBean family : families) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("familyItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                FamilyItemGraphicControllerJavaFX familyItemController = fxmlLoader.getController();

                if(family.getCompatibility()>=50.0){
                    count++;
                }
                    familyItemController.setData(family, familyRequestBean);

                    if (column == 3) {
                        column = 0;
                        row++;
                    }

                    familyGrid.add(anchorPane, column++, row);

                    familyGrid.setMinWidth(Region.USE_COMPUTED_SIZE);
                    familyGrid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    familyGrid.setMaxWidth(Region.USE_PREF_SIZE);

                    familyGrid.setMinHeight(Region.USE_COMPUTED_SIZE);
                    familyGrid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    familyGrid.setMaxHeight(Region.USE_PREF_SIZE);

                    GridPane.setMargin(anchorPane, new Insets(10));
            }

        } catch (IOException e){
            e.printStackTrace();
        }

        return count;
    }

    public void setSelectedProfile(FamilyBean familyBean, float compatibility) {

        FamilyPreferences pref = familyBean.getFamilyPreferences();

        String listAnimal = "";
        int count = 0;
        String animalQuantity;
        String animalType;

        name.setText(familyBean.getName());
        address.setText(familyBean.getAddress());
        house.setText(familyBean.getFamilyPreferences().getHouse());
        phone.setText(familyBean.getPhone());
        compatibilityL.setText(String.valueOf(compatibility));

        if (familyBean.getImgSrc() != null){
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(familyBean.getImgSrc())));
            imgFamily.setImage(image);
        }

        for (Animal animal : familyBean.getAnimals()) {
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

        animals.setText(listAnimal);

        Iterator<String> iteratorfood = checkFood(pref.getVegetarian(), pref.getVegan()).iterator();

        count = 0;
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

        food.setText(listFood);

        Iterator<String> iteratorhobby = checkHobbies(pref.getTravels(),pref.getBooks(), pref.getFilm(), pref.getVideoGames(), pref.getNature(), pref.getCooking(), pref.getSport()).iterator();

        count = 0;
        String listHobby = null;

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

        hobbies.setText(listHobby);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("Age"));
        parenthoodColumn.setCellValueFactory(new PropertyValueFactory<>("Parenthood"));

        Iterator<FamilyMember> iteratormember = familyBean.getMembers().iterator();

        ObservableList<FamilyMember> familyMembers = tableViewMembers.getItems();

        while(iteratormember.hasNext()){
            FamilyMember member = iteratormember.next();
            familyMembers.add(member);
        }
        tableViewMembers.setItems(familyMembers);
    }

    public void backToFamilyList(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    private static List<String> checkHobbies(int travels, int books, int film, int videoGames, int nature, int cooking, int sport) {

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
        return hobbies;
    }

    private static List<String> checkFood(int vegetarian, int vegan) {
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
        return food;
    }

    public void savedRequest() throws IOException {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UNDECORATED);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("savedRequest.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        dialog.setScene(scene);
        dialog.centerOnScreen();
        dialog.show();
    }

    @FXML
    void backStudHomepage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("homepageStudent.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Main.getStage().setScene(scene);
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    void close(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
}
