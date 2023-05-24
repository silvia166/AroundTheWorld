package com.example.aroundtheworld.viewcli;

import com.example.aroundtheworld.engineering.Printer;
import com.example.aroundtheworld.engineering.ScannerSupport;
import com.example.aroundtheworld.engineering.ShowExceptionSupport;
import com.example.aroundtheworld.exception.CommandErrorException;
import com.example.aroundtheworld.graphiccontroller.cli.StudentCLIController;
import com.example.aroundtheworld.graphiccontroller.cli.StudentRequestCLIController;

import java.util.Scanner;

public class StudentRequestViewCLI {
    private StudentRequestCLIController studentRequestCLIController;

    public StudentRequestViewCLI(StudentRequestCLIController studentRequestCLIController) {
        this.studentRequestCLIController = studentRequestCLIController;
    }

    public int run() {
        Printer.printMessage("\nSelect the type of requests:");
        Printer.printMessage(" 1) Residence \n 2) Host Family");
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        try {
            choice = studentRequestCLIController.executeCommand(scanner.nextLine());
        } catch (CommandErrorException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
        }
        return choice;
    }

    public void showResidenceRequest(int id, int status, String arrival, String departure, String room, String city) {
        Printer.printMessage("\nId Request: "+ id);
        Printer.printMessage("City: " + city);
        Printer.printMessage("Arrival: "+ arrival);
        Printer.printMessage("Departure: "+ departure);
        String label = "Requested: ";
        if (status == 1){
            label = "Modified: ";
            if(room.equals("single")){
                room = "double";
            } else {
                room = "single";
            }
        }
        Printer.printMessage(label + room + " room ");
    }

    public void manageRequest(int type) {
        Printer.printMessage("\nInsert the ID of the request you want to manage or 0 to go back");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        studentRequestCLIController.selectID(id, type);
    }

    public void printActionResidence() {
        Printer.printMessage("\n 1) Accept request \n 2) Reject request \n 3) Back");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        try {
            studentRequestCLIController.executeAction(choice, 1);
        } catch (CommandErrorException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
        }
    }
    public void printNoRequest() {
        Printer.printMessage("\nThere are no requests to manage!");
    }

    public void showFamilyRequest(int id, int status, String arrival, String departure, String familyName, String city) {
        Printer.printMessage("\nId Request: "+ id);
        Printer.printMessage("Family: "+familyName);
        Printer.printMessage("City: " + city);
        Printer.printMessage("Arrival: "+ arrival);
        Printer.printMessage("Departure: "+ departure);
        String label = "Status: Pending Request";
        if (status == 1){
            label = "Status: Accepted Request";
        }
        Printer.printMessage(label);
    }

    public void printActionFamily() {
        Printer.printMessage("\n 1) Book travel \n 2) Reject \n 3) Back");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        try {
            studentRequestCLIController.executeAction(choice, 2);
        } catch (CommandErrorException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
        }
    }

    public void printErrorSelection(String type){
        Printer.printMessage("\nYou selected a pending request, wait for the " +type+" to manage it");
        Printer.printMessage("Press ENTER to continue");
        ScannerSupport.waitEnter();
        StudentCLIController studentCLIController = new StudentCLIController();
        studentCLIController.start();
    }
}
