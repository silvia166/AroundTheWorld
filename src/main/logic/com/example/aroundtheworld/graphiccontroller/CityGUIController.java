package com.example.aroundtheworld.graphiccontroller;

import com.example.aroundtheworld.bean.CityBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class CityGUIController {
    @FXML
    private Label distanceResidenceSchool;
    @FXML
    private Label residenceAddress;
    @FXML
    private Label schoolAddress;
    @FXML
    private Label schoolCourses;
    @FXML
    private Label schoolHours;
    @FXML
    private Label schoolName;
    @FXML
    private ImageView act1Img;
    @FXML
    private Label act1Name;
    @FXML
    private ImageView act2Img;
    @FXML
    private Label act2Name;
    @FXML
    private ImageView act3Img;
    @FXML
    private Label act3Name;
    @FXML
    private ImageView cityImg;
    @FXML
    private Label cityName;
    @FXML
    private Label language;
    @FXML
    private Label residenceName;

    public void setData(CityBean city) {

        cityName.setText(city.getName());
        language.setText(city.getLanguage());
        residenceName.setText(city.getName()+" Residence");
        residenceAddress.setText(city.getAddressResidence());
        distanceResidenceSchool.setText(city.getDistanceSchool());
        schoolName.setText(city.getName()+" School");
        schoolAddress.setText(city.getAddressSchool());
        schoolCourses.setText(city.getCourses());
        schoolHours.setText(city.getHours());

        act1Name.setText(city.getAct1());
        act2Name.setText(city.getAct2());
        act3Name.setText(city.getAct3());

        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(city.getCityImgSrc())));
        cityImg.setImage(image);
        image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(city.getAct1ImgSrc())));
        act1Img.setImage(image);
        image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(city.getAct2ImgSrc())));
        act2Img.setImage(image);
        image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(city.getAct3ImgSrc())));
        act3Img.setImage(image);
    }

    public void backToAccess() throws IOException {
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("accessPage.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
