package com.example.aroundtheworld.controller_grafico;

import com.example.aroundtheworld.bean.CompatibleFamilyBean;
import com.example.aroundtheworld.bean.FamilyRequestBean;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class RequestItemGUIController {
    @FXML
    private Button acceptBtn;
    @FXML
    private Label arrivalLabel;
    @FXML
    private Label compatibilityLabel;
    @FXML
    private Label departureLabel;
    @FXML
    private HBox manageReqBox;
    @FXML
    private Label nameLabel;
    @FXML
    private Button rejectBtn;
    @FXML
    private Label notBookedLabel;
    @FXML
    private VBox vBox;
    @FXML
    private Button viewReqBtn;

   @FXML
   private AnchorPane reqPane;

    private FamilyRequestBean familyRequest;
    private Pane pane;

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
        }
    }
}
