package com.example.aroundtheworld.graphiccontroller;

import com.example.aroundtheworld.bean.FamilyRequestBean;
import com.example.aroundtheworld.bean.ResidenceRequestBean;
import com.example.aroundtheworld.bean.StudentBean;
import com.example.aroundtheworld.appcontroller.BookingFamilyController;
import com.example.aroundtheworld.appcontroller.ReserveRoomController;
import com.example.aroundtheworld.engineering.Session;
import com.example.aroundtheworld.engineering.observer.Observer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class StudentRequestGUIController implements Observer {
    @FXML
    private HBox familyReqList;
    @FXML
    private HBox residenceReqList;

    public void setStudentRequests() throws IOException {

        StudentBean studentBean = Session.getCurrentSession().getStudentBean();
        ReserveRoomController reserveRoomController = new ReserveRoomController();

        List<ResidenceRequestBean> requestList = reserveRoomController.getStudentResidenceRequests(studentBean);

        for (ResidenceRequestBean requestBean : requestList) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("requestStudentItem.fxml"));
            Pane requestBox = fxmlLoader.load();
            requestBean.register(this);

            ResidenceRequestItemGUIController residenceRequestItemGUIController = fxmlLoader.getController();
            residenceRequestItemGUIController.setPane(requestBox);
            if (requestBean.getStatus() == 1) {
                residenceRequestItemGUIController.setModifiedRequest(requestBean);
            } else {
                residenceRequestItemGUIController.setPendingRequest(requestBean);
            }
            residenceReqList.getChildren().add(requestBox);
        }

        BookingFamilyController bookingFamilyController = new BookingFamilyController();
        List<FamilyRequestBean> familyRequestBeanList = bookingFamilyController.getStudentFamilyRequest(studentBean);

        for(FamilyRequestBean familyRequestBean: familyRequestBeanList){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("familyItem.fxml"));
            Pane requestBox = fxmlLoader.load();
            familyRequestBean.register(this);

            FamilyItemGUIController familyItemGUIController = fxmlLoader.getController();
            familyItemGUIController.setPane(requestBox);
            familyItemGUIController.setFamilyRequest(familyRequestBean);
            familyReqList.getChildren().add(requestBox);
        }
    }

    public void toHomepageStudent() throws IOException {
        StudentGUIController studentGraphicControllerJavaFX = new StudentGUIController();
        studentGraphicControllerJavaFX.toHomepageStudent();
    }

    public void toProfileStudent() throws IOException {
        StudentGUIController studentGraphicControllerJavaFX = new StudentGUIController();
        studentGraphicControllerJavaFX.toProfileStudent();
    }

    public void toTravelStudent() throws IOException {
        StudentGUIController studentGraphicControllerJavaFX = new StudentGUIController();
        studentGraphicControllerJavaFX.toTravelStudent();
    }

    @Override
    public void updateResidencePage(ResidenceRequestBean requestBean, Pane pane) {
        if (residenceReqList.getChildren().contains(pane))
            residenceReqList.getChildren().remove(pane);
    }

    @Override
    public void updateFamilyPage(FamilyRequestBean requestBean, Pane pane) {
        if (familyReqList.getChildren().contains(pane))
            familyReqList.getChildren().remove(pane);
    }

    @Override
    public void update() {
        //ignore
    }

    public void backToAccess() throws IOException {
        Session.closeSession();
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("accessPage.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
