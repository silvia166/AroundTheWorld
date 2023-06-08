package com.example.aroundtheworld.graphiccontroller;

import com.example.aroundtheworld.appcontroller.ContactFamilyController;
import com.example.aroundtheworld.bean.FamilyRequestBean;
import com.example.aroundtheworld.bean.ResidenceRequestBean;
import com.example.aroundtheworld.bean.StudentBean;
import com.example.aroundtheworld.engineering.observer.Observer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class FamilyRequestItemGUIController implements Observer {

    private FamilyRequestBean familyRequest;
    private Pane pane;
    @FXML
    private Label arrivalLabel;
    @FXML
    private Label compatibilityLabel;
    @FXML
    private Label departureLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private AnchorPane reqPane;
    private static final String GREENSHADOW = "-fx-background-color: white; -fx-border-radius: 20; -fx-background-radius: 20; -fx-effect: dropShadow(three-pass-box, rgb(81, 241, 155), 10.0 , 0.0 , 0.0 ,5.0);";
    @FXML
    private Label notBookedLabel;
    @FXML
    private Button acceptBtn;
    @FXML
    private Button viewReqBtn;
    @FXML
    private Button rejectBtn;

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public void setFamilyRequest(FamilyRequestBean familyRequestBean) {
        this.familyRequest = familyRequestBean;

        nameLabel.setText(familyRequestBean.getStudentName());
        compatibilityLabel.setText(familyRequestBean.getCompatibilityBean() +"%");
        arrivalLabel.setText(familyRequest.getArrivalBean());
        departureLabel.setText(familyRequest.getDepartureBean());

        if (familyRequestBean.getStatus() == 1){
            reqPane.getChildren().removeAll(acceptBtn,rejectBtn,viewReqBtn);
            notBookedLabel.setVisible(true);
            reqPane.setStyle(GREENSHADOW);
        }
    }

    public void acceptRequest() {
        ContactFamilyController contactFamilyController = new ContactFamilyController();
        this.familyRequest.register(this);
        contactFamilyController.acceptRequest(this.familyRequest, this.pane);
    }

    public void rejectRequest() {
        ContactFamilyController contactFamilyController = new ContactFamilyController();
        this.familyRequest.register(this);
        contactFamilyController.rejectRequest(this.familyRequest, this.pane);
    }

    public void viewRequest() throws IOException {

        ContactFamilyController contactFamilyController = new ContactFamilyController();
        StudentBean studentBean = contactFamilyController.getStudent(this.familyRequest.getIdStudentBean());

        Stage stage =  new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("viewRequestFamily.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        ViewRequestGUIController viewRequestGUIController = fxmlLoader.getController();
        viewRequestGUIController.displayRequest(studentBean, this.familyRequest);

        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @Override
    public void update() {
        if (this.familyRequest.getStatus() == 1) {
            reqPane.getChildren().removeAll(acceptBtn, rejectBtn, viewReqBtn);
            notBookedLabel.setVisible(true);
            reqPane.setStyle(GREENSHADOW);
        }
    }

    @Override
    public void updateResidencePage(ResidenceRequestBean requestBean, Pane pane){
        //ignore
    }

    @Override
    public void updateFamilyPage(FamilyRequestBean requestBean, Pane pane) {
        //ignore
    }
}
