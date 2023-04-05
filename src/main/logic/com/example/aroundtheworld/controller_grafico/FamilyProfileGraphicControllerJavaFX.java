package com.example.aroundtheworld.controller_grafico;

import com.example.aroundtheworld.bean.FamilyBean;
import com.example.aroundtheworld.bean.StudentBean;
import com.example.aroundtheworld.engineering.Session;
import com.example.aroundtheworld.model.Animal;
import com.example.aroundtheworld.model.FamilyMember;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;

public class FamilyProfileGraphicControllerJavaFX extends LogoutGraphicControllerJavaFX{
    Stage stage;
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

    public void toRequestFamily(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("requestFamily.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toHomepageFamily(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("homepageFamily.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() {

        FamilyBean familyBean = Session.getCurrentSession().getFamilyBean();

        String listAnimal = null;
        int count = 0;

        name.setText(familyBean.getName());
        address.setText(familyBean.getAddress());
        house.setText(familyBean.getHouse());
        phone.setText(familyBean.getPhone());

        if (familyBean.getImgSrc() != null){
            Image image = new Image(getClass().getResourceAsStream(familyBean.getImgSrc()));
            imgFamily.setImage(image);
        }

        Iterator<Animal> iteratoranimal = familyBean.getAnimals().iterator();

        while(iteratoranimal.hasNext()){
            Animal animal = iteratoranimal.next();

            if(count==0){
                listAnimal = animal.getQuantity() + " " + animal.getType();
                count = 1;
            }else{
                listAnimal = listAnimal + ", " + animal.getQuantity() + " " + animal.getType();
            }

        }

        animals.setText(listAnimal);

        Iterator<String> iteratorfood = familyBean.getFood().iterator();

        count = 0;
        String listFood = null;

        while(iteratorfood.hasNext()){
            String food = iteratorfood.next();

            if(count==0){
                listFood = food;
                count = 1;
            }else{
                listFood = listFood + ", " + food;
            }
        }

        food.setText(listFood);

        Iterator<String> iteratorhobby = familyBean.getHoobies().iterator();

        count = 0;
        String listHobby = null;

        while(iteratorhobby.hasNext()){
            String hobby = iteratorhobby.next();

            if(count==0){
                listHobby = hobby;
                count = 1;
            }else{
                listHobby = listHobby + ", " + hobby;
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
}
