package com.example.aroundtheworld.viewcli;

import com.example.aroundtheworld.engineering.Printer;
import com.example.aroundtheworld.engineering.ShowExceptionSupport;
import com.example.aroundtheworld.exception.CommandErrorException;
import com.example.aroundtheworld.graphiccontroller.cli.FormCLIController;

import java.util.Scanner;

public abstract class FormViewCLI {
    FormCLIController formCLIController;
    protected FormViewCLI(FormCLIController formCLIController) {
        this.formCLIController = formCLIController;
    }
    public String printSelectionCity() {
        String city = null;
        Printer.printMessage("\nSelect city:");
        Printer.printMessage(" 1) London \n 2) Rome\n 3) Paris \n 4) New York \n 5) Valencia");
        Scanner scanner = new Scanner(System.in);
        try {
            city = formCLIController.executeCity(scanner.nextLine());
        } catch (CommandErrorException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
            printSelectionCity();
        }
        return city;
    }
    public String printArrival(){
        Printer.printMessage("\nInsert arrival with date format yyyy/mm/dd:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    public String printDeparture(){
        Printer.printMessage("\nInsert departure with date format yyyy/mm/dd:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    public String printRoom(){
        String room = null;
        Printer.printMessage("\nSelect room type:");
        Printer.printMessage(" 1) Single Room \n 2) Double Room");
        Scanner scanner = new Scanner(System.in);
        try {
            room = formCLIController.executeRoom(scanner.nextLine());
        } catch (CommandErrorException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
            printRoom();
        }
        return room;
    }
    public String printSelectionHouse(){
        String room = null;
        Printer.printMessage("\nSelect room type:");
        Printer.printMessage(" 1) Single Room \n 2) Shared Room");
        Scanner scanner = new Scanner(System.in);
        try {
            room = formCLIController.executeHouse(scanner.nextLine());
        } catch (CommandErrorException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
            printSelectionHouse();
        }
        return room;
    }

    public int printSelectionFood(){
        int food = 3;
        Printer.printMessage("\nSelect food preferences:");
        Printer.printMessage(" 1) Vegetarian \n 2) Vegan \n 3) No preferences");
        Scanner scanner = new Scanner(System.in);
        try {
            food = formCLIController.executeFood(scanner.nextLine());
        } catch (CommandErrorException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
            printSelectionFood();
        }
        return food;
    }
    public int printPreference(String pref){
        int choice = 2;
        Printer.printMessage(pref);
        Printer.printMessage(" 1) Yes \n 2) No");
        Scanner scanner = new Scanner(System.in);
        try {
            choice = formCLIController.executeChoice(scanner.nextLine());
        } catch (CommandErrorException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
            printPreference(pref);
        }
        return choice;
    }


}
