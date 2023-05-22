package com.example.aroundtheworld.viewcli;
import com.example.aroundtheworld.engineering.Printer;
import com.example.aroundtheworld.graphiccontroller.cli.ContactFamilyCLIController;

public class ContactFamilyFormViewCLI{
    private ContactFamilyCLIController contactFamilyCLIController;
    private FormViewCLI formViewCLI;
    public ContactFamilyFormViewCLI(ContactFamilyCLIController contactFamilyCLIController) {
        this.formViewCLI = new FormViewCLI();
        this.contactFamilyCLIController = contactFamilyCLIController;
    }

    public void run() {
        Printer.printMessage("\n-------------------------------------------- CONTACT FAMILY FORM --------------------------------------------");
        String city = formViewCLI.printSelectionCity();
        String arrival = formViewCLI.printArrival();
        String departure = formViewCLI.printDeparture();
        String house = formViewCLI.printSelectionHouse();
        int food = formViewCLI.printSelectionFood(); // 0 -> vegetarian, 1 -> vegan, 2 -> no pref
        Printer.printMessage("\nSelect your preferences\n");
        int siblings = formViewCLI.printPreference("Siblings:");
        int animals = formViewCLI.printPreference("Animals:");
        Printer.printMessage("\nSelect your hobbies\n");
        int travels = formViewCLI.printPreference("Travels:");
        int sport = formViewCLI.printPreference("Sport:");
        int books = formViewCLI.printPreference("Books:");
        int nature = formViewCLI.printPreference("Nature:");
        int film = formViewCLI.printPreference("Film:");
        int videoGames = formViewCLI.printPreference("VideoGames:");
        int cooking = formViewCLI.printPreference("Cooking:");

        contactFamilyCLIController.setMainRequestInfo(city,arrival,departure,siblings,animals,house,food);
        contactFamilyCLIController.setRequestHobbies(travels,sport,books,nature,film,videoGames,cooking);

    }

}
