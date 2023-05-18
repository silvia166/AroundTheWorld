package com.example.aroundtheworld.controller_applicativo;


import com.example.aroundtheworld.bean.FamilyRequestBean;
import com.example.aroundtheworld.bean.ResidenceRequestBean;
import com.example.aroundtheworld.bean.RoomBean;
import com.example.aroundtheworld.dao.FamilyRequestDAO;
import com.example.aroundtheworld.dao.ResidenceRequestDAO;
import com.example.aroundtheworld.dao.RoomDAO;
import com.example.aroundtheworld.exception.NoAvailableRoomsException;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.model.FamilyRequest;
import com.example.aroundtheworld.model.ResidenceRequest;
import com.example.aroundtheworld.model.Room;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.List;

public class ReserveRoomController {
    public List<ResidenceRequestBean> getResidenceRequests() throws NotFoundException {
        List<ResidenceRequestBean> residenceRequestBeans = new ArrayList<>();
        List<ResidenceRequest> requests = ResidenceRequestDAO.retrieveRequests();

        for(ResidenceRequest request: requests){
            ResidenceRequestBean residenceRequestBean = new ResidenceRequestBean(request.getCity(), request.getArrival(), request.getDeparture(), request.getRoom(), request.getIdStudent(), request.getStatus());
            residenceRequestBean.setStudentName(request.getStudentName());
            residenceRequestBean.setIdResidence(request.getIdResidence());
            residenceRequestBean.setId(request.getId());
            residenceRequestBeans.add(residenceRequestBean);
        }
        return residenceRequestBeans;
    }

    public List<RoomBean> getAvailableRooms(ResidenceRequestBean residenceRequest) throws NoAvailableRoomsException {
        List<RoomBean> roomBeans = new ArrayList<>();
        List<Room> rooms = RoomDAO.retrieveAvailableRooms(residenceRequest.getIdResidence(),residenceRequest.getArrival(),residenceRequest.getDeparture());

        for(Room room: rooms){
            RoomBean roomBean = new RoomBean(room.getNumber(), room.getIdResidence() ,room.getType());
            roomBeans.add(roomBean);
        }
        return roomBeans;
    }

    public void reserveRoom(RoomBean selectedRoom, ResidenceRequestBean requestBean, int status, Pane pane) {
        requestBean.setStatus(status);
        requestBean.notifyObserversResidence(requestBean, pane);
        ResidenceRequestDAO.updateRoom(selectedRoom.getNumber(), requestBean.getId(), status);
    }

    public List<ResidenceRequestBean> getStudentResidenceRequest(int id) {
        List<ResidenceRequestBean> residenceRequestBeans = new ArrayList<>();
        List<ResidenceRequest> requests = ResidenceRequestDAO.retrieveStudentResidenceRequest(id);

        for(ResidenceRequest request: requests){
            ResidenceRequestBean residenceRequestBean = new ResidenceRequestBean(request.getCity(), request.getArrival(), request.getDeparture(), request.getRoom(), request.getIdStudent(), request.getStatus());
            residenceRequestBean.setId(request.getId());
            residenceRequestBeans.add(residenceRequestBean);
        }
        return residenceRequestBeans;
    }

    public void updateStatus(ResidenceRequestBean requestBean, int status, Pane pane) {
        requestBean.setStatus(status);
        requestBean.notifyObserversResidence(requestBean, pane);
        ResidenceRequestDAO.updateStatus(requestBean.getId(), status);
    }

    public void deleteResidenceRequest(ResidenceRequestBean requestBean, Pane pane) {
        requestBean.notifyObserversResidence(requestBean, pane);
        ResidenceRequestDAO.deleteRequest(requestBean.getId());
    }

}
