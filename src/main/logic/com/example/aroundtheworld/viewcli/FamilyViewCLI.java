package com.example.aroundtheworld.viewcli;

import com.example.aroundtheworld.engineering.Printer;
import com.example.aroundtheworld.engineering.ShowExceptionSupport;
import com.example.aroundtheworld.exception.CommandErrorException;
import com.example.aroundtheworld.graphiccontroller.cli.FamilyCLIController;

import java.sql.SQLException;
import java.util.Scanner;

public class FamilyViewCLI {
    private FamilyCLIController familyCLIController;
    public FamilyViewCLI(FamilyCLIController familyCLIController) {
        this.familyCLIController = familyCLIController;
    }

    public void run() {
        Printer.printMessage("\n-------------------------------------------- FAMILY HOMEPAGE --------------------------------------------");
        Printer.printMessage(" 1) View Travels  \n 2) Manage Requests \n 3) View Profile \n 4) Logout");
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
        try {
            this.familyCLIController.executeCommand(inputLine);
        } catch (CommandErrorException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
            run();
        }
    }
}
