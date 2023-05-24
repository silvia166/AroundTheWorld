package com.example.aroundtheworld.graphiccontroller.cli;

import com.example.aroundtheworld.appcontroller.TravelsController;
import com.example.aroundtheworld.bean.FamilyBean;
import com.example.aroundtheworld.bean.TravelBean;
import com.example.aroundtheworld.engineering.Session;
import com.example.aroundtheworld.engineering.ShowExceptionSupport;
import com.example.aroundtheworld.exception.CommandErrorException;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.viewcli.FamilyTravelViewCLI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FamilyTravelsCLIController implements GraphicCLIController{
    private static final String NEXT = "1";
    private static final String PAST = "2";
    private FamilyTravelViewCLI familyTravelViewCLI;

    private List<TravelBean> nextTravels = new ArrayList<>();
    private List<TravelBean> pastTravels = new ArrayList<>();

    @Override
    public void start() {
        this.familyTravelViewCLI = new FamilyTravelViewCLI(this);
        FamilyBean familyBean = Session.getCurrentSession().getFamilyBean();
        TravelsController travelsController = new TravelsController();
        try {
            List<TravelBean> travelBeanList = travelsController.getFamilyTravels(familyBean);
            LocalDate currentDate = LocalDate.now();

            for(TravelBean travel: travelBeanList){
                if(LocalDate.parse(travel.getArrival()).isAfter(currentDate)){
                    nextTravels.add(travel);
                } else {
                    pastTravels.add(travel);
                }
            }
            familyTravelViewCLI.run();
        } catch (NotFoundException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
        }
    }

    public void executeCommand(String nextLine) throws CommandErrorException {
        switch (nextLine){
            case NEXT -> displayNextTravels();
            case PAST -> displayPastTravels();
            default -> throw new CommandErrorException();
        }
    }

    public void displayPastTravels() {
        if (pastTravels.isEmpty()){
            familyTravelViewCLI.printNoTravels();
        }
        for (TravelBean travel : pastTravels){
            familyTravelViewCLI.displayPastTravel(travel.getStudentName(),travel.getStudentNationality(),travel.getStudentAge(),travel.getArrival(),travel.getDeparture(),travel.getRate());
        }
        familyTravelViewCLI.printContinue();
        FamilyCLIController familyCLIController = new FamilyCLIController();
        familyCLIController.start();
    }

    public void displayNextTravels() {
        if (nextTravels.isEmpty()){
            familyTravelViewCLI.printNoTravels();
        }
        for (TravelBean travel : nextTravels){
            familyTravelViewCLI.displayNextTravel(travel.getStudentName(),travel.getStudentNationality(),travel.getStudentAge(),travel.getArrival(),travel.getDeparture());
        }
        familyTravelViewCLI.printContinue();
        FamilyCLIController familyCLIController = new FamilyCLIController();
        familyCLIController.start();
    }
}
