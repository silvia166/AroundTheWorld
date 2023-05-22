package com.example.aroundtheworld.viewcli;

import com.example.aroundtheworld.engineering.Printer;
import com.example.aroundtheworld.engineering.ShowExceptionSupport;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.graphiccontroller.cli.FormCLIController;
import com.example.aroundtheworld.graphiccontroller.cli.QuoteCLIController;

public class QuoteViewCLI extends FormViewCLI{

    private QuoteCLIController quoteCLIController;

    public QuoteViewCLI(QuoteCLIController quoteCLIController) {
        super(quoteCLIController);
        this.quoteCLIController = quoteCLIController;
    }

    public void run() {
        Printer.printMessage("\n-------------------------------------------- QUOTE FORM --------------------------------------------");
        String city = printSelectionCity();
        int permanence = printPeriod();
        int quantity = printQuantity(permanence);
        String accommodation = printAccommodation();
        String room = null;
        if (accommodation.equals("residence")){
            room = printRoom();
        }
        try {
            this.quoteCLIController.getCityActivities(city, permanence, quantity, accommodation, room);
        } catch (NotFoundException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
        }
    }

    public void showActivities(String act1Bean, String act2Bean, String act3Bean) {
        int act1 = printPreference(act1Bean);
        int act2 = printPreference(act2Bean);
        int act3 = printPreference(act3Bean);
        int sport = printPreference("Would you like to take part in sports activities?");
        this.quoteCLIController.setActivities(act1, act2, act3, sport);
    }

    public void showQuote(int price) {
        Printer.printMessage("Price --> " + price + "€");
    }
}
