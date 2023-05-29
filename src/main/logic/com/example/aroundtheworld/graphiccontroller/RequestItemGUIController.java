package com.example.aroundtheworld.graphiccontroller;

import com.example.aroundtheworld.bean.FamilyRequestBean;
import com.example.aroundtheworld.bean.ResidenceRequestBean;
import com.example.aroundtheworld.bean.StudentBean;
import com.example.aroundtheworld.appcontroller.ContactFamilyController;
import com.example.aroundtheworld.appcontroller.ReserveRoomController;
import com.example.aroundtheworld.engineering.ShowExceptionSupport;
import com.example.aroundtheworld.engineering.observer.Observer;
import com.example.aroundtheworld.exception.NoAvailableRoomsException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class RequestItemGUIController implements Observer {
    @FXML
    private Button acceptBtn;
    @FXML
    private Label arrivalLabel;
    @FXML
    private Label compatibilityLabel;
    @FXML
    private Label departureLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Button rejectBtn;
    @FXML
    private Label notBookedLabel;
    @FXML
    private Button viewReqBtn;
    @FXML
    private Button manageButton;
    @FXML
    private Label modifiedLabel;
    @FXML
    private AnchorPane reqPane;

    private FamilyRequestBean familyRequest;
    private Pane pane;
    private ResidenceRequestBean residenceRequest;

    @FXML
    private Label residenceLabel;

    @FXML
    private Label roomLabel;

    @FXML
    private Label statusLabel;

    private static final String GREENSHADOW = "-fx-background-color: white; -fx-border-radius: 20; -fx-background-radius: 20; -fx-effect: dropShadow(three-pass-box, rgb(81, 241, 155), 10.0 , 0.0 , 0.0 ,5.0);";
    private static final String SHADOW = "-fx-background-color: white; -fx-border-radius: 20; -fx-background-radius: 20; -fx-effect: dropShadow(three-pass-box,rgba(0,0,0,0.1), 10.0 , 0.0 , 0.0 ,10.0);";
    private static final String REDSHADOW = "-fx-background-color: white; -fx-border-radius: 20; -fx-background-radius: 20; -fx-effect: dropShadow(three-pass-box, rgb(252,126,126), 10.0 , 0.0 , 0.0 ,5.0);";
    private static final String LIGHTGREEN = "-fx-background-color: white; -fx-border-radius: 20; -fx-background-radius: 20; -fx-effect: dropShadow(three-pass-box, rgb(192,249,221), 10.0 , 0.0 , 0.0 ,5.0);";


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
        StudentBean studentBean = contactFamilyController.getStudent(this.familyRequest);

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

    public void setResidenceRequest(ResidenceRequestBean requestBean) {

        this.residenceRequest = requestBean;

        nameLabel.setText(requestBean.getStudentName());
        cityLabel.setText(requestBean.getCity());
        arrivalLabel.setText(requestBean.getArrival());
        departureLabel.setText(requestBean.getDeparture());

        if (requestBean.getStatus() == 1){
            reqPane.getChildren().remove(manageButton);
            reqPane.setStyle(REDSHADOW);
        } else if (requestBean.getStatus() == 2){
            reqPane.getChildren().remove(manageButton);
            reqPane.setStyle(GREENSHADOW);
        }else{
            reqPane.setStyle(SHADOW);
        }
    }

    public void manageRequest() throws IOException {

        this.residenceRequest.register(this);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("reserveRoom.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            ReserveRoomGUIController reserveRoomGUIController = fxmlLoader.getController();
            reserveRoomGUIController.reserveRoom(this.residenceRequest, this.pane);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
            stage.show();
        }catch(NoAvailableRoomsException e){
            ReserveRoomController reserveRoomController = new ReserveRoomController();
            reserveRoomController.deleteResidenceRequest(this.residenceRequest, this.pane);
            ShowExceptionSupport.showException(e.getMessage());
        }
    }

    public void setModifiedRequest(ResidenceRequestBean requestBean) {
        this.residenceRequest = requestBean;

        String room;
        if(requestBean.getRoom().equals("single")){
            room = "Double";
        }else {
            room = "Single";
        }

        residenceLabel.setText(requestBean.getCity()+" Residence");
        statusLabel.setText("Modified Request");
        roomLabel.setText(room + " Room");
        arrivalLabel.setText(requestBean.getArrival());
        departureLabel.setText(requestBean.getDeparture());

        reqPane.setStyle(LIGHTGREEN);
    }

    public void acceptModifiedRequest() {
        ReserveRoomController reserveRoomController = new ReserveRoomController();
        reserveRoomController.updateStatus(this.residenceRequest, 2, this.pane);
    }

    public void rejectModifiedRequest(){
        ReserveRoomController reserveRoomController = new ReserveRoomController();
        reserveRoomController.deleteResidenceRequest(this.residenceRequest, this.pane);
    }

    public void setPendingRequest(ResidenceRequestBean requestBean) {
        this.residenceRequest = requestBean;

        String room;
        if(requestBean.getRoom().equals("single")){
            room = "Single";
        }else{
            room = "Double";
        }
        statusLabel.setText("Pending Request");
        residenceLabel.setText(requestBean.getCity()+" Residence");
        roomLabel.setText(room + " Room");
        arrivalLabel.setText(requestBean.getArrival());
        departureLabel.setText(requestBean.getDeparture());
        modifiedLabel.setText("Requested:");
        reqPane.getChildren().removeAll(rejectBtn, acceptBtn);

        reqPane.setStyle(LIGHTGREEN);
    }

    @Override
    public void updateResidence(ResidenceRequestBean requestBean, Pane pane) {
        if (requestBean.getStatus() == 1){
            reqPane.getChildren().remove(manageButton);
            reqPane.setStyle(REDSHADOW);
        } else if (requestBean.getStatus() == 2){
            reqPane.getChildren().remove(manageButton);
            reqPane.setStyle(GREENSHADOW);
        }
    }

    @Override
    public void updateFamily(FamilyRequestBean requestBean, Pane pane) {
        if (requestBean.getStatus() == 1) {
            reqPane.getChildren().removeAll(acceptBtn, rejectBtn, viewReqBtn);
            notBookedLabel.setVisible(true);
            reqPane.setStyle(GREENSHADOW);
        }
    }
}

