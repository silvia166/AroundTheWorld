package com.example.aroundtheworld.controller_grafico;

import com.example.aroundtheworld.bean.CompatibleFamilyBean;
import com.example.aroundtheworld.bean.FamilyBean;
import com.example.aroundtheworld.controller_applicativo.ContactFamilyController;
import com.example.aroundtheworld.exception.NotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class FamilyItemGraphicControllerJavaFX {

        @FXML
        private Label compatibility;
        @FXML
        private Label familyCity;
        @FXML
        private Label familyName;
        @FXML
        private ImageView img;

        @FXML
        private Button sendRequestBtn;
        @FXML
        private Button viewProfileBtn;

        private CompatibleFamilyBean family;
        @FXML
        void sendRequest(ActionEvent event) {

        }
        @FXML
        void viewProfile(ActionEvent event) throws NotFoundException, IOException {
                ContactFamilyController contactFamilyController = new ContactFamilyController();
                FamilyBean familyBean = contactFamilyController.getFamilyProfile(family);

                Stage stage =  new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.UNDECORATED);
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("selectedFamilyProfile.fxml"));

                Scene scene = new Scene(fxmlLoader.load());

                FamilyListGraphicControllerJavaFX familyListGraphicControllerJavaFX = fxmlLoader.getController();
                familyListGraphicControllerJavaFX.setSelectedProfile(familyBean, this.family.getCompatibility());

                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();
        }

        public void setData(CompatibleFamilyBean compatibleFamilyBean, String city) {

                this.family = compatibleFamilyBean;

                familyName.setText(compatibleFamilyBean.getName()+"  family");
                familyCity.setText(city);
                compatibility.setText(""+compatibleFamilyBean.getCompatibility()+"%");
                if(compatibleFamilyBean.getImgSrc() != null){
                        Image image = new Image(getClass().getResourceAsStream(compatibleFamilyBean.getImgSrc()));
                        img.setImage(image);
                }
        }

}


