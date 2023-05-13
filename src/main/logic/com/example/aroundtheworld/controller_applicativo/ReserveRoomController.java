package com.example.aroundtheworld.controller_applicativo;


import com.example.aroundtheworld.bean.ResidenceRequestBean;
import com.example.aroundtheworld.bean.RoomBean;
import com.example.aroundtheworld.dao.ResidenceRequestDAO;
import com.example.aroundtheworld.dao.RoomDAO;
import com.example.aroundtheworld.dao.StudentDAO;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.model.ResidenceRequest;
import com.example.aroundtheworld.model.Room;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReserveRoomController {
    public List<ResidenceRequestBean> getResidenceRequests() throws NotFoundException {
        List<ResidenceRequestBean> residenceRequestBeans = new ArrayList<>();
        List<ResidenceRequest> requests = ResidenceRequestDAO.retrieveResidenceRequests();
        String studentName;

        for(ResidenceRequest request: requests){
            studentName = StudentDAO.getNameById(request.getIdStudent());
            ResidenceRequestBean residenceRequestBean = new ResidenceRequestBean(request.getCity(), request.getArrival(), request.getDeparture(), request.getRoom(), request.getIdStudent(), request.getStatus());
            residenceRequestBean.setStudentName(studentName);
            residenceRequestBean.setIdResidence(request.getIdResidence());
            residenceRequestBean.setId(request.getId());
            residenceRequestBeans.add(residenceRequestBean);
        }
        return residenceRequestBeans;
    }

    public List<RoomBean> getAvailableRooms(ResidenceRequestBean residenceRequest) throws IOException {
        List<RoomBean> roomBeans = new ArrayList<>();
        List<Room> rooms = RoomDAO.retrieveAvailableRooms(residenceRequest.getIdResidence(),residenceRequest.getArrival(),residenceRequest.getDeparture());

        for(Room room: rooms){
            RoomBean roomBean = new RoomBean(room.getNumber(), room.getIdResidence() ,room.getType());
            roomBeans.add(roomBean);
        }
        return roomBeans;
    }

    public void reserveRoom(RoomBean selectedRoom, ResidenceRequestBean requestBean, int status, Object object) {
        requestBean.setStatus(status);
        requestBean.notifyObservers(requestBean, object);
        ResidenceRequestDAO.updateRoom(selectedRoom.getNumber(), requestBean.getId(), status);
    }

    public List<ResidenceRequestBean> getModifiedRequest(int id) throws IOException {
        List<ResidenceRequestBean> residenceRequestBeans = new ArrayList<>();
        List<ResidenceRequest> requests = ResidenceRequestDAO.retrieveModifiedRequests(id);

        for(ResidenceRequest request: requests){
            ResidenceRequestBean residenceRequestBean = new ResidenceRequestBean(request.getCity(), request.getArrival(), request.getDeparture(), request.getRoom(), request.getIdStudent(), request.getStatus());
            residenceRequestBean.setId(request.getId());
            residenceRequestBeans.add(residenceRequestBean);
        }
        return residenceRequestBeans;
    }

    public void updateStatus(ResidenceRequestBean requestBean, int status, Object object) {
        requestBean.setStatus(status);
        requestBean.notifyObservers(requestBean, object);
        ResidenceRequestDAO.updateRequest(requestBean.getId(), status);
    }

    public void deleteResidenceRequest(ResidenceRequestBean requestBean, Object object) {
        requestBean.notifyObservers(requestBean, object);
        ResidenceRequestDAO.deleteResidenceRequest(requestBean.getId());
    }
}
