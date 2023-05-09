package com.example.aroundtheworld.controller_grafico;

import com.example.aroundtheworld.bean.FamilyRequestBean;
import com.example.aroundtheworld.bean.StudentBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.text.Text;

public class ViewFamilyRequestGUIController {

    @FXML
    private Text ageLabel;

    @FXML
    private Text animalLabel;

    @FXML
    private Text arrivalText;

    @FXML
    private Text bookLabel;

    @FXML
    private Text cityLabel;

    @FXML
    private Text cookingLabel;

    @FXML
    private Text departureText;

    @FXML
    private Text emailLabel;

    @FXML
    private Text filmLabel;

    @FXML
    private Text foodLabel;

    @FXML
    private Text nameText;

    @FXML
    private Text nationalityLabel;

    @FXML
    private Text natureLabel;

    @FXML
    private Text phoneLabel;

    @FXML
    private Text roomLabel;

    @FXML
    private Text siblingLabel;

    @FXML
    private Text sportLabel;

    @FXML
    private Text travelLabel;

    @FXML
    private Text videogamesLabel;

    @FXML
    private Text surnameText;

    public void initialize(StudentBean studentBean, FamilyRequestBean familyRequestBean){

        nameText.setText(studentBean.getName());
        surnameText.setText(studentBean.getSurname());
        emailLabel.setText(studentBean.getEmail());
        ageLabel.setText(studentBean.getBirth());
        phoneLabel.setText(studentBean.getPhoneNumber());
        nationalityLabel.setText(studentBean.getNationality());
        cityLabel.setText(familyRequestBean.getCityBean());
        arrivalText.setText(familyRequestBean.getArrivalBean());
        departureText.setText(familyRequestBean.getDepartureBean());
        roomLabel.setText(familyRequestBean.getHouse());
        siblingLabel.setText(getChoice(familyRequestBean.getSiblingsBean()));
        animalLabel.setText(getChoice(familyRequestBean.getAnimalsBean()));
        if (familyRequestBean.getVegan() == 1){
            foodLabel.setText("Vegan");
        }else if(familyRequestBean.getVegetarian()==1){
            foodLabel.setText("Vegetarian");
        }else{
            foodLabel.setText("No Preferences");
        }
        travelLabel.setText(getChoice(familyRequestBean.getTravels()));
        sportLabel.setText(getChoice(familyRequestBean.getSport()));
        bookLabel.setText(getChoice(familyRequestBean.getBooks()));
        natureLabel.setText(getChoice(familyRequestBean.getNature()));
        filmLabel.setText(getChoice(familyRequestBean.getFilm()));
        videogamesLabel.setText(getChoice(familyRequestBean.getVideoGames()));
        cookingLabel.setText(getChoice(familyRequestBean.getCooking()));
    }

    private String getChoice(int choice) {
        if(choice == 1){
            return "Yes";
        }else{
            return "No";
        }
    }

    public void backButton(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
}
