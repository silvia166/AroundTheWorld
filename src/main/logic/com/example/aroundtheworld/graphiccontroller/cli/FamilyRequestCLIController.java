package com.example.aroundtheworld.graphiccontroller.cli;

import com.example.aroundtheworld.appcontroller.ContactFamilyController;
import com.example.aroundtheworld.bean.*;
import com.example.aroundtheworld.engineering.Session;
import com.example.aroundtheworld.exception.CommandErrorException;
import com.example.aroundtheworld.viewcli.FamilyRequestViewCLI;

import java.util.ArrayList;
import java.util.List;

public class FamilyRequestCLIController implements GraphicCLIController{
    private FamilyRequestViewCLI familyRequestViewCLI;
    private static final String PENDING = "1";
    private static final String CONFIRMED = "2";
    private static final String ACCEPT = "1";
    private static final String REJECT = "2";
    private static final String VIEW = "3";
    private List<FamilyRequestBean> pendingRequest = new ArrayList<>();
    private List<FamilyRequestBean> confirmedRequest = new ArrayList<>();
    private FamilyRequestBean familyRequestBean;

    @Override
    public void start() {
        this.familyRequestViewCLI = new FamilyRequestViewCLI(this);
        FamilyBean familyBean = Session.getCurrentSession().getFamilyBean();
        ContactFamilyController contactFamilyController = new ContactFamilyController();
        List<FamilyRequestBean> requests = contactFamilyController.getFamilyRequests(familyBean);
        for(FamilyRequestBean request : requests){
            if(request.getStatus() == 0){
                pendingRequest.add(request);
            } else {
                confirmedRequest.add(request);
            }
        }
        familyRequestViewCLI.run();
    }

    public void executeCommand(String nextLine) throws CommandErrorException {
        switch (nextLine){
            case PENDING -> displayPendingRequests();
            case CONFIRMED -> displayConfirmedRequests();
            default -> throw new CommandErrorException();
        }
    }

    public void displayConfirmedRequests() {
        if (confirmedRequest.isEmpty()){
            familyRequestViewCLI.printNoRequests();
        }
        for (FamilyRequestBean requestBean : confirmedRequest){
            familyRequestViewCLI.displayRequest(requestBean.getId(),requestBean.getStudentName(),requestBean.getCompatibilityBean(),requestBean.getArrivalBean(),requestBean.getDepartureBean());
        }
        familyRequestViewCLI.printContinue();
        FamilyCLIController familyCLIController = new FamilyCLIController();
        familyCLIController.start();
    }

    public void displayPendingRequests() {
        if (pendingRequest.isEmpty()){
            familyRequestViewCLI.printNoRequests();
        }
        for (FamilyRequestBean requestBean : pendingRequest){
            familyRequestViewCLI.displayRequest(requestBean.getId(),requestBean.getStudentName(),requestBean.getCompatibilityBean(),requestBean.getArrivalBean(),requestBean.getDepartureBean());
        }
        familyRequestViewCLI.printAction();
    }

    public void selectID(int id) {
        if(id == 0){
            FamilyCLIController familyCLIController = new FamilyCLIController();
            familyCLIController.start();
        }else{
            manageRequest(id);
        }
    }

    private void manageRequest(int id) {
        for(FamilyRequestBean request : pendingRequest) {
            if (request.getId() == id) {
                this.familyRequestBean = request;
                familyRequestViewCLI.printOption();
            }

        }
    }


    public void executeOption(String choice) throws CommandErrorException {
        switch (choice){
            case ACCEPT -> acceptRequest();
            case REJECT -> rejectRequest();
            case VIEW -> viewRequest();
            default -> throw new CommandErrorException();
        }
    }

    private void viewRequest() {
        ContactFamilyController contactFamilyController = new ContactFamilyController();
        StudentBean studentBean = contactFamilyController.getStudent(familyRequestBean.getIdStudentBean());
        familyRequestViewCLI.printStudentInfo(studentBean.getName(), studentBean.getSurname(), studentBean.getBirth(),studentBean.getNationality(), studentBean.getEmail(), studentBean.getPhoneNumber());
        familyRequestViewCLI.printRequestInfo(familyRequestBean.getCityBean(),familyRequestBean.getArrivalBean(), familyRequestBean.getDepartureBean(),familyRequestBean.getHouse(),familyRequestBean.getSiblingsBean(),familyRequestBean.getAnimalsBean());
        familyRequestViewCLI.printRequestFood(familyRequestBean.getVegan(),familyRequestBean.getVegetarian());
        familyRequestViewCLI.printRequestHobbies(familyRequestBean.getTravels(),familyRequestBean.getSport(),familyRequestBean.getNature(),familyRequestBean.getBooks(),familyRequestBean.getVideoGames(),familyRequestBean.getCooking(),familyRequestBean.getFilm());
    }

    private void rejectRequest() {
        ContactFamilyController contactFamilyController = new ContactFamilyController();
        contactFamilyController.rejectRequest(familyRequestBean,null);
        pendingRequest.remove(familyRequestBean);
        familyRequestBean = null;
        displayPendingRequests();
    }

    private void acceptRequest() {
        ContactFamilyController contactFamilyController = new ContactFamilyController();
        contactFamilyController.acceptRequest(familyRequestBean,null);
        pendingRequest.remove(familyRequestBean);
        confirmedRequest.add(familyRequestBean);
        familyRequestBean = null;
        displayPendingRequests();
    }
    
}
