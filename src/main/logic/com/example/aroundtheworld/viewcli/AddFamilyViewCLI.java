package com.example.aroundtheworld.viewcli;

import com.example.aroundtheworld.engineering.Printer;
import com.example.aroundtheworld.engineering.ShowExceptionSupport;
import com.example.aroundtheworld.exception.CommandErrorException;
import com.example.aroundtheworld.exception.PhoneFormatException;
import com.example.aroundtheworld.graphiccontroller.cli.AddFamilyCLIController;

import java.util.Scanner;

public class AddFamilyViewCLI {
    private AddFamilyCLIController addFamilyCLIController;
    private FormViewCLI formViewCLI;

    public AddFamilyViewCLI(AddFamilyCLIController addFamilyCLIController) {
        this.addFamilyCLIController = addFamilyCLIController;
        formViewCLI = new FormViewCLI();

    }
    public void run() {
        int choice = 1;
        Printer.printMessage("\nInsert Family Name:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        Printer.printMessage("Insert Phone Number:");
        String phone = scanner.nextLine();
        String city = formViewCLI.printSelectionCity();
        Printer.printMessage("Insert Address:");
        String address = scanner.nextLine();
        String house = formViewCLI.printSelectionHouse();
        Printer.printMessage("\nSelect hobbies");
        int travels = formViewCLI.printPreference("Travels:");
        int sport = formViewCLI.printPreference("Sport:");
        int nature = formViewCLI.printPreference("Nature:");
        int books = formViewCLI.printPreference("Books:");
        int film = formViewCLI.printPreference("Film:");
        int videoGames = formViewCLI.printPreference("VideoGames:");
        int cooking = formViewCLI.printPreference("Cooking:");
        int food = formViewCLI.printSelectionFood(); // 0 -> vegetarian, 1 -> vegan, 2 -> no pref

        Printer.printMessage("Insert number of animals or 0 to continue");
        int dog = formViewCLI.printAnimal("Dogs:");
        int cat = formViewCLI.printAnimal("Cats:");
        int bird = formViewCLI.printAnimal("Birds:");
        int rodent = formViewCLI.printAnimal("Rodents:");
        int reptile = formViewCLI.printAnimal("Reptile:");
        int other = formViewCLI.printAnimal("Others:");

        try {
            addFamilyCLIController.createFamily(name, phone, address, city, house, food);
        } catch (PhoneFormatException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
        }

        addFamilyCLIController.setFamilyHobbies(travels,sport,nature,books, film, videoGames, cooking);
        addFamilyCLIController.setAnimals(dog,cat,bird,rodent,reptile,other);

        Printer.printMessage("Insert first member");

        String nameM = formViewCLI.printMember("Member name:");
        String ageM = formViewCLI.printMember("Member age:");
        String parenthoodM = formViewCLI.printMember("Member parenthood:");

        addFamilyCLIController.addMember(nameM, ageM, parenthoodM);

        choice = printNewMember();
        do{
            nameM = formViewCLI.printMember("Member name:");
            ageM = formViewCLI.printMember("Member age:");
            parenthoodM = formViewCLI.printMember("Member parenthood:");
            addFamilyCLIController.addMember(nameM, ageM, parenthoodM);
            choice = printNewMember();
        }while(choice == 1);

        addFamilyCLIController.addFamily();

    }

    public int printNewMember() {
        int choice = 0;
        Printer.printMessage("Select: ");
        Printer.printMessage(" 1) Add another member \n 2) Add family ");
        Scanner scanner = new Scanner(System.in);
        try {
            choice = addFamilyCLIController.executeNewMember(scanner.nextLine());
        } catch (CommandErrorException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
            printNewMember();
        }
        return choice;
    }
}
