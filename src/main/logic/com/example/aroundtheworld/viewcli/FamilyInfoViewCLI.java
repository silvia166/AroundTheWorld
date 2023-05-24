package com.example.aroundtheworld.viewcli;

import com.example.aroundtheworld.engineering.Printer;
import com.example.aroundtheworld.engineering.ShowExceptionSupport;
import com.example.aroundtheworld.exception.CommandErrorException;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.graphiccontroller.cli.FamilyInfoCLIController;

import java.util.Scanner;

public class FamilyInfoViewCLI {

    private FamilyInfoCLIController familyInfoCLIController;

    public FamilyInfoViewCLI(FamilyInfoCLIController familyInfoCLIController) {
        this.familyInfoCLIController = familyInfoCLIController;
    }

    public void run() throws NotFoundException {
        int selection = 0;
        Printer.printMessage("\nSelect:");
        Printer.printMessage(" 1) View Profile 2) Send Request");
        Scanner scanner = new Scanner(System.in);
        try {
            selection = familyInfoCLIController.executeSelection(scanner.nextLine());
        } catch (CommandErrorException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
        }
        Printer.printMessage("\nInsert Id Family:");
        int idFamily = Integer.parseInt(scanner.nextLine());

        if(selection == 1){
            familyInfoCLIController.viewProfile(idFamily);
        } else {
            familyInfoCLIController.sendRequest(idFamily);
        }
    }

    public void displayFamily(String name, int id, float compatibility, String city) {
        Printer.printMessage("\nId Family: "+ id);
        Printer.printMessage("Family: " + name);
        Printer.printMessage("City: " + city);
        Printer.printMessage("Compatibility "+compatibility+" %");
    }

    public void displayProfile(String name, String address, String phone, int vegan, int vegetarian, String house) {
        Printer.printMessage("\nFamily name: " + name);
        Printer.printMessage("Address: " + address);
        Printer.printMessage("Phone Number: " + phone);
        Printer.printMessage("House: " + house + " Room");
        if(vegetarian == 1){
            Printer.printMessage("Food Preferences: Vegetarian");
        } else if (vegan == 1) {
            Printer.printMessage("Food Preferences: Vegan");
        }else{
            Printer.printMessage("Food Preferences: No Preferences");
        }
    }

    public void displayFamilyHobbies(int books, int videoGames, int film, int cooking, int nature, int sport, int travels) {
        Printer.printMessage("\nHobbies: ");
        if(books == 1){
            Printer.printMessage("Books");
        }
        if(videoGames == 1){
            Printer.printMessage("VideoGames");
        }
        if(film == 1){
            Printer.printMessage("Film");
        }
        if(cooking == 1){
            Printer.printMessage("Cooking");
        }
        if(nature == 1){
            Printer.printMessage("Nature");
        }
        if(sport == 1){
            Printer.printMessage("Sport");
        }
        if(travels == 1){
            Printer.printMessage("Travels");
        }

        Printer.printMessage("\nAnimals: ");
    }

    public void displayAnimal(String type, int quantity) {
        Printer.printMessage(quantity + " " + type);
    }

    public void displayMember(int count, String name, int age, String parenthood) {
        Printer.printMessage("Member " + count);
        Printer.printMessage(name + " " + age + " " + parenthood);
    }

    public void displayContinueMessage() {
        Printer.printMessage("\nPress ENTER to continue");
    }

    public void printRequestSent() {
        Printer.printMessage("\nYour request has been successfully sent!");
    }
}
