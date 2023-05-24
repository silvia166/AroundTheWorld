package com.example.aroundtheworld.viewcli;

import com.example.aroundtheworld.engineering.Printer;
import com.example.aroundtheworld.engineering.ScannerSupport;
import com.example.aroundtheworld.engineering.ShowExceptionSupport;
import com.example.aroundtheworld.exception.CommandErrorException;
import com.example.aroundtheworld.graphiccontroller.cli.AgencyRequestCLIController;

import java.util.Scanner;

public class AgencyRequestsViewCLI {
    private AgencyRequestCLIController agencyRequestCLIController;

    public AgencyRequestsViewCLI(AgencyRequestCLIController agencyRequestCLIController) {
        this.agencyRequestCLIController = agencyRequestCLIController;
    }

    public int run() {
        Printer.printMessage("\nSelect the type of requests:");
        Printer.printMessage(" 1) Pending \n 2) Modified \n 3) Confirmed");
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        try {
            choice = agencyRequestCLIController.executeCommand(scanner.nextLine());
        } catch (CommandErrorException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
        }
        return choice;
    }

    public void printNoRequest() {
        Printer.printMessage("\nThere are no requests to manage!");
    }

    public void printContinue() {
        Printer.printMessage("\nPress ENTER to continue");
        ScannerSupport.waitEnter();
    }


    public void showRequest(int id, String studentName, String arrival, String departure, String city) {
        Printer.printMessage("\nId Request: "+ id);
        Printer.printMessage("Student: "+studentName);
        Printer.printMessage("City: " + city);
        Printer.printMessage("Arrival: "+ arrival);
        Printer.printMessage("Departure: "+ departure);
    }

    public void manageRequest() {
        Printer.printMessage("\nInsert the ID of the request you want to manage or 0 to go back");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        agencyRequestCLIController.selectID(id);
    }

    public void displayRoom(int number, String type) {
        Printer.printMessage("Room: "+ number + " " + type);
    }

    public void printRequestedRoom(String room) {
        Printer.printMessage("\nRequested room: "+ room);
    }

    public int selectRoom() {
        Printer.printMessage("\nInsert the room number you want to reserve:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
