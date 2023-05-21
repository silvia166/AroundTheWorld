package com.example.aroundtheworld.viewcli;

import com.example.aroundtheworld.engineering.Printer;
import com.example.aroundtheworld.engineering.ShowExceptionSupport;
import com.example.aroundtheworld.exception.CommandErrorException;
import com.example.aroundtheworld.graphiccontroller.cli.BookResidenceCLIController;

import java.util.Scanner;

public class BookingFormViewCLI {
    private BookResidenceCLIController bookResidenceCLIController;
    public BookingFormViewCLI(BookResidenceCLIController bookResidenceCLIController) {
        this.bookResidenceCLIController = bookResidenceCLIController;
    }

    public void run() {
        String city = null;
        String room = null;
        Printer.printMessage("\n-------------------------------------------- RESIDENCE FORM --------------------------------------------");
        Printer.printMessage("\nSelect city:");
        Printer.printMessage(" 1) London \n 2) Rome\n 3) Paris \n 4) New York \n 5) Valencia");
        Scanner scanner = new Scanner(System.in);
        String inputLineCity = scanner.nextLine();

        Printer.printMessage("\nInsert arrival with date format yyyy/mm/dd:");
        scanner = new Scanner(System.in);
        String arrival = scanner.nextLine();

        Printer.printMessage("\nInsert departure with date format yyyy/mm/dd:");
        scanner = new Scanner(System.in);
        String departure = scanner.nextLine();

        Printer.printMessage("\nSelect room type:");
        Printer.printMessage(" 1) Single Room \n 2) Double Room");
        scanner = new Scanner(System.in);
        String inputLineRoom = scanner.nextLine();

        try {
            city = bookResidenceCLIController.executeCity(inputLineCity);
            room = bookResidenceCLIController.executeRoom(inputLineRoom);
        } catch (CommandErrorException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
        }

        BookResidenceCLIController bookResidenceCLIController = new BookResidenceCLIController();
        bookResidenceCLIController.sendResidenceRequest(city, arrival, departure, room);
    }

    public void displayRequestSentMessage() {
        Printer.printMessage("Request sent successfully\n");
    }
}
