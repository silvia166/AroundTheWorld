package com.example.aroundtheworld.viewcli;

import com.example.aroundtheworld.engineering.Printer;
import com.example.aroundtheworld.graphiccontroller.cli.CreateAccountCLIController;

import java.util.Scanner;

public class AccountFormViewCLI {

    private CreateAccountCLIController createAccountCLIController;
    public AccountFormViewCLI(CreateAccountCLIController createAccountCLIController) {
        this.createAccountCLIController = createAccountCLIController;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        Printer.printMessage("\nInsert name:");
        String name = scanner.nextLine();

        Printer.printMessage("\nInsert surname:");
        String surname = scanner.nextLine();

        Printer.printMessage("\nInsert nationality:");
        String nationality = scanner.nextLine();

        Printer.printMessage("\nInsert date of birh with date format yyyy/mm/dd:");
        String birth = scanner.nextLine();

        Printer.printMessage("\nInsert phone number:");
        String phone = scanner.nextLine();

        Printer.printMessage("\nChoose email:");
        String email = scanner.nextLine();

        Printer.printMessage("\nChoose password:");
        String password = scanner.nextLine();

        createAccountCLIController.createAccount(name, surname, nationality, birth, phone, email, password);
    }
}
