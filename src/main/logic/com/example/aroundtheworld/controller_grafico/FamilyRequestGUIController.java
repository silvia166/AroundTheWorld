package com.example.aroundtheworld.controller_grafico;

import com.example.aroundtheworld.bean.FamilyBean;
import com.example.aroundtheworld.bean.FamilyRequestBean;
import com.example.aroundtheworld.controller_applicativo.ContactFamilyController;
import com.example.aroundtheworld.engineering.Session;
import com.example.aroundtheworld.exception.NotFoundException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.List;

public class FamilyRequestGUIController extends LogoutGUIControllerJavaFX{

    @FXML
    private HBox confirmedReqList;
    @FXML
    private HBox sentReqList;

    public void toFamilyProfile() throws IOException{
        FamilyGUIController familyGUIController = new FamilyGUIController();
        familyGUIController.toFamilyProfile();
    }

    public void toHomepageFamily() throws IOException {
        FamilyGUIController familyGUIController = new FamilyGUIController();
        familyGUIController.toHomepageFamily();
    }

    public void initialize() throws NotFoundException, IOException {

        FamilyBean familyBean = Session.getCurrentSession().getFamilyBean();

        ContactFamilyController contactFamilyController = new ContactFamilyController();
        List<FamilyRequestBean> requestList = contactFamilyController.getFamilyRequests(familyBean);

        for(FamilyRequestBean requestBean: requestList){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("requestFamilyItem.fxml"));
            AnchorPane requestBox = fxmlLoader.load();

            RequestItemGUIController requestItemGUIController = fxmlLoader.getController();
            requestItemGUIController.setFamilyRequest(requestBean);

            if (requestBean.getStatus() == 0){
                sentReqList.getChildren().add(requestBox);
            } else {
                confirmedReqList.getChildren().add(requestBox);
            }

        }
    }
}
