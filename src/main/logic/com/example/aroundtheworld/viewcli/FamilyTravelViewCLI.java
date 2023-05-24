package com.example.aroundtheworld.viewcli;

import com.example.aroundtheworld.engineering.Printer;
import com.example.aroundtheworld.engineering.ScannerSupport;
import com.example.aroundtheworld.engineering.ShowExceptionSupport;
import com.example.aroundtheworld.exception.CommandErrorException;
import com.example.aroundtheworld.graphiccontroller.cli.FamilyTravelsCLIController;

import java.util.Scanner;

public class FamilyTravelViewCLI {
    private FamilyTravelsCLIController familyTravelsCLIController;


    public FamilyTravelViewCLI(FamilyTravelsCLIController familyTravelsCLIController) {
        this.familyTravelsCLIController = familyTravelsCLIController;
    }

    public void run() {
        Printer.printMessage("\n Would you like to see:");
        Printer.printMessage(" 1) Next Travels \n 2) Past Travels");
        Scanner scanner = new Scanner(System.in);
        try {
            familyTravelsCLIController.executeCommand(scanner.nextLine());
        } catch (CommandErrorException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
        }
    }

    public void displayPastTravel(String studentName, String studentNationality, int studentAge, String arrival, String departure, int rate) {
        displayNextTravel(studentName,studentNationality,studentAge,arrival,departure);
        if(rate != 0){
            Printer.printMessage("Rate: "+rate);
        } else {
            Printer.printMessage("Not rated yet");
        }
    }

    public void displayNextTravel(String studentName, String studentNationality, int studentAge, String arrival, String departure) {
        Printer.printMessage("\nStudent name: "+studentName);
        Printer.printMessage("Nationality: " + studentNationality);
        Printer.printMessage("Age: "+studentAge);
        Printer.printMessage("Arrival: "+ arrival);
        Printer.printMessage("Departure: "+ departure);
    }

    public void printNoTravels() {
        Printer.printMessage("\nThere are no travels!");
    }

    public void printContinue() {
        Printer.printMessage("\nPress ENTER to continue");
        ScannerSupport.waitEnter();
    }
}
