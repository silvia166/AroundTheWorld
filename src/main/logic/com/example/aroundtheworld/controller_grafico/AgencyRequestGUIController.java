package com.example.aroundtheworld.controller_grafico;

import com.example.aroundtheworld.bean.FamilyBean;
import com.example.aroundtheworld.bean.FamilyRequestBean;
import com.example.aroundtheworld.bean.ResidenceRequestBean;
import com.example.aroundtheworld.controller_applicativo.ContactFamilyController;
import com.example.aroundtheworld.controller_applicativo.ReserveRoomController;
import com.example.aroundtheworld.engineering.Session;
import com.example.aroundtheworld.engineering.observer.Observer;
import com.example.aroundtheworld.exception.NotFoundException;
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

public class AgencyRequestGUIController {

    @FXML
    private HBox confirmedReqList;
    @FXML
    private HBox modifiedReqList;
    @FXML
    private HBox pendingReqList;

    public void backToAccess() throws IOException {
        Session.closeSession();
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("accessPage.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toHomepageAgency() throws IOException {
        AgencyGUIController agencyGUIController = new AgencyGUIController();
        agencyGUIController.toHomepageAgency();
    }

    public void initialize() throws NotFoundException, IOException {

        ReserveRoomController reserveRoomController = new ReserveRoomController();
        List<ResidenceRequestBean> requestList = reserveRoomController.getResidenceRequests();

        for(ResidenceRequestBean requestBean: requestList){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("requestAgencyItem.fxml"));
            Pane requestBox = fxmlLoader.load();

            RequestItemGUIController requestItemGUIController = fxmlLoader.getController();
            requestItemGUIController.setPane(requestBox);
            requestItemGUIController.setResidenceRequest(requestBean);

            if (requestBean.getStatus() == 0){
                pendingReqList.getChildren().add(requestBox);
            } else if (requestBean.getStatus() == 1){
                modifiedReqList.getChildren().add(requestBox);
            } else if(requestBean.getStatus() == 1){
                confirmedReqList.getChildren().add(requestBox);
            }
        }
    }

}
