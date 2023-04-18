package com.example.aroundtheworld.controller_grafico;

import com.example.aroundtheworld.bean.CompatibleFamilyBean;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FamilyItemGraphicControllerJavaFX {

        @FXML
        private Label compatibility;
        @FXML
        private Label familyCity;
        @FXML
        private Label familyName;
        @FXML
        private ImageView img;

        public void setData(CompatibleFamilyBean compatibleFamilyBean, String city) {
                familyName.setText(compatibleFamilyBean.getName()+"  family");
                familyCity.setText(city);
                compatibility.setText(""+compatibleFamilyBean.getCompatibility()+"%");
                if(compatibleFamilyBean.getImgSrc() != null){
                        Image image = new Image(getClass().getResourceAsStream(compatibleFamilyBean.getImgSrc()));
                        img.setImage(image);
                }
        }

}


