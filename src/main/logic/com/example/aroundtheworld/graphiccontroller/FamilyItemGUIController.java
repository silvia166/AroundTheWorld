package com.example.aroundtheworld.graphiccontroller;

import com.example.aroundtheworld.bean.CompatibleFamilyBean;
import com.example.aroundtheworld.bean.FamilyBean;
import com.example.aroundtheworld.bean.FamilyRequestBean;
import com.example.aroundtheworld.appcontroller.BookingFamilyController;
import com.example.aroundtheworld.appcontroller.ContactFamilyController;
import com.example.aroundtheworld.engineering.ShowExceptionSupport;
import com.example.aroundtheworld.exception.DuplicateRequestException;
import com.example.aroundtheworld.exception.NotFoundException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class FamilyItemGUIController {
        @FXML
        private Label compatibility;
        @FXML
        private Label familyCity;
        @FXML
        private Label familyName;
        @FXML
        private Label arrivalLabel;
        @FXML
        private ImageView img;
        @FXML
        private Button bookBtn;
        @FXML
        private Button rejectBtn;
        @FXML
        private Button sendRequestBtn;
        @FXML
        private Label statusLabel;
        @FXML
        private Button viewProfileBtn;
        @FXML
        private AnchorPane reqPane;
        private CompatibleFamilyBean family;
        private FamilyRequestBean familyRequest;
        private Pane pane;
        private static final String LIGHTGREEN = "-fx-background-color: white; -fx-border-radius: 20; -fx-background-radius: 20; -fx-effect: dropShadow(three-pass-box, rgb(192,249,221), 10.0 , 0.0 , 0.0 ,5.0);";

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
                FamilyBean familyBean = contactFamilyController.getFamilyProfile(family.getId());

                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.UNDECORATED);
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("selectedFamilyProfile.fxml"));

                Scene scene = new Scene(fxmlLoader.load());

                FamilyProfileGUIController familyProfileGUIController = fxmlLoader.getController();
                familyProfileGUIController.displaySelectedProfile(familyBean, this.family.getCompatibility());

                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();
        }

        public void setData(CompatibleFamilyBean compatibleFamilyBean, FamilyRequestBean familyRequestBean) {

                this.family = compatibleFamilyBean;
                this.familyRequest = familyRequestBean;
                familyName.setText(compatibleFamilyBean.getName() + "  family");
                familyCity.setText(familyRequestBean.getCityBean());
                compatibility.setText("" + compatibleFamilyBean.getCompatibility() + "%");
                if (compatibleFamilyBean.getImgSrc() != null) {
                        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(compatibleFamilyBean.getImgSrc())));
                        img.setImage(image);
                }
        }

        public void setPane(Pane pane) {
                this.pane = pane;
        }

        public void setFamilyRequest(FamilyRequestBean requestBean) {
                this.familyRequest = requestBean;

                reqPane.setStyle(LIGHTGREEN);
                reqPane.getChildren().removeAll(viewProfileBtn, sendRequestBtn);
                familyName.setText(requestBean.getFamilyName());

                if(requestBean.getImgFamily() != null){
                        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(requestBean.getImgFamily())));
                        img.setImage(image);
                }
                familyCity.setText(requestBean.getCityBean());
                arrivalLabel.setText(requestBean.getArrivalBean());
                compatibility.setText(requestBean.getDepartureBean());
                if(requestBean.getStatus() == 0){
                        statusLabel.setText("Pending Request");
                }else{
                        statusLabel.setText("Accepted Request");
                        bookBtn.setVisible(true);
                        rejectBtn.setVisible(true);
                }
        }

        public void rejectRequest() {
                BookingFamilyController bookingFamilyController = new BookingFamilyController();
                bookingFamilyController.rejectRequest(this.familyRequest, this.pane);
        }

        public void bookFamily(){
                BookingFamilyController bookingFamilyController = new BookingFamilyController();
                bookingFamilyController.bookFamily(this.familyRequest, this.pane);
        }

}


