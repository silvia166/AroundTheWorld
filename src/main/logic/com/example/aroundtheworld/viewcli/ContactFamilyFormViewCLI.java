package com.example.aroundtheworld.viewcli;
import com.example.aroundtheworld.engineering.Printer;
import com.example.aroundtheworld.graphiccontroller.cli.ContactFamilyCLIController;

public class ContactFamilyFormViewCLI extends FormViewCLI{
    private ContactFamilyCLIController contactFamilyCLIController;
    public ContactFamilyFormViewCLI(ContactFamilyCLIController contactFamilyCLIController) {
        super(contactFamilyCLIController);
        this.contactFamilyCLIController = contactFamilyCLIController;
    }

    public void run() {
        String city = printSelectionCity();
        String arrival = printArrival();
        String departure = printDeparture();
        String house = printSelectionHouse();
        int food = printSelectionFood(); // 0 -> vegetarian, 1 -> vegan, 2 -> no pref
        Printer.printMessage("\nSelect your preferences\n");
        int siblings = printPreference("Siblings:");
        int animals = printPreference("Animals:");
        Printer.printMessage("\nSelect your hobbies\n");
        int travels = printPreference("Travels:");
        int sport = printPreference("Sport:");
        int books = printPreference("Books:");
        int nature = printPreference("Nature:");
        int film = printPreference("Film:");
        int videoGames = printPreference("VideoGames:");
        int cooking = printPreference("Cooking:");

        contactFamilyCLIController.setMainRequestInfo(city,arrival,departure,siblings,animals,house,food);
        contactFamilyCLIController.setRequestHobbies(travels,sport,books,nature,film,videoGames,cooking);

    }

}
