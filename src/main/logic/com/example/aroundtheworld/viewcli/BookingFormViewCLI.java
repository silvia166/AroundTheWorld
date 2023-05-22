package com.example.aroundtheworld.viewcli;

import com.example.aroundtheworld.engineering.Printer;
import com.example.aroundtheworld.engineering.ShowExceptionSupport;
import com.example.aroundtheworld.exception.CommandErrorException;
import com.example.aroundtheworld.graphiccontroller.cli.BookResidenceCLIController;

import java.util.Scanner;

public class BookingFormViewCLI extends FormViewCLI {
    private BookResidenceCLIController bookResidenceCLIController;
    public BookingFormViewCLI(BookResidenceCLIController bookResidenceCLIController) {
        super(bookResidenceCLIController);
        this.bookResidenceCLIController = bookResidenceCLIController;
    }

    public void run() {
        Printer.printMessage("\n-------------------------------------------- RESIDENCE FORM --------------------------------------------");
        String city = printSelectionCity();
        String arrival = printArrival();
        String departure = printDeparture();
        String room = printRoom();
        BookResidenceCLIController bookResidenceCLIController = new BookResidenceCLIController();
        bookResidenceCLIController.sendResidenceRequest(city, arrival, departure, room);
    }

    public void displayRequestSentMessage() {
        Printer.printMessage("Request sent successfully\n");
    }
}
