package com.example.aroundtheworld.graphiccontroller;

import com.example.aroundtheworld.bean.StudentBean;
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

public class StudentTravelGUIController {

    @FXML
    private HBox futureTravelList;

    @FXML
    private HBox pastTravelList;

    public void initialize() throws IOException, NotFoundException {

        StudentBean studentBean = Session.getCurrentSession().getStudentBean();

        TravelsController travelsController = new TravelsController();
        List<TravelBean> requestList = travelsController.getStudentTravels(studentBean);

        for(TravelBean travelBean: requestList){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("travelItemStudent.fxml"));
            Pane requestBox = fxmlLoader.load();

            TravelItemGUIController travelItemGUIController = fxmlLoader.getController();
            travelItemGUIController.setPane(requestBox);

            LocalDate currentDate = LocalDate.now();
            LocalDate arrival = LocalDate.parse(travelBean.getArrival());

            if (arrival.isAfter(currentDate)){
                travelItemGUIController.setFutureTravelStudent(travelBean);
                futureTravelList.getChildren().add(requestBox);
            } else {
                travelItemGUIController.setPastTravelStudent(travelBean);
                pastTravelList.getChildren().add(requestBox);
            }
        }
    }

    @FXML
    void logout() throws IOException {
        LogoutGUIController logoutGUIController = new LogoutGUIController();
        logoutGUIController.logout();
    }

    @FXML
    void toHomepageStudent() throws IOException {
        StudentGUIController studentGUIController = new StudentGUIController();
        studentGUIController.toHomepageStudent();
    }

    @FXML
    void toProfileStudent() throws IOException {
        StudentGUIController studentGUIController = new StudentGUIController();
        studentGUIController.toProfileStudent();
    }

    @FXML
    void toRequestStudent() throws IOException {
        StudentGUIController studentGUIController = new StudentGUIController();
        studentGUIController.toRequestStudent();
    }

}
