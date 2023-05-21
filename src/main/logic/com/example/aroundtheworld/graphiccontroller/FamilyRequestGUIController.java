package com.example.aroundtheworld.graphiccontroller;

import com.example.aroundtheworld.bean.FamilyBean;
import com.example.aroundtheworld.bean.FamilyRequestBean;
import com.example.aroundtheworld.bean.ResidenceRequestBean;
import com.example.aroundtheworld.appcontroller.ContactFamilyController;
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

public class FamilyRequestGUIController implements Observer {

    @FXML
    private HBox confirmedReqList;
    @FXML
    private HBox sentReqList;

    public void backToAccess() throws IOException {
        Session.closeSession();
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("accessPage.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toFamilyProfile() throws IOException{
        FamilyGUIController familyGUIController = new FamilyGUIController();
        familyGUIController.toFamilyProfile();
    }

    public void toHomepageFamily() throws IOException {
        FamilyGUIController familyGUIController = new FamilyGUIController();
        familyGUIController.toHomepageFamily();
    }

    public void initialize() throws IOException {

        FamilyBean familyBean = Session.getCurrentSession().getFamilyBean();

        ContactFamilyController contactFamilyController = new ContactFamilyController();
        List<FamilyRequestBean> requestList = contactFamilyController.getFamilyRequests(familyBean);

        for(FamilyRequestBean requestBean: requestList){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("requestFamilyItem.fxml"));
            Pane requestBox = fxmlLoader.load();
            requestBean.register(this);

            RequestItemGUIController requestItemGUIController = fxmlLoader.getController();
            requestItemGUIController.setPane(requestBox);
            requestItemGUIController.setFamilyRequest(requestBean);

            if (requestBean.getStatus() == 0){
                sentReqList.getChildren().add(requestBox);
            } else {
                confirmedReqList.getChildren().add(requestBox);
            }
        }
    }

    @Override
    public void updateResidence(ResidenceRequestBean requestBean, Pane pane) {
        //ignore
    }

    @Override
    public void updateFamily(FamilyRequestBean requestBean, Pane pane) {
        if (sentReqList.getChildren().contains(pane))
            sentReqList.getChildren().remove(pane);

        if (requestBean.getStatus() == 1) {
            confirmedReqList.getChildren().add(pane);
        }
    }
}
