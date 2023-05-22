package com.example.aroundtheworld.viewcli;

import com.example.aroundtheworld.engineering.Printer;
import com.example.aroundtheworld.engineering.ShowExceptionSupport;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.graphiccontroller.cli.FormCLIController;
import com.example.aroundtheworld.graphiccontroller.cli.QuoteCLIController;

public class QuoteViewCLI{

    private QuoteCLIController quoteCLIController;
    private FormViewCLI formViewCLI;

    public QuoteViewCLI(QuoteCLIController quoteCLIController) {
        this.quoteCLIController = quoteCLIController;
        this.formViewCLI = new FormViewCLI();
    }

    public void run() {
        Printer.printMessage("\n-------------------------------------------- QUOTE FORM --------------------------------------------");
        String city = formViewCLI.printSelectionCity();
        int permanence = formViewCLI.printPeriod();
        int quantity = formViewCLI.printQuantity(permanence);
        String accommodation = formViewCLI.printAccommodation();
        String room = null;
        if (accommodation.equals("residence")){
            room = formViewCLI.printRoom();
        }
        try {
            this.quoteCLIController.getCityActivities(city, permanence, quantity, accommodation, room);
        } catch (NotFoundException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
        }
    }

    public void showActivities(String act1Bean, String act2Bean, String act3Bean) {
        int act1 = formViewCLI.printPreference(act1Bean);
        int act2 = formViewCLI.printPreference(act2Bean);
        int act3 = formViewCLI.printPreference(act3Bean);
        int sport = formViewCLI.printPreference("Would you like to take part in sports activities?");
        this.quoteCLIController.setActivities(act1, act2, act3, sport);
    }

    public void showQuote(int price) {
        Printer.printMessage("Price --> " + price + "€");
    }
}
