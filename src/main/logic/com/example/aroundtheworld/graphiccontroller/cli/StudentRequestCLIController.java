package com.example.aroundtheworld.graphiccontroller.cli;

import com.example.aroundtheworld.appcontroller.BookingFamilyController;
import com.example.aroundtheworld.appcontroller.ReserveRoomController;
import com.example.aroundtheworld.bean.FamilyRequestBean;
import com.example.aroundtheworld.bean.ResidenceRequestBean;
import com.example.aroundtheworld.bean.StudentBean;
import com.example.aroundtheworld.engineering.Session;
import com.example.aroundtheworld.exception.CommandErrorException;
import com.example.aroundtheworld.viewcli.StudentRequestViewCLI;

import java.util.List;

public class StudentRequestCLIController implements GraphicCLIController{
    private static final String RESIDENCE = "1";
    private static final String HOST_FAMILY = "2";
    private static final String ACCEPT = "1";
    private static final String REJECT = "2";
    private static final String BACK = "3";
    private StudentRequestViewCLI studentRequestViewCLI;
    private List<ResidenceRequestBean> residenceRequestList;
    private List<FamilyRequestBean> familyRequestList;
    private FamilyRequestBean familyRequestBean;
    private ResidenceRequestBean residenceRequestBean;

    @Override
    public void start() {
        StudentBean studentBean = Session.getCurrentSession().getStudentBean();
        this.studentRequestViewCLI = new StudentRequestViewCLI(this);
        int choice = studentRequestViewCLI.run();
        if (choice == 1) {
            ReserveRoomController reserveRoomController = new ReserveRoomController();
            this.residenceRequestList = reserveRoomController.getStudentResidenceRequests(studentBean.getId());
            displayResidenceRequests(residenceRequestList);
        } else {
            BookingFamilyController bookingFamilyController = new BookingFamilyController();
            this.familyRequestList = bookingFamilyController.getStudentFamilyRequest(studentBean.getId());
            displayFamilyRequests(familyRequestList);
        }
    }

    private void displayFamilyRequests(List<FamilyRequestBean> familyRequestList) {
        if(familyRequestList.isEmpty()) {
            studentRequestViewCLI.printNoRequest();
            StudentCLIController studentCLIController = new StudentCLIController();
            studentCLIController.start();
        }
        for(FamilyRequestBean request : familyRequestList){
            studentRequestViewCLI.showFamilyRequest(request.getId(),request.getStatus(),request.getArrivalBean(),request.getDepartureBean(),request.getFamilyName(),request.getCityBean());
        }
        studentRequestViewCLI.manageRequest(2);
    }

    private void displayResidenceRequests(List<ResidenceRequestBean> residenceRequestList) {
        if(residenceRequestList.isEmpty()) {
            studentRequestViewCLI.printNoRequest();
            StudentCLIController studentCLIController = new StudentCLIController();
            studentCLIController.start();
        }
        for(ResidenceRequestBean request : residenceRequestList){
            studentRequestViewCLI.showResidenceRequest(request.getId(),request.getStatus(),request.getArrival(),request.getDeparture(),request.getRoom(),request.getCity());
        }
        studentRequestViewCLI.manageRequest(1);
    }

    public int executeCommand(String inputLine) throws CommandErrorException {
        int choice;
        switch (inputLine){
            case RESIDENCE -> choice = 1;
            case HOST_FAMILY -> choice = 2;
            default -> throw new CommandErrorException();
        }
        return choice;
    }

    public void selectID(int id, int type) {
        if(id == 0){
            StudentCLIController studentCLIController = new StudentCLIController();
            studentCLIController.start();
        }
        if (type == 1) {
            manageResidenceRequest(id);
        } else {
            manageFamilyRequest(id);
        }
    }

    private void manageFamilyRequest(int id) {
        for(FamilyRequestBean request : familyRequestList) {
            if (request.getId() == id) {
                if (request.getStatus() == 0) {
                    studentRequestViewCLI.printErrorSelection("family");
                    displayFamilyRequests(familyRequestList);
                } else {
                    this.familyRequestBean = request;
                    studentRequestViewCLI.printActionFamily();
                }
            }
        }
    }

    private void manageResidenceRequest(int id) {
        for(ResidenceRequestBean request : residenceRequestList) {
            if (request.getId() == id) {
                if (request.getStatus() == 0) {
                    studentRequestViewCLI.printErrorSelection("agency");
                    displayResidenceRequests(residenceRequestList);
                } else {
                    this.residenceRequestBean = request;
                    studentRequestViewCLI.printActionResidence();
                }
            }
        }
    }

    public void executeAction(String inputLine, int type) throws CommandErrorException {
        switch (inputLine){
            case ACCEPT -> acceptRequest(type);
            case REJECT -> deleteRequest(type);
            case BACK -> {
                if(type == 1) {
                    this.residenceRequestBean = null;
                } else {
                    this.familyRequestBean = null;
                }
                studentRequestViewCLI.manageRequest(type);
            }
            default -> throw new CommandErrorException();
        }
    }

    public void acceptRequest(int type) {
        if(type == 1) {
            ReserveRoomController reserveRoomController = new ReserveRoomController();
            reserveRoomController.updateStatus(residenceRequestBean, 2, null);
            residenceRequestList.remove(residenceRequestBean);
            residenceRequestBean = null;
            displayResidenceRequests(residenceRequestList);
        } else {
            BookingFamilyController bookingFamilyController = new BookingFamilyController();
            bookingFamilyController.bookFamily(familyRequestBean, null);
            familyRequestList.remove(familyRequestBean);
            familyRequestBean = null;
            displayFamilyRequests(familyRequestList);
        }
    }

    public void deleteRequest(int type) {
        if(type == 1) {
            ReserveRoomController reserveRoomController = new ReserveRoomController();
            reserveRoomController.deleteResidenceRequest(residenceRequestBean, null);
            residenceRequestList.remove(residenceRequestBean);
            residenceRequestBean = null;
            displayResidenceRequests(residenceRequestList);
        } else {
            BookingFamilyController bookingFamilyController = new BookingFamilyController();
            bookingFamilyController.rejectRequest(familyRequestBean, null);
            familyRequestList.remove(familyRequestBean);
            familyRequestBean = null;
            displayFamilyRequests(familyRequestList);
        }
    }
}

