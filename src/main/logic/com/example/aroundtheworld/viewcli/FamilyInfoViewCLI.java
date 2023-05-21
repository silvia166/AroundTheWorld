package com.example.aroundtheworld.viewcli;

import com.example.aroundtheworld.engineering.Printer;
import com.example.aroundtheworld.engineering.ShowExceptionSupport;
import com.example.aroundtheworld.exception.CommandErrorException;
import com.example.aroundtheworld.graphiccontroller.cli.FamilyInfoCLIController;

import java.util.Scanner;

public class FamilyInfoViewCLI {

    private FamilyInfoCLIController familyInfoCLIController;

    public FamilyInfoViewCLI(FamilyInfoCLIController familyInfoCLIController) {
        this.familyInfoCLIController = familyInfoCLIController;
    }

    public void run() {
        Printer.printMessage("\nSelect:");
        Printer.printMessage(" 1) View Profile 2) Send Request");
        Scanner scanner = new Scanner(System.in);
        try {
            int selection = familyInfoCLIController.executeSelection(scanner.nextLine());
        } catch (CommandErrorException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
        }
        Printer.printMessage("\nInsert Id Family:");
        int idFamily = Integer.parseInt(scanner.nextLine());
    }

    public void displayFamily(String name, int id, float compatibility, String city) {
        Printer.printMessage("\nId Family: "+ id);
        Printer.printMessage("Family: " + name);
        Printer.printMessage("City: " + city);
        Printer.printMessage("Compatibility "+compatibility+" %");
    }
}
