package com.example.aroundtheworld.viewcli;

import com.example.aroundtheworld.engineering.Printer;
import com.example.aroundtheworld.engineering.ShowExceptionSupport;
import com.example.aroundtheworld.exception.CommandErrorException;
import com.example.aroundtheworld.graphiccontroller.cli.StudentCLIController;

import java.util.Scanner;

public class StudentViewCLI {
    private final StudentCLIController studentCLIController;
    public StudentViewCLI(StudentCLIController studentCLIController) {
        this.studentCLIController = studentCLIController;
    }

    public void run() {
        Printer.printMessage("\n-------------------------------------------- STUDENT HOMEPAGE --------------------------------------------");
        Printer.printMessage(" 1) Book Residence \n 2) Contact Family \n 3) Request Quote \n 4) Manage Requests \n 5) View Travels \n 6) View Profile \n 7) Logout");
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
        try {
            this.studentCLIController.executeCommand(inputLine);
        } catch (CommandErrorException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
            run();
        }
    }


}
