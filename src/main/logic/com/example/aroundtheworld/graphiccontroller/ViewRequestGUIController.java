package com.example.aroundtheworld.graphiccontroller;

import com.example.aroundtheworld.bean.FamilyRequestBean;
import com.example.aroundtheworld.bean.StudentBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.text.Text;

import java.time.Year;

public class ViewRequestGUIController {
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

    public void displayRequest(StudentBean studentBean, FamilyRequestBean familyRequestBean){

        nameText.setText(studentBean.getName());
        surnameText.setText(studentBean.getSurname());
        emailLabel.setText(studentBean.getEmail());
        String age = getAgeFromDate(studentBean.getBirth());
        ageLabel.setText(age);
        phoneLabel.setText(studentBean.getPhoneNumber());
        nationalityLabel.setText(studentBean.getNationality());
        cityLabel.setText(familyRequestBean.getCity());
        arrivalText.setText(familyRequestBean.getArrival());
        departureText.setText(familyRequestBean.getDeparture());
        roomLabel.setText(familyRequestBean.getHouse());
        siblingLabel.setText(getChoice(familyRequestBean.getSiblings()));
        animalLabel.setText(getChoice(familyRequestBean.getAnimals()));
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

    private String getAgeFromDate(String birth) {
        int currYear = Year.now().getValue();
        int birthYear = Integer.parseInt(String.format("%."+ 4 +"s", birth));
        int age = currYear - birthYear;
        return String.valueOf(age);
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
