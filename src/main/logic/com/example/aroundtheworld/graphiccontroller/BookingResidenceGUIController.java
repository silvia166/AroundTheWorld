package com.example.aroundtheworld.graphiccontroller;

import com.example.aroundtheworld.bean.ResidenceRequestBean;
import com.example.aroundtheworld.appcontroller.BookingResidenceController;
import com.example.aroundtheworld.engineering.Printer;
import com.example.aroundtheworld.engineering.Session;
import com.example.aroundtheworld.engineering.ShowExceptionSupport;
import com.example.aroundtheworld.exception.DuplicateRequestException;
import com.example.aroundtheworld.exception.FormEmptyException;
import com.example.aroundtheworld.exception.MessageException;
import com.example.aroundtheworld.exception.NotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class BookingResidenceGUIController {
    Scene scene;
    Stage stage;
    @FXML
    ChoiceBox cityBox = new ChoiceBox();
    @FXML
    private DatePicker arrivalBox;
    @FXML
    private DatePicker departureBox;
    @FXML
    private RadioButton doubleR;
    @FXML
    private RadioButton singleR;

    @FXML
    ObservableList cityList = FXCollections.observableArrayList("London","Rome","Paris","New York","Valencia");

    @FXML
    public void initialize(){
        cityBox.setItems(cityList);
    }

    public void backButton(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    public void sendResidenceRequest(ActionEvent event) throws IOException {
        int idStudent = Session.getCurrentSession().getStudentBean().getId();
        String room;

        try {
            if (cityBox.getValue() == null)
                throw new FormEmptyException("City");
            if (arrivalBox.getValue() == null)
                throw new FormEmptyException("Arrival");
            if (departureBox.getValue() == null)
                throw new FormEmptyException("Departure");
            if (!singleR.isSelected() && !doubleR.isSelected())
                throw new FormEmptyException("House");

            if (singleR.isSelected())
                room = "single";
            else
                room = "double";

            ResidenceRequestBean residenceRequestBean = new ResidenceRequestBean(cityBox.getValue().toString(), arrivalBox.getValue().toString(), departureBox.getValue().toString(), room, idStudent,0);

            BookingResidenceController bookingResidenceController = new BookingResidenceController();
            bookingResidenceController.sendRequest(residenceRequestBean);

            ((Node)event.getSource()).getScene().getWindow().hide();

            ShowExceptionSupport.showException("Your request has been successfully saved!");

        } catch (FormEmptyException | MessageException | DuplicateRequestException e) {
            ShowExceptionSupport.showException(e.getMessage());
        } catch (NotFoundException e) {
            Printer.printError(e.getMessage());
        }
    }
}
