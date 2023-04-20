package com.example.aroundtheworld.controller_grafico;

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

public class CityGraphicControllerJavaFX{

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

    private CityBean city;

    public void setData(CityBean city) {
        this.city = city;

        cityName.setText(city.getNameBean());
        language.setText(city.getLanguageBean());
        residenceName.setText(city.getResidenceBean().getName());
        residenceAddress.setText(city.getResidenceBean().getAddress());
        distanceResidenceSchool.setText(city.getResidenceBean().getDistanceSchool());
        schoolName.setText(city.getSchoolBean().getName());
        schoolAddress.setText(city.getSchoolBean().getAddress());
        schoolCourses.setText(city.getSchoolBean().getCourses());
        schoolHours.setText(city.getSchoolBean().getHours());

        act1Name.setText(city.getAct1Bean());
        act2Name.setText(city.getAct2Bean());
        act3Name.setText(city.getAct3Bean());

        Image image = new Image(getClass().getResourceAsStream(city.getCityImgSrcBean()));
        cityImg.setImage(image);
        image = new Image(getClass().getResourceAsStream(city.getAct1ImgSrcBean()));
        act1Img.setImage(image);
        image = new Image(getClass().getResourceAsStream(city.getAct2ImgSrcBean()));
        act2Img.setImage(image);
        image = new Image(getClass().getResourceAsStream(city.getAct3ImgSrcBean()));
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
