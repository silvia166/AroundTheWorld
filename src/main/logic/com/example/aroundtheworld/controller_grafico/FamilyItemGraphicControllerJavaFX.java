package com.example.aroundtheworld.controller_grafico;

import com.example.aroundtheworld.model.Family;
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

        private Family family;
        public void setData(Family family) {
                this.family = family;
                familyName.setText(family.getName()+"  family");
                familyCity.setText(family.getCity());
                Image image = new Image(getClass().getResourceAsStream(family.getImgSrc()));
                img.setImage(image);

        }
}


