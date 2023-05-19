package com.example.aroundtheworld.controller_grafico;
import com.example.aroundtheworld.bean.TravelBean;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class TravelItemGUIController {

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
    private static final String PINKSHADOW = "-fx-background-color: white; -fx-border-radius: 20; -fx-background-radius: 20; -fx-effect: dropShadow(three-pass-box, rgb(192,249,221), 5.0 , 0.0 , 0.0 ,5.0);";

    public void setPastTravel(TravelBean travelBean) {
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

    public void setFutureTravel(TravelBean travelBean){
        this.travelBean = travelBean;
        studNameLabel.setText(travelBean.getStudentName());
        nationalityAgeLabel.setText(travelBean.getStudentNationality()+" - "+travelBean.getStudentAge()+" years old");
        arrivalLabel.setText(travelBean.getArrival());
        departureLabel.setText(travelBean.getDeparture());
        statusLabel.setText("Next arrival");
        bookingPane.setStyle(PINKSHADOW);

        rate1.setVisible(false);
        rate2.setVisible(false);
        rate3.setVisible(false);
        rate4.setVisible(false);
        rate5.setVisible(false);
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }
}
