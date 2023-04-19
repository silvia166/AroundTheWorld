package com.example.aroundtheworld.controller_grafico;

import com.example.aroundtheworld.bean.CompatibleFamilyBean;
import com.example.aroundtheworld.bean.FamilyBean;
import com.example.aroundtheworld.bean.FamilyRequestBean;
import com.example.aroundtheworld.controller_applicativo.ContactFamilyController;
import com.example.aroundtheworld.engineering.Session;
import com.example.aroundtheworld.engineering.ShowExceptionSupport;
import com.example.aroundtheworld.exception.FormEmptyException;
import com.example.aroundtheworld.exception.MessageException;
import com.example.aroundtheworld.model.Animal;
import com.example.aroundtheworld.model.FamilyMember;
import com.example.aroundtheworld.model.FamilyPreferences;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ContactFamilyGraphicControllerJavaFX {
    @FXML
    private RadioButton animalN;
    @FXML
    private RadioButton animalY;
    @FXML
    private DatePicker arrivalBox;
    @FXML
    private CheckBox booksC;
    @FXML
    private CheckBox cookingC;
    @FXML
    private DatePicker departureBox;
    @FXML
    private CheckBox filmC;
    @FXML
    private CheckBox natureC;
    @FXML
    private RadioButton noPrefRB;
    @FXML
    private RadioButton sharedH;
    @FXML
    private RadioButton siblingN;
    @FXML
    private RadioButton siblingY;
    @FXML
    private RadioButton singleH;
    @FXML
    private CheckBox sportC;
    @FXML
    private CheckBox travelsC;
    @FXML
    private RadioButton veganRB;
    @FXML
    private RadioButton vegetarianRB;
    @FXML
    private CheckBox videogamesC;
    @FXML
    ChoiceBox cityBox;
    @FXML
    ObservableList cityList = FXCollections.observableArrayList("London","Rome","Paris","New York","Valencia");

    @FXML
    public void initialize() {
        cityBox.setItems(cityList);
    }

    public void backButton(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    public void contactFamily(ActionEvent event) throws IOException {
        int siblings = 0;
        int animals = 0;
        int idStudent = Session.getCurrentSession().getStudentBean().getId();

        try {
            if (cityBox.getValue() == null)
                throw new FormEmptyException("City");
            if (arrivalBox.getValue() == null)
                throw new FormEmptyException("Arrival");
            if (departureBox.getValue() == null)
                throw new FormEmptyException("Departure");
            if (!vegetarianRB.isSelected() && !veganRB.isSelected() && !noPrefRB.isSelected())
                throw new FormEmptyException("Food Diet Preferences");
            if (!singleH.isSelected() && !sharedH.isSelected())
                throw new FormEmptyException("House");
            if (!siblingY.isSelected() && !siblingN.isSelected())
                throw new FormEmptyException("Siblings");
            if (!animalY.isSelected() && !animalN.isSelected())
                throw new FormEmptyException("Animals");

            if(siblingY.isSelected()){
                siblings = 1;
            }
            if(animalY.isSelected()){
                animals = 1;
            }

            FamilyPreferences preferences = getRequestPreferences();
            FamilyRequestBean familyRequestBean = new FamilyRequestBean(cityBox.getValue().toString(),arrivalBox.getValue(),departureBox.getValue(),siblings,animals,idStudent);
            familyRequestBean.setFamilyPreferences(preferences);

            ContactFamilyController contactFamilyController = new ContactFamilyController();
            List<CompatibleFamilyBean> families = contactFamilyController.getCompatibleFamilies(familyRequestBean);

            Stage stage =  (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("familyList.fxml"));

            Scene scene = new Scene(fxmlLoader.load());

            FamilyListGraphicControllerJavaFX familyListGraphicControllerJavaFX = fxmlLoader.getController();
            int count = familyListGraphicControllerJavaFX.setList(families, familyRequestBean.getCity());

            stage.setScene(scene);
            stage.show();

            if(count==0){
                throw new MessageException("No families with compatibility \n greater than 50%");
            }

        } catch (FormEmptyException | MessageException  e) {
            ShowExceptionSupport.showException(e.getMessage());
        }
    }

    private FamilyPreferences getRequestPreferences() {

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

        if (travelsC.isSelected()) {
            travels = 1;
        }
        if (sportC.isSelected()) {
            sport = 1;
        }
        if (cookingC.isSelected()) {
            cooking = 1;
        }
        if (videogamesC.isSelected()) {
            videoGames = 1;
        }
        if (filmC.isSelected()) {
            film = 1;
        }
        if (booksC.isSelected()) {
            books = 1;
        }
        if (natureC.isSelected()) {
            nature = 1;
        }

        if (vegetarianRB.isSelected()) {
            vegetarian = 1;
        } else if (veganRB.isSelected()) {
            vegan = 1;
        }

        if (singleH.isSelected())
            room = "Single";
        else
            room = "Shared";

        FamilyPreferences familyPreferences = new FamilyPreferences();
        familyPreferences.setFood(vegetarian, vegan);
        familyPreferences.setHobbies(travels, sport, books, nature, film, videoGames, cooking);
        familyPreferences.setHouse(room);

        return familyPreferences;
    }

}
