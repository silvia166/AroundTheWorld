package com.example.aroundtheworld.graphiccontroller.cli;

import com.example.aroundtheworld.appcontroller.RateController;
import com.example.aroundtheworld.appcontroller.TravelsController;
import com.example.aroundtheworld.bean.StudentBean;
import com.example.aroundtheworld.bean.TravelBean;
import com.example.aroundtheworld.engineering.Session;
import com.example.aroundtheworld.exception.CommandErrorException;
import com.example.aroundtheworld.viewcli.TravelStudentViewCLI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TravelsStudentCLIController implements GraphicCLIController{
    private static final String NEXT = "1";
    private static final String PAST = "2";
    private TravelStudentViewCLI travelStudentViewCLI;
    private List<TravelBean> nextTravels = new ArrayList<>();
    private List<TravelBean> pastTravels = new ArrayList<>();
    private TravelBean travelBean;

    @Override
    public void start() {
        this.travelStudentViewCLI = new TravelStudentViewCLI(this);
        StudentBean studentBean = Session.getCurrentSession().getStudentBean();

        TravelsController travelsController = new TravelsController();
        List<TravelBean> requestList = travelsController.getStudentTravels(studentBean);
        LocalDate currentDate = LocalDate.now();

        for(TravelBean travel: requestList){
            if(LocalDate.parse(travel.getArrival()).isAfter(currentDate)){
                nextTravels.add(travel);
            } else {
                pastTravels.add(travel);
            }
        }
        travelStudentViewCLI.run();
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
            travelStudentViewCLI.printNoTravels();
        }
        for (TravelBean travel : pastTravels){
            travelStudentViewCLI.displayPastTravel(travel.getId(),travel.getFamilyName(),travel.getCity(),travel.getArrival(),travel.getDeparture(),travel.getRate());
        }
    }

    public void displayNextTravels() {
        if (nextTravels.isEmpty()){
            travelStudentViewCLI.printNoTravels();
        }
        for (TravelBean travel : nextTravels){
            travelStudentViewCLI.displayNextTravel(travel.getFamilyName(),travel.getCity(),travel.getArrival(),travel.getDeparture());
        }
        travelStudentViewCLI.printContinue();
        StudentCLIController studentCLIController = new StudentCLIController();
        studentCLIController.start();
    }

    public void getID(int id) {
        if(id == 0){
            StudentCLIController studentCLIController = new StudentCLIController();
            studentCLIController.start();
        }else{
            checkTravel(id);
        }
    }

    private void checkTravel(int id) {
        for(TravelBean travel : pastTravels){
            if(travel.getId() == id){
                if(travel.getFamilyName() == null){
                    travelStudentViewCLI.printError(1);
                } else {
                if(travel.getRate() != 0){
                    travelStudentViewCLI.printError(2);
                } else {
                    this.travelBean = travel;
                    travelStudentViewCLI.manageTravel();
                }
                }
            }
        }
    }


    public int executeRate(String line) throws CommandErrorException {
        int rate;
        switch (line){
            case "1" -> rate = 1;
            case "2" -> rate = 2;
            case "3" -> rate = 3;
            case "4" -> rate = 4;
            case "5" -> rate = 5;
            default -> throw new CommandErrorException();
        }
        return rate;
    }

    public void setRate(int rate) {
        travelBean.setRate(rate);
        RateController rateController = new RateController();
        rateController.rateFamily(travelBean);
        travelStudentViewCLI.printContinue();
        StudentCLIController studentCLIController = new StudentCLIController();
        studentCLIController.start();
    }
}
