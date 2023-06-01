package com.example.aroundtheworld.appcontroller;


import com.example.aroundtheworld.bean.ResidenceRequestBean;
import com.example.aroundtheworld.bean.RoomBean;
import com.example.aroundtheworld.dao.ResidenceRequestDAO;
import com.example.aroundtheworld.dao.RoomDAO;
import com.example.aroundtheworld.dao.StudentDAO;
import com.example.aroundtheworld.engineering.factory.StudentDAOFactory;
import com.example.aroundtheworld.exception.NoAvailableRoomsException;
import com.example.aroundtheworld.exception.NotFoundException;
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
            StudentDAO studentDAO = StudentDAOFactory.getInstance().createStudentDAO();
            String studentName = studentDAO.getNameById(request.getIdStudent());
            ResidenceRequestBean residenceRequestBean = new ResidenceRequestBean(request.getCity(), request.getArrival(), request.getDeparture(), request.getRoom(), request.getIdStudent(), request.getStatus());
            residenceRequestBean.setStudentName(studentName);
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

    public void finalizeRequest(RoomBean selectedRoom, ResidenceRequestBean requestBean, int status, Pane pane) {
        requestBean.setStatus(status);
        if(pane != null){
            requestBean.notifyObserversResidence(requestBean, pane);
            }
        ResidenceRequestDAO.updateRoom(selectedRoom.getNumber(), requestBean.getId(), status);
    }

    public List<ResidenceRequestBean> getStudentResidenceRequests(int id) {
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
        if(pane != null){
            requestBean.notifyObserversResidence(requestBean, pane);
        }
        ResidenceRequestDAO.updateStatus(requestBean.getId(), status);
    }

    public void deleteResidenceRequest(ResidenceRequestBean requestBean, Pane pane) {
        if(pane != null){
            requestBean.notifyObserversResidence(requestBean, pane);
        }
        ResidenceRequestDAO.deleteRequest(requestBean.getId());
    }

}
