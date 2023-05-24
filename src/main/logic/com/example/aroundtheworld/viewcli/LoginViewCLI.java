package com.example.aroundtheworld.viewcli;

import com.example.aroundtheworld.engineering.Printer;
import com.example.aroundtheworld.engineering.ShowExceptionSupport;
import com.example.aroundtheworld.exception.CommandErrorException;
import com.example.aroundtheworld.exception.NotImplementedException;
import com.example.aroundtheworld.graphiccontroller.cli.LoginCLIController;

import java.util.Scanner;

public class LoginViewCLI {
    private final LoginCLIController loginCLIController;

    public LoginViewCLI(LoginCLIController cliLoginControllerCurrent) {
        this.loginCLIController = cliLoginControllerCurrent;
    }
    public void run()  {
        Printer.printMessage("\n-------------------------------------------- MAIN PAGE --------------------------------------------");
        Printer.printMessage(" 1) Login \n 2) Login with Google \n 3) Sign up \n 4) Discover a city");
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
        try {
            this.loginCLIController.executeCommand(inputLine);
        } catch (CommandErrorException | NotImplementedException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
            run();
        }
    }
    public void getCredentials() {
        Printer.printMessage("\nInsert email:");
        Scanner scanner = new Scanner(System.in);
        String email = scanner.nextLine();
        Printer.printMessage("\nInsert password:");
        String password = scanner.nextLine();

        LoginCLIController loginCLIController = new LoginCLIController();
        try {
            loginCLIController.checkLogin(email, password);
        }
        catch (Exception e){
            Printer.printError(e.getMessage());
        }
    }
}
