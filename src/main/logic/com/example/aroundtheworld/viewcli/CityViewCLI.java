package com.example.aroundtheworld.viewcli;

import com.example.aroundtheworld.engineering.Printer;
import com.example.aroundtheworld.engineering.ScannerSupport;
import com.example.aroundtheworld.engineering.ShowExceptionSupport;
import com.example.aroundtheworld.exception.CommandErrorException;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.graphiccontroller.cli.CityCLIController;

import java.util.Scanner;

public class CityViewCLI {
    private CityCLIController cityCLIController;

    public CityViewCLI(CityCLIController cityCLIController) {
        this.cityCLIController = cityCLIController;
    }

    public void run(){
        Printer.printMessage("\nSelect city:");
        Printer.printMessage(" 1) London \n 2) Rome\n 3) Paris \n 4) New York \n 5) Valencia");
        Scanner scanner = new Scanner(System.in);
        try {
            cityCLIController.getCity(scanner.nextLine());
        } catch (CommandErrorException | NotFoundException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
        }
    }
    public void setResidenceInfo(String name, String address, String distanceSchool){
        Printer.printMessage("\nResidence Information:");
        Printer.printMessage(name);
        Printer.printMessage(address);
        Printer.printMessage(distanceSchool);
    }
    public void setSchoolInfo(String name, String address, String hours, String courses){
        Printer.printMessage("\nResidence Information:");
        Printer.printMessage(name);
        Printer.printMessage(address);
        Printer.printMessage(hours);
        Printer.printMessage(courses);
        Printer.printMessage("\nPress ENTER to go back:");
        ScannerSupport.waitEnter();

    }
    public void setCityInfo(String name, String language, String act1, String act2, String act3) {
        Printer.printMessage("******************"+name.toUpperCase()+"******************");
        Printer.printMessage("Language: "+language);
        Printer.printMessage("\nMain Activities:");
        Printer.printMessage(act1);
        Printer.printMessage(act2);
        Printer.printMessage(act3);
    }
}
