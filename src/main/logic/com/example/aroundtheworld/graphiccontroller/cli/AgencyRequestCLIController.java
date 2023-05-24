package com.example.aroundtheworld.graphiccontroller.cli;

import com.example.aroundtheworld.appcontroller.BookingFamilyController;
import com.example.aroundtheworld.appcontroller.ReserveRoomController;
import com.example.aroundtheworld.bean.FamilyRequestBean;
import com.example.aroundtheworld.bean.ResidenceRequestBean;
import com.example.aroundtheworld.bean.RoomBean;
import com.example.aroundtheworld.bean.StudentBean;
import com.example.aroundtheworld.engineering.Session;
import com.example.aroundtheworld.engineering.ShowExceptionSupport;
import com.example.aroundtheworld.exception.CommandErrorException;
import com.example.aroundtheworld.exception.NoAvailableRoomsException;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.viewcli.AgencyRequestsViewCLI;
import com.example.aroundtheworld.viewcli.StudentRequestViewCLI;

import java.util.ArrayList;
import java.util.List;

public class AgencyRequestCLIController implements GraphicCLIController{

    private AgencyRequestsViewCLI agencyRequestsViewCLI;
    private static final String PENDING = "1";
    private static final String MODIFIED = "2";
    private static final String CONFIRMED = "3";
    private List<ResidenceRequestBean> residenceRequestList;
    private List<ResidenceRequestBean> pendingRequestsList = new ArrayList<>();
    private List<ResidenceRequestBean> modifiedRequestsList = new ArrayList<>();
    private List<ResidenceRequestBean> confirmedRequestsList = new ArrayList<>();
    private ResidenceRequestBean residenceRequestBean;

    public void start() {
        this.agencyRequestsViewCLI = new AgencyRequestsViewCLI(this);
        ReserveRoomController reserveRoomController = new ReserveRoomController();
        try {
            this.residenceRequestList = reserveRoomController.getResidenceRequests();
        } catch (NotFoundException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
        }

        for(ResidenceRequestBean residenceRequest : residenceRequestList){
            if(residenceRequest.getStatus() == 0){
                this.pendingRequestsList.add(residenceRequest);
            }else if(residenceRequest.getStatus() == 1){
                this.modifiedRequestsList.add(residenceRequest);
            }else if(residenceRequest.getStatus() == 2){
                this.confirmedRequestsList.add(residenceRequest);
            }
        }
        int choice = agencyRequestsViewCLI.run();

        if(choice == 1){
            displayPendingRequests(pendingRequestsList);
        }else if(choice == 2){
            displayModifiedRequests(modifiedRequestsList);
        }else {
            displayConfirmedRequests(confirmedRequestsList);
        }
    }

    private void displayPendingRequests(List<ResidenceRequestBean> pendingRequestsList) {
        if(pendingRequestsList.isEmpty()) {
            agencyRequestsViewCLI.printNoRequest();
            AgencyCLIController agencyCLIController = new AgencyCLIController();
            agencyCLIController.start();
        }
        for(ResidenceRequestBean request : pendingRequestsList){
            agencyRequestsViewCLI.showRequest(request.getId(), request.getStudentName(), request.getArrival(), request.getDeparture(), request.getCity());
        }
        agencyRequestsViewCLI.manageRequest();
    }

    private void displayConfirmedRequests(List<ResidenceRequestBean> confirmedRequestsList) {
        if(confirmedRequestsList.isEmpty()) {
            agencyRequestsViewCLI.printNoRequest();
            AgencyCLIController agencyCLIController = new AgencyCLIController();
            agencyCLIController.start();
        }
        for(ResidenceRequestBean request : confirmedRequestsList){
            agencyRequestsViewCLI.showRequest(request.getId(), request.getStudentName(), request.getArrival(), request.getDeparture(), request.getCity());
        }
        agencyRequestsViewCLI.printContinue();
        AgencyCLIController agencyCLIController = new AgencyCLIController();
        agencyCLIController.start();
    }

    private void displayModifiedRequests(List<ResidenceRequestBean> modifiedRequestsList) {
        if(modifiedRequestsList.isEmpty()) {
            agencyRequestsViewCLI.printNoRequest();
            AgencyCLIController agencyCLIController = new AgencyCLIController();
            agencyCLIController.start();
        }
        for(ResidenceRequestBean request : modifiedRequestsList){
            agencyRequestsViewCLI.showRequest(request.getId(), request.getStudentName(), request.getArrival(), request.getDeparture(), request.getCity());
        }
        agencyRequestsViewCLI.printContinue();
        AgencyCLIController agencyCLIController = new AgencyCLIController();
        agencyCLIController.start();
    }

    public int executeCommand(String nextLine) throws CommandErrorException {
        int choice;
        switch (nextLine){
            case PENDING -> choice = 1;
            case MODIFIED -> choice = 2;
            case CONFIRMED -> choice = 3;
            default -> throw new CommandErrorException();
        }
        return choice;
    }

    public void selectID(int id){
        if(id == 0){
            AgencyCLIController agencyCLIController = new AgencyCLIController();
            agencyCLIController.start();
        }else{
            manageRequest(id);
        }
    }

    private void manageRequest(int id){
        RoomBean selectedRoom = null;

        for(ResidenceRequestBean request : pendingRequestsList) {
            if (request.getId() == id) {
                this.residenceRequestBean = request;
            }
        }

        ReserveRoomController reserveRoomController = new ReserveRoomController();
        List<RoomBean> rooms = null;
        try {
            rooms = reserveRoomController.getAvailableRooms(residenceRequestBean);
        } catch (NoAvailableRoomsException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
        }
        agencyRequestsViewCLI.printRequestedRoom(residenceRequestBean.getRoom());
        for(RoomBean roomBean : rooms){
            agencyRequestsViewCLI.displayRoom(roomBean.getNumber(), roomBean.getType());
        }

        int choice = agencyRequestsViewCLI.selectRoom();
        for(RoomBean roomBean : rooms) {
            if (roomBean.getNumber() == choice) {
                selectedRoom = roomBean;
            }
        }

        if(residenceRequestBean.getRoom().equals(selectedRoom.getType())){
            reserveRoomController.reserveRoom(selectedRoom, residenceRequestBean, 2, null);
        }else{
            reserveRoomController.reserveRoom(selectedRoom, residenceRequestBean, 1, null);
        }

        agencyRequestsViewCLI.printContinue();
        AgencyCLIController agencyCLIController = new AgencyCLIController();
        agencyCLIController.start();
    }
}
