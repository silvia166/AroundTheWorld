package com.example.aroundtheworld.graphiccontroller;
import com.example.aroundtheworld.bean.TravelBean;
import com.example.aroundtheworld.appcontroller.RateController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class TravelItemGUIController {
    @FXML
    private Label accommodation;
    @FXML
    private Label compatibility;
    @FXML
    private Label cityLabel;
    @FXML
    private ImageView img;
    @FXML
    private AnchorPane reqPane;
    @FXML
    private Label arrivalLabel;
    @FXML
    private AnchorPane bookingPane;
    @FXML
    private Label departureLabel;
    @FXML
    private Label nationalityAgeLabel;
    @FXML
    private ImageView rate1;
    @FXML
    private ImageView rate2;
    @FXML
    private ImageView rate3;
    @FXML
    private ImageView rate4;
    @FXML
    private ImageView rate5;
    @FXML
    private Label statusLabel;
    @FXML
    private Label studNameLabel;

    private Pane pane;
    private TravelBean travelBean;

    private static final String YELLOWSHADOW = "-fx-background-color: white; -fx-border-radius: 20; -fx-background-radius: 20; -fx-effect: dropShadow(three-pass-box, rgb(255,178,11), 5.0 , 0.0 , 0.0 ,5.0);";
    private static final String GREENSHADOW = "-fx-background-color: white; -fx-border-radius: 20; -fx-background-radius: 20; -fx-effect: dropShadow(three-pass-box, rgb(192,249,221), 5.0 , 0.0 , 0.0 ,5.0);";

    Image imageStar = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/star.png")));

    public void setPastTravelFamily(TravelBean travelBean) {
        this.travelBean = travelBean;
        studNameLabel.setText(travelBean.getStudentName());
        nationalityAgeLabel.setText(travelBean.getStudentNationality() + " - " + travelBean.getStudentAge() + " years old");
        arrivalLabel.setText(travelBean.getArrival());
        departureLabel.setText(travelBean.getDeparture());
        bookingPane.setStyle(YELLOWSHADOW);

        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/star.png")));
        switch (travelBean.getRate()) {
            case 5 -> {
                rate5.setImage(image);
                rate4.setImage(image);
                rate3.setImage(image);
                rate2.setImage(image);
                rate1.setImage(image);

            }
            case 4 -> {
                rate4.setImage(image);
                rate3.setImage(image);
                rate2.setImage(image);
                rate1.setImage(image);
            }
            case 3 -> {
                rate3.setImage(image);
                rate2.setImage(image);
                rate1.setImage(image);
            }
            case 2 -> {
                rate2.setImage(image);
                rate1.setImage(image);

            }
            case 1 -> rate1.setImage(image);
            default -> {
                statusLabel.setText("Not rated yet");
                rate1.setVisible(false);
                rate2.setVisible(false);
                rate3.setVisible(false);
                rate4.setVisible(false);
                rate5.setVisible(false);
            }
        }
    }

    public void setFutureTravelFamily(TravelBean travelBean){
        this.travelBean = travelBean;
        studNameLabel.setText(travelBean.getStudentName());
        nationalityAgeLabel.setText(travelBean.getStudentNationality()+" - "+travelBean.getStudentAge()+" years old");
        arrivalLabel.setText(travelBean.getArrival());
        departureLabel.setText(travelBean.getDeparture());
        statusLabel.setText("Next arrival");
        bookingPane.setStyle(GREENSHADOW);

        rate1.setVisible(false);
        rate2.setVisible(false);
        rate3.setVisible(false);
        rate4.setVisible(false);
        rate5.setVisible(false);
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public void setFutureTravelStudent(TravelBean travelBean) {
        this.travelBean = travelBean;
        arrivalLabel.setText(travelBean.getArrival());
        departureLabel.setText(travelBean.getDeparture());
        statusLabel.setText("Next travel");
        cityLabel.setText(travelBean.getCity());
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(travelBean.getImg())));
        img.setImage(image);
        bookingPane.setStyle(GREENSHADOW);

        rate1.setVisible(false);
        rate2.setVisible(false);
        rate3.setVisible(false);
        rate4.setVisible(false);
        rate5.setVisible(false);

        if(travelBean.getFamilyName() == null){
            accommodation.setText("Residence");
            cityLabel.setText(travelBean.getCity());
        }else{
            accommodation.setText(travelBean.getFamilyName() + " Family");
        }
    }


    public void setPastTravelStudent(TravelBean travelBean) {
        this.travelBean = travelBean;
        arrivalLabel.setText(travelBean.getArrival());
        departureLabel.setText(travelBean.getDeparture());
        cityLabel.setText(travelBean.getCity());
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(travelBean.getImg())));
        img.setImage(image);
        bookingPane.setStyle(YELLOWSHADOW);

        if(travelBean.getFamilyName() == null){

            accommodation.setText("Residence");
            cityLabel.setText(travelBean.getCity());

            rate1.setVisible(false);
            rate2.setVisible(false);
            rate3.setVisible(false);
            rate4.setVisible(false);
            rate5.setVisible(false);

        }else{

            accommodation.setText(travelBean.getFamilyName() + " Family");
            Image imageStarGrey = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/greyStar.png")));
            switch (travelBean.getRate()) {
                case 5 -> {
                    rate5.setImage(imageStar);
                    rate4.setImage(imageStar);
                    rate3.setImage(imageStar);
                    rate2.setImage(imageStar);
                    rate1.setImage(imageStar);

                }
                case 4 -> {
                    rate4.setImage(imageStar);
                    rate3.setImage(imageStar);
                    rate2.setImage(imageStar);
                    rate1.setImage(imageStar);
                }
                case 3 -> {
                    rate3.setImage(imageStar);
                    rate2.setImage(imageStar);
                    rate1.setImage(imageStar);
                }
                case 2 -> {
                    rate2.setImage(imageStar);
                    rate1.setImage(imageStar);

                }
                case 1 -> rate1.setImage(imageStar);
                default -> {
                    rate5.setImage(imageStarGrey);
                    rate4.setImage(imageStarGrey);
                    rate3.setImage(imageStarGrey);
                    rate2.setImage(imageStarGrey);
                    rate1.setImage(imageStarGrey);
                }
            }
        }
    }

    @FXML
    void rateTravel(MouseEvent event) {
        int rate = 0;
        if(travelBean.getRate()==0) {
            if (event.getSource().equals(rate1)) {
                rate1.setImage(imageStar);
                rate = 1;
            } else if (event.getSource().equals(rate2)) {
                rate = 2;
                rate2.setImage(imageStar);
                rate1.setImage(imageStar);
            } else if (event.getSource().equals(rate3)) {
                rate = 3;
                rate3.setImage(imageStar);
                rate2.setImage(imageStar);
                rate1.setImage(imageStar);
            } else if (event.getSource().equals(rate4)) {
                rate = 4;
                rate4.setImage(imageStar);
                rate3.setImage(imageStar);
                rate2.setImage(imageStar);
                rate1.setImage(imageStar);
            } else if (event.getSource().equals(rate5)) {
                rate = 5;
                rate5.setImage(imageStar);
                rate4.setImage(imageStar);
                rate3.setImage(imageStar);
                rate2.setImage(imageStar);
                rate1.setImage(imageStar);
            }

            RateController rateController = new RateController();
            rateController.rateFamily(rate, travelBean.getId());
        }
    }


}
