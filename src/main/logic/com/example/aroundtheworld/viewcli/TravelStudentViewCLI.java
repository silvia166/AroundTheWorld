package com.example.aroundtheworld.viewcli;

import com.example.aroundtheworld.engineering.Printer;
import com.example.aroundtheworld.engineering.ScannerSupport;
import com.example.aroundtheworld.engineering.ShowExceptionSupport;
import com.example.aroundtheworld.exception.CommandErrorException;
import com.example.aroundtheworld.graphiccontroller.cli.TravelsStudentCLIController;

import java.util.Scanner;

public class TravelStudentViewCLI {
    private TravelsStudentCLIController travelsStudentCLIController;

    public TravelStudentViewCLI(TravelsStudentCLIController travelsStudentCLIController) {
        this.travelsStudentCLIController = travelsStudentCLIController;
    }

    public void run() {
        Printer.printMessage("\n Would you like to see:");
        Printer.printMessage(" 1) Next Travels \n 2) Past Travels");
        Scanner scanner = new Scanner(System.in);
        try {
            travelsStudentCLIController.executeCommand(scanner.nextLine());
        } catch (CommandErrorException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
        }
    }

    public void displayNextTravel(String familyName, String city, String arrival, String departure) {
        if(familyName != null){
            Printer.printMessage("\n"+familyName);
        } else {
            Printer.printMessage("\nResidence");
        }
        Printer.printMessage("City: " + city);
        Printer.printMessage("Arrival: "+ arrival);
        Printer.printMessage("Departure: "+ departure);
    }

    public void printContinue() {
        Printer.printMessage("\nPress ENTER to continue");
        ScannerSupport.waitEnter();
    }

    public void printNoTravels() {
        Printer.printMessage("\nThere are no travels!");
    }

    public void displayPastTravel(int id, String familyName, String city, String arrival, String departure, int rate) {
        Printer.printMessage("\nId Request: "+id);
        if(familyName != null){
            Printer.printMessage(familyName);
        } else {
            Printer.printMessage("Residence");
        }
        Printer.printMessage("City: " + city);
        Printer.printMessage("Arrival: "+ arrival);
        Printer.printMessage("Departure: "+ departure);
        if (familyName != null) {
            if (rate != 0) {
                Printer.printMessage("Rate: " + rate);
            } else {
                Printer.printMessage("Not rated yet");
            }
        }
        printAction();
    }

    public void printAction(){
        Printer.printMessage("\nInsert the ID of the travel you want to rate or 0 to go back");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        travelsStudentCLIController.getID(id);
    }

    public void manageTravel() {
        Printer.printMessage("Rate the travel from 1 to 5");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int rate = 0;
        try {
            rate = travelsStudentCLIController.executeRate(line);
            travelsStudentCLIController.setRate(rate);
        } catch (CommandErrorException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
        }
    }

    public void printError(int i) {
        if(i == 2) {
        Printer.printMessage("You have already rated this travel!");
        } else {
            Printer.printMessage("You can rate only travels in host family!");
        }
        travelsStudentCLIController.displayPastTravels();
    }

}
