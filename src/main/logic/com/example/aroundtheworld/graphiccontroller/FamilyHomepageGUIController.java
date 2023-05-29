package com.example.aroundtheworld.graphiccontroller;

import com.example.aroundtheworld.bean.FamilyBean;
import com.example.aroundtheworld.bean.TravelBean;
import com.example.aroundtheworld.appcontroller.TravelsController;
import com.example.aroundtheworld.engineering.Session;
import com.example.aroundtheworld.exception.NotFoundException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class FamilyHomepageGUIController {
    @FXML
    private HBox nextArrivalsList;

    @FXML
    private HBox pastTravelsList;

    @FXML
    void logout() throws IOException {
        LogoutGUIController logoutGUIController = new LogoutGUIController();
        logoutGUIController.logout();
    }

    @FXML
    void toFamilyProfile() throws IOException {
        FamilyGUIController familyGUIController = new FamilyGUIController();
        familyGUIController.toFamilyProfile();
    }

    @FXML
    void toRequestFamily() throws IOException {
        FamilyGUIController familyGUIController = new FamilyGUIController();
        familyGUIController.toRequestFamily();
    }

    public void initialize() throws IOException, NotFoundException {

        FamilyBean familyBean = Session.getCurrentSession().getFamilyBean();

        TravelsController travelsController = new TravelsController();
        List<TravelBean> requestList = travelsController.getFamilyTravels(familyBean);

        for(TravelBean travelBean: requestList){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("TravelItemFamily.fxml"));
            Pane requestBox = fxmlLoader.load();

            TravelItemGUIController travelItemGUIController = fxmlLoader.getController();
            travelItemGUIController.setPane(requestBox);

            LocalDate currentDate = LocalDate.now();
            LocalDate arrival = LocalDate.parse(travelBean.getArrival());

            if (arrival.isAfter(currentDate)){
                travelItemGUIController.setFutureTravelFamily(travelBean);
                nextArrivalsList.getChildren().add(requestBox);
            } else {
                travelItemGUIController.setPastTravelFamily(travelBean);
                pastTravelsList.getChildren().add(requestBox);
            }
        }
    }
}
