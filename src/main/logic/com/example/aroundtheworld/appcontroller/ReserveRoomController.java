package com.example.aroundtheworld.appcontroller;


import com.example.aroundtheworld.bean.ResidenceRequestBean;
import com.example.aroundtheworld.bean.RoomBean;
import com.example.aroundtheworld.bean.StudentBean;
import com.example.aroundtheworld.dao.ResidenceRequestDAO;
import com.example.aroundtheworld.dao.RoomDAO;
import com.example.aroundtheworld.dao.StudentDAO;
import com.example.aroundtheworld.engineering.factory.StudentDAOFactory;
import com.example.aroundtheworld.exception.NoAvailableRoomsException;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.model.ResidenceRequest;
import com.example.aroundtheworld.model.Room;
import com.example.aroundtheworld.model.Student;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.List;

public class ReserveRoomController {
    public List<ResidenceRequestBean> getResidenceRequests() throws NotFoundException {
        List<ResidenceRequestBean> residenceRequestBeans = new ArrayList<>();
        List<ResidenceRequest> requests = ResidenceRequestDAO.retrieveRequests();

        for(ResidenceRequest request: requests){
            StudentDAO studentDAO = StudentDAOFactory.getInstance().createStudentDAO();
            Student student = studentDAO.getNameById(request.getIdStudent());
            String studentName = student.getName();
            studentName = studentName.concat(" ");
            studentName = studentName.concat(student.getSurname());
            ResidenceRequestBean residenceRequestBean = new ResidenceRequestBean(request.getCity(), request.getArrival(), request.getDeparture(), request.getRequestedRoom(), request.getIdStudent(), request.getStatus());
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
            RoomBean roomBean = new RoomBean(room.getNumber() ,room.getType());
            roomBeans.add(roomBean);
        }
        return roomBeans;
    }

    public void finalizeRequest(RoomBean selectedRoom, ResidenceRequestBean requestBean, Pane pane) {
        if(pane != null){
            requestBean.notifyObserversResidence(requestBean, pane);
            }
        ResidenceRequestDAO.updateRoom(selectedRoom.getNumber(), requestBean.getId(), requestBean.getStatus());
    }

    public List<ResidenceRequestBean> getStudentResidenceRequests(StudentBean studentBean) {
        List<ResidenceRequestBean> residenceRequestBeans = new ArrayList<>();
        List<ResidenceRequest> requests = ResidenceRequestDAO.retrieveStudentResidenceRequest(studentBean.getId());

        for(ResidenceRequest request: requests){
            ResidenceRequestBean residenceRequestBean = new ResidenceRequestBean(request.getCity(), request.getArrival(), request.getDeparture(), request.getRequestedRoom(), request.getIdStudent(), request.getStatus());
            residenceRequestBean.setId(request.getId());
            residenceRequestBeans.add(residenceRequestBean);
        }
        return residenceRequestBeans;
    }

    public void updateStatus(ResidenceRequestBean requestBean,  Pane pane) {
        if(pane != null){
            requestBean.notifyObserversResidence(requestBean, pane);
        }
        ResidenceRequestDAO.updateStatus(requestBean.getId(), requestBean.getStatus());
    }

    public void deleteResidenceRequest(ResidenceRequestBean requestBean, Pane pane) {
        if(pane != null){
            requestBean.notifyObserversResidence(requestBean, pane);
        }
        ResidenceRequestDAO.deleteRequest(requestBean.getId());
    }

}
