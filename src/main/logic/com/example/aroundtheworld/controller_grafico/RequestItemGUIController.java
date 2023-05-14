package com.example.aroundtheworld.controller_grafico;

import com.example.aroundtheworld.bean.FamilyRequestBean;
import com.example.aroundtheworld.bean.ResidenceRequestBean;
import com.example.aroundtheworld.bean.StudentBean;
import com.example.aroundtheworld.controller_applicativo.ContactFamilyController;
import com.example.aroundtheworld.controller_applicativo.ReserveRoomController;
import com.example.aroundtheworld.engineering.ShowExceptionSupport;
import com.example.aroundtheworld.engineering.observer.Observer;
import com.example.aroundtheworld.exception.NoAvailableRoomsException;
import com.example.aroundtheworld.exception.NotFoundException;
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
            reqPane.setStyle("-fx-background-color: white; -fx-border-radius: 20; -fx-background-radius: 20; -fx-effect: dropShadow(three-pass-box, rgb(81, 241, 155), 10.0 , 0.0 , 0.0 ,5.0);");
        }
    }

    public void acceptRequest() throws IOException {
        ContactFamilyController contactFamilyController = new ContactFamilyController();
        this.familyRequest.register(this);
        try {
            contactFamilyController.acceptRequest(this.familyRequest, this.pane);
        } catch (NotFoundException e) {
            ShowExceptionSupport.showException(e.getMessage());
        }
    }

    public void rejectRequest() throws IOException {
        ContactFamilyController contactFamilyController = new ContactFamilyController();
        this.familyRequest.register(this);
        try {
            contactFamilyController.rejectRequest(this.familyRequest, this.pane);
        } catch (NotFoundException e) {
            ShowExceptionSupport.showException(e.getMessage());
        }
    }

    public void viewRequest() throws IOException, NotFoundException {

        ContactFamilyController contactFamilyController = new ContactFamilyController();
        StudentBean studentBean = contactFamilyController.viewRequest(this.familyRequest);

        Stage stage =  new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("viewRequestFamily.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        ViewFamilyRequestGUIController viewFamilyRequestGUIController = fxmlLoader.getController();
        viewFamilyRequestGUIController.initialize(studentBean, this.familyRequest);

        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @Override
    public void update(Object object1, Object object2) {

        if (object1 instanceof FamilyRequestBean) {
            if (((FamilyRequestBean) object1).getStatus() == 1) {
                reqPane.getChildren().removeAll(acceptBtn, rejectBtn, viewReqBtn);
                notBookedLabel.setVisible(true);
                reqPane.setStyle("-fx-background-color: white; -fx-border-radius: 20; -fx-background-radius: 20; -fx-effect: dropShadow(three-pass-box, rgb(81, 241, 155), 10.0 , 0.0 , 0.0 ,5.0);");
            }

        } else if (object1 instanceof ResidenceRequestBean){

            if (((ResidenceRequestBean) object1).getStatus() == 1){
                reqPane.getChildren().remove(manageButton);
                reqPane.setStyle("-fx-background-color: white; -fx-border-radius: 20; -fx-background-radius: 20; -fx-effect: dropShadow(three-pass-box, rgb(252,126,126), 10.0 , 0.0 , 0.0 ,5.0);");
            } else if (((ResidenceRequestBean) object1).getStatus() == 2){
                reqPane.getChildren().remove(manageButton);
                reqPane.setStyle("-fx-background-color: white; -fx-border-radius: 20; -fx-background-radius: 20; -fx-effect: dropShadow(three-pass-box, rgb(81,241,155), 10.0 , 0.0 , 0.0 ,5.0);");
            }
        }
    }

    public void setResidenceRequest(ResidenceRequestBean requestBean) {

        this.residenceRequest = requestBean;

        nameLabel.setText(requestBean.getStudentName());
        cityLabel.setText(requestBean.getCity());
        arrivalLabel.setText(requestBean.getArrival());
        departureLabel.setText(requestBean.getDeparture());

        if (requestBean.getStatus() == 1){
            reqPane.getChildren().remove(manageButton);
            reqPane.setStyle("-fx-background-color: white; -fx-border-radius: 20; -fx-background-radius: 20; -fx-effect: dropShadow(three-pass-box, rgb(252,126,126), 10.0 , 0.0 , 0.0 ,5.0);");
        } else if (requestBean.getStatus() == 2){
            reqPane.getChildren().remove(manageButton);
            reqPane.setStyle("-fx-background-color: white; -fx-border-radius: 20; -fx-background-radius: 20; -fx-effect: dropShadow(three-pass-box, rgb(81, 241, 155), 10.0 , 0.0 , 0.0 ,5.0);");
        }else{
            reqPane.setStyle("-fx-background-color: white; -fx-border-radius: 20; -fx-background-radius: 20; -fx-effect: dropShadow(three-pass-box,rgba(0,0,0,0.1), 10.0 , 0.0 , 0.0 ,10.0);");
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

        reqPane.setStyle("-fx-background-color: white; -fx-border-radius: 20; -fx-background-radius: 20; -fx-effect: dropShadow(three-pass-box, rgb(192,249,221), 10.0 , 0.0 , 0.0 ,5.0);");

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

        reqPane.setStyle("-fx-background-color: white; -fx-border-radius: 20; -fx-background-radius: 20; -fx-effect: dropShadow(three-pass-box, rgb(192,249,221), 10.0 , 0.0 , 0.0 ,5.0);");
    }

}

