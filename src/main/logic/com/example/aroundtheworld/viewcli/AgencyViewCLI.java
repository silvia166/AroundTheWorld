package com.example.aroundtheworld.viewcli;

import com.example.aroundtheworld.engineering.Printer;
import com.example.aroundtheworld.engineering.ShowExceptionSupport;
import com.example.aroundtheworld.exception.CommandErrorException;
import com.example.aroundtheworld.graphiccontroller.cli.AgencyCLIController;

import java.util.Scanner;

public class AgencyViewCLI {
    private AgencyCLIController agencyCLIController;

    public AgencyViewCLI(AgencyCLIController agencyCLIController) {
        this.agencyCLIController = agencyCLIController;
    }

    public void run() {
        Printer.printMessage("\n-------------------------------------------- AGENCY HOMEPAGE --------------------------------------------");
        Printer.printMessage(" 1) Add Family  \n 2) Manage Requests \n 3) Logout");
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
        try {
            this.agencyCLIController.executeCommand(inputLine);
        } catch (CommandErrorException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
            run();
        }
    }
}
