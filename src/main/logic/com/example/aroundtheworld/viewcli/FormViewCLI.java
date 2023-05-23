package com.example.aroundtheworld.viewcli;

import com.example.aroundtheworld.engineering.Printer;
import com.example.aroundtheworld.engineering.ShowExceptionSupport;
import com.example.aroundtheworld.exception.CommandErrorException;
import com.example.aroundtheworld.graphiccontroller.cli.FormCLIController;

import java.util.Scanner;

public class FormViewCLI {
    FormCLIController formCLIController;
    protected FormViewCLI() {
        this.formCLIController = new FormCLIController();
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

    public int printPeriod(){
        int choice=0;
        Printer.printMessage("\nSelect permanence:");
        Printer.printMessage(" 1) Weeks \n 2) Months \n 3) Years");
        Scanner scanner = new Scanner(System.in);
        try {
            choice = formCLIController.executePermanence(scanner.nextLine());
        } catch (CommandErrorException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
            printPeriod();
        }
        return choice;
    }

    public int printQuantity(int count){

        switch (count){
            case 1-> {Printer.printMessage("\nInsert a number from 1 to 3 weeks:");}
            case 2-> {Printer.printMessage("\nInsert a number from 1 to 11 months:");}
            default -> {Printer.printMessage("\nInsert a number from 1 to 2 years:");}
        }
        int choice=0;
        Scanner scanner = new Scanner(System.in);
        try {
            choice = formCLIController.executePeriod(scanner.nextLine(), count);
        } catch (CommandErrorException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
            printQuantity(count);
        }
        return choice;
    }

    public String printAccommodation(){
        String choice = null;
        Printer.printMessage("\nSelect the type of accommodation:");
        Printer.printMessage(" 1) Residence \n 2) Host Family");
        Scanner scanner = new Scanner(System.in);
        try {
            choice = formCLIController.executeAccommodation(scanner.nextLine());
        } catch (CommandErrorException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
            printAccommodation();
        }
        return choice;
    }

    public int printAnimal(String animal){
        int choice;
        Printer.printMessage(animal);
        Scanner scanner = new Scanner(System.in);
        choice = formCLIController.executeAnimal(scanner.nextLine());
        return choice;
    }

    public String printMember(String message){
        Printer.printMessage(message);
        Scanner scanner = new Scanner(System.in);
        return  scanner.nextLine();
    }


}
