package com.example.aroundtheworld.controller_grafico;

import com.example.aroundtheworld.bean.CompatibleFamilyBean;
import com.example.aroundtheworld.bean.FamilyBean;
import com.example.aroundtheworld.bean.FamilyRequestBean;
import com.example.aroundtheworld.controller_applicativo.ContactFamilyController;
import com.example.aroundtheworld.engineering.ShowExceptionSupport;
import com.example.aroundtheworld.exception.DuplicateRequestException;
import com.example.aroundtheworld.exception.NotFoundException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class FamilyItemGUIController {
        @FXML
        private Label compatibility;
        @FXML
        private Label familyCity;
        @FXML
        private Label familyName;
        @FXML
        private ImageView img;
        private CompatibleFamilyBean family;
        private FamilyRequestBean familyRequest;
        @FXML
        void sendRequest() throws IOException {
                familyRequest.setCompatibility(family.getCompatibility());
                familyRequest.setIdFamily(family.getId());
                ContactFamilyController contactFamilyController = new ContactFamilyController();
                try {
                        contactFamilyController.saveRequest(familyRequest);
                        Main.getSecondaryStage().hide();
                        ShowExceptionSupport.showException("Your request has been successfully saved!");

                } catch (DuplicateRequestException e) {
                        ShowExceptionSupport.showException(e.getMessage());
                }
        }
        @FXML
        void viewProfile() throws NotFoundException, IOException {
                ContactFamilyController contactFamilyController = new ContactFamilyController();
                FamilyBean familyBean = contactFamilyController.getFamilyProfile(family);

                Stage stage =  new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.UNDECORATED);
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("selectedFamilyProfile.fxml"));

                Scene scene = new Scene(fxmlLoader.load());

                FamilyProfileGUIController familyProfileGUIController = fxmlLoader.getController();
                familyProfileGUIController.initializeSelectedProfile(familyBean, this.family.getCompatibility());

                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();
        }

        public void setData(CompatibleFamilyBean compatibleFamilyBean, FamilyRequestBean familyRequestBean) {

                this.family = compatibleFamilyBean;
                this.familyRequest = familyRequestBean;

                familyName.setText(compatibleFamilyBean.getName()+"  family");
                familyCity.setText(familyRequestBean.getCityBean());
                compatibility.setText(""+compatibleFamilyBean.getCompatibility()+"%");
                if(compatibleFamilyBean.getImgSrc() != null){
                        Image image = new Image(getClass().getResourceAsStream(compatibleFamilyBean.getImgSrc()));
                        img.setImage(image);
                }
        }

}


