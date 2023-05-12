package com.example.aroundtheworld.controller_grafico;

import com.example.aroundtheworld.bean.ResidenceRequestBean;
import com.example.aroundtheworld.bean.StudentBean;
import com.example.aroundtheworld.controller_applicativo.ReserveRoomController;
import com.example.aroundtheworld.engineering.Session;
import com.example.aroundtheworld.engineering.observer.Observer;
import com.example.aroundtheworld.exception.NotFoundException;
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

    public void initialize() throws NotFoundException, IOException {

        StudentBean studentBean = Session.getCurrentSession().getStudentBean();
        ReserveRoomController reserveRoomController = new ReserveRoomController();
        List<ResidenceRequestBean> requestList = reserveRoomController.getModifiedRequest(studentBean.getId());

        for(ResidenceRequestBean requestBean: requestList){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("requestFamilyItem.fxml"));
            Pane requestBox = fxmlLoader.load();
            requestBean.register(this);

            RequestItemGUIController requestItemGUIController = fxmlLoader.getController();
            requestItemGUIController.setPane(requestBox);
            requestItemGUIController.setModifiedRequest(requestBean);

            residenceReqList.getChildren().add(requestBox);

        }
    }

    public void toHomepageStudent() throws IOException {
        StudentGUIControllerJavaFX studentGraphicControllerJavaFX = new StudentGUIControllerJavaFX();
        studentGraphicControllerJavaFX.toHomepageStudent();
    }

    public void toProfileStudent() throws IOException {
        StudentGUIControllerJavaFX studentGraphicControllerJavaFX = new StudentGUIControllerJavaFX();
        studentGraphicControllerJavaFX.toProfileStudent();
    }

    public void toTravelStudent() throws IOException {
        StudentGUIControllerJavaFX studentGraphicControllerJavaFX = new StudentGUIControllerJavaFX();
        studentGraphicControllerJavaFX.toTravelStudent();
    }

    @Override
    public void update(Object object1, Object object2) {
        //
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
