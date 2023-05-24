package com.example.aroundtheworld.viewcli;

import com.example.aroundtheworld.engineering.Printer;
import com.example.aroundtheworld.graphiccontroller.cli.BookResidenceCLIController;


public class BookingFormViewCLI{
    private BookResidenceCLIController bookResidenceCLIController;
    private FormViewCLI formViewCLI;
    public BookingFormViewCLI(BookResidenceCLIController bookResidenceCLIController) {
        this.bookResidenceCLIController = bookResidenceCLIController;
        this.formViewCLI = new FormViewCLI();
    }

    public void run() {
        Printer.printMessage("\n-------------------------------------------- RESIDENCE FORM --------------------------------------------");
        String city = formViewCLI.printSelectionCity();
        String arrival = formViewCLI.printArrival();
        String departure = formViewCLI.printDeparture();
        String room = formViewCLI.printRoom();
        bookResidenceCLIController.sendResidenceRequest(city, arrival, departure, room);
    }

    public void displayRequestSentMessage() {
        Printer.printMessage("\nYour request has been successfully sent!\n");
    }
}
