package com.example.aroundtheworld.viewcli;

import com.example.aroundtheworld.engineering.Printer;
import com.example.aroundtheworld.engineering.ScannerSupport;
import com.example.aroundtheworld.engineering.ShowExceptionSupport;
import com.example.aroundtheworld.exception.CommandErrorException;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.graphiccontroller.cli.FamilyRequestCLIController;

import java.util.Scanner;


public class FamilyRequestViewCLI {
    private FamilyRequestCLIController familyRequestCLIController;

    public FamilyRequestViewCLI(FamilyRequestCLIController familyRequestCLIController) {
        this.familyRequestCLIController = familyRequestCLIController;
    }

    public void run() {
        Printer.printMessage("Select:");
        Printer.printMessage("\n 1) Pending request \n 2) Confirmed request");
        Scanner scanner = new Scanner(System.in);
        try {
            familyRequestCLIController.executeCommand(scanner.nextLine());
        } catch (CommandErrorException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
        }
    }

    public void printContinue() {
        Printer.printMessage("\nPress ENTER to continue");
        ScannerSupport.waitEnter();
    }

    public void printNoRequests() {
        Printer.printMessage("\nThere are no travels!");
    }

    public void displayRequest(int id, String studentName, float compatibility, String arrival, String departure) {
        Printer.printMessage("\nId request: " + id);
        Printer.printMessage("Student Name: " + studentName);
        Printer.printMessage("Arrival: "+ arrival);
        Printer.printMessage("Departure: "+ departure);
        Printer.printMessage("Compatibility: "+ compatibility+"%");
    }

    public void printAction() {
        Printer.printMessage("\nInsert the ID of the request you want to manage or 0 to go back");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        familyRequestCLIController.selectID(id);
    }

    public void printOption() {
        Printer.printMessage("\n 1) Accept request \n 2) Reject request \n 3) View Request");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        try {
            familyRequestCLIController.executeOption(choice);
        } catch (CommandErrorException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
        }
    }

    public void printStudentInfo(String name, String surname, String birth, String nationality, String email, String phoneNumber) {
        Printer.printMessage("\nStudent Information");
        Printer.printMessage("Name: " + name +" "+surname);
        Printer.printMessage("Date of birth: "+birth);
        Printer.printMessage("Nationality: "+nationality);
        Printer.printMessage("Email: "+email);
        Printer.printMessage("Phone Number: "+phoneNumber);
    }

    public void printRequestInfo(String cityBean, String arrival, String departure, String house, int siblings, int animals) {
        Printer.printMessage("\nRequest Information");
        Printer.printMessage("City: "+cityBean);
        Printer.printMessage("Arrival: "+arrival);
        Printer.printMessage("Departure: "+departure);
        Printer.printMessage("\nRequest preferences --> 1 for YES, 0 for NO");
        Printer.printMessage("House: "+house);
        Printer.printMessage("Siblings: "+siblings);
        Printer.printMessage("Animals: "+animals);
    }


    public void printRequestFood(int vegan, int vegetarian) {
        Printer.printMessage("Vegetarian: "+vegetarian);
        Printer.printMessage("Vegan: "+vegan);
    }

    public void printRequestHobbies(int travels, int sport, int nature, int books, int videoGames, int cooking, int film) {
        Printer.printMessage("Travels: "+travels);
        Printer.printMessage("Sport: "+sport);
        Printer.printMessage("Nature: "+nature);
        Printer.printMessage("Books: "+books);
        Printer.printMessage("VideoGames: "+videoGames);
        Printer.printMessage("Cooking: "+cooking);
        Printer.printMessage("Film: "+film);
        printContinue();
        familyRequestCLIController.displayPendingRequests();
    }
}
