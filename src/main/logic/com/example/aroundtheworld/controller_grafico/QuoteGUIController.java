package com.example.aroundtheworld.controller_grafico;

import com.example.aroundtheworld.bean.QuoteBean;
import com.example.aroundtheworld.engineering.ShowExceptionSupport;
import com.example.aroundtheworld.exception.FormEmptyException;
import com.example.aroundtheworld.exception.NotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class QuoteGUIController {
    @FXML
    ChoiceBox cityBox = new ChoiceBox();

    @FXML
    ChoiceBox periodBox = new ChoiceBox();

    @FXML
    ObservableList cityList = FXCollections.observableArrayList("London","Rome","Paris","New York","Valencia");
    @FXML
    ObservableList weekList = FXCollections.observableArrayList("1","2","3");
    @FXML
    ObservableList monthList = FXCollections.observableArrayList("1","2","3","4","5","6","7","8","9","10", "11");
    @FXML
    ObservableList yearList = FXCollections.observableArrayList("1","2");

    @FXML
    private RadioButton doubleRB;
    @FXML
    private RadioButton familyRB;
    @FXML
    private RadioButton residenceRB;
    @FXML
    private RadioButton selectMonth;
    @FXML
    private RadioButton selectYear;
    @FXML
    private RadioButton selectWeek;
    @FXML
    private RadioButton singleRB;

    @FXML
    private Label accommodationQuote;

    @FXML
    private Label cityQuote;

    @FXML
    private ImageView imgQuote;

    @FXML
    private Label periodQuote;

    @FXML
    private Label priceLabel;

    @FXML
    public void setQuoteForm(){
        cityBox.setItems(cityList);
        periodBox.setItems(weekList);
    }

    @FXML
    void changeAccommodation() {
        if(familyRB.isSelected()){
            singleRB.setVisible(false);
            doubleRB.setVisible(false);
        } else if (residenceRB.isSelected()){
            singleRB.setVisible(true);
            doubleRB.setVisible(true);
        }
    }
    @FXML
    void changePeriod() {
        if(selectWeek.isSelected()){
            periodBox.setItems(weekList);
        } else if (selectMonth.isSelected()){
            periodBox.setItems(monthList);
        } else if (selectYear.isSelected()){
            periodBox.setItems(yearList);
        }
    }

    public void getStandardInfo(ActionEvent event) throws IOException {
        String room = "double";
        String accommodation = "host family";

        try {
            if (cityBox.getValue() == null)
                throw new FormEmptyException("City");
            if (periodBox.getValue() == null )
                throw new FormEmptyException("Permanence");
            if(!familyRB.isSelected() && !residenceRB.isSelected())
                throw new FormEmptyException("Accommodation");

            if (residenceRB.isSelected()) {
                accommodation = "residence";
                if (!singleRB.isSelected() && !doubleRB.isSelected()) {
                    throw new FormEmptyException("Room Type");
                } else {
                    if (singleRB.isSelected())
                        room = "single";
                }
            }
            int weeks = getSelectedPeriod();
            QuoteBean quoteBean = new QuoteBean(cityBox.getValue().toString(),weeks,accommodation,room);

            Stage stage =  (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("extraActivitiesForm.fxml"));
            Main.setSecondaryStage(stage);

            Scene scene = new Scene(fxmlLoader.load());

            QuoteExtraGUIController quoteExtraGUIController = fxmlLoader.getController();
            quoteExtraGUIController.setExtraForm(quoteBean);

            stage.setScene(scene);
            stage.show();

        } catch (FormEmptyException | NotFoundException e) {
            ShowExceptionSupport.showException(e.getMessage());
        }

    }
    private int getSelectedPeriod() {
        int weeks;

        if (selectWeek.isSelected()){
            weeks = Integer.parseInt(periodBox.getValue().toString());
        } else if (selectMonth.isSelected()){
            weeks = Integer.parseInt(periodBox.getValue().toString())*4;
        } else {
            weeks = Integer.parseInt(periodBox.getValue().toString())*52;
        }
        return weeks;
    }

    public void backButton(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    public void close(ActionEvent event){
        Main.getSecondaryStage().hide();
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    public void showQuoteResult(int price, QuoteBean quoteBean) {
        String path = null;
        accommodationQuote.setText(quoteBean.getAccommodation().toUpperCase());
        cityQuote.setText(quoteBean.getCity().toUpperCase());
        if (quoteBean.getWeeks() < 4) {
            periodQuote.setText(quoteBean.getWeeks() + " WEEKS");
        } else if (quoteBean.getWeeks() < (12*4)){
            periodQuote.setText(quoteBean.getWeeks()/4 + " MONTHS");
        } else {
            periodQuote.setText(quoteBean.getWeeks()/52 + " YEARS");
        }
        priceLabel.setText(price +" €");

        switch (quoteBean.getCity()){
            case "Rome" -> path = "image/romeFlag.png";
            case "Paris" -> path = "image/parisFlag.png";
            case "New York" -> path = "image/newYorkFlag.png";
            case "Valencia" -> path = "image/valenciaFlag.png";
            default -> path = "image/londonFlag.png";
        }
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(path)));
        imgQuote.setImage(image);
    }
}
