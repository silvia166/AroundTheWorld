package com.example.aroundtheworld.graphiccontroller;

import com.example.aroundtheworld.bean.ResidenceRequestBean;
import com.example.aroundtheworld.bean.RoomBean;
import com.example.aroundtheworld.appcontroller.ReserveRoomController;
import com.example.aroundtheworld.exception.NoAvailableRoomsException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;


public class ReserveRoomGUIController {
    @FXML
    private Text arrivalLabel;
    @FXML
    private Text departureLabel;
    @FXML
    private Text resNameLabel;
    @FXML
    private Button roomBtn;
    @FXML
    private TableColumn<RoomBean, Integer> roomColumn;
    @FXML
    private Text roomLabel;
    @FXML
    private TableView<RoomBean> roomsTableView;
    @FXML
    private Text studNameLabel;
    @FXML
    private TableColumn<RoomBean, String> typeColumn;

    private ResidenceRequestBean requestBean;
    private Pane pane;

    public void reserveRoom(ResidenceRequestBean residenceRequest, Pane pane) throws NoAvailableRoomsException {

        this.requestBean = residenceRequest;
        this.pane = pane;

        studNameLabel.setText(residenceRequest.getStudentName());
        arrivalLabel.setText(residenceRequest.getArrival());
        departureLabel.setText(residenceRequest.getDeparture());
        roomLabel.setText(residenceRequest.getRoom());

        String residenceName = residenceRequest.getCity();
        residenceName = residenceName.concat(" Residence");
        resNameLabel.setText(residenceName);

        ReserveRoomController reserveRoomController = new ReserveRoomController();
        List<RoomBean> rooms = reserveRoomController.getAvailableRooms(residenceRequest);

        displayRooms(rooms);

    }

    private void displayRooms(List<RoomBean> roomBeans) {
        roomColumn.setCellValueFactory(new PropertyValueFactory<>("Number"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));

        Iterator<RoomBean> iterator = roomBeans.iterator();
        ObservableList<RoomBean> rooms = roomsTableView.getItems();

        while(iterator.hasNext()){
            RoomBean room = iterator.next();
            rooms.add(room);
        }
        roomsTableView.setItems(rooms);
    }

    @FXML
    void getSelectedRoom(MouseEvent event) {
        int index = roomsTableView.getSelectionModel().getSelectedIndex();
        String typeSelected = typeColumn.getCellData(index);
        if(requestBean.getRoom().equals(typeSelected)){
            roomBtn.setText("Reserve room");
        } else {
            roomBtn.setText("Modify request");
        }
    }

    public void selectRoom(ActionEvent event){
        ReserveRoomController reserveRoomController = new ReserveRoomController();
        RoomBean selectedRoom = roomsTableView.getSelectionModel().getSelectedItem();
        if(roomBtn.getText().equals("Reserve room")){
            reserveRoomController.reserveRoom(selectedRoom, requestBean, 2, this.pane);
        } else {
            reserveRoomController.reserveRoom(selectedRoom, requestBean, 1, this.pane);
        }

        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    public void backButton(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
}
