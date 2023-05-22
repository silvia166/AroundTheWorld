package com.example.aroundtheworld.viewcli;

import com.example.aroundtheworld.engineering.Printer;
import com.example.aroundtheworld.graphiccontroller.cli.StudentProfileCLIController;

public class StudentProfileViewCLI {
    private StudentProfileCLIController studentProfileCLIController;

    public StudentProfileViewCLI(StudentProfileCLIController studentProfileCLIController) {
        this.studentProfileCLIController = studentProfileCLIController;
    }

    public void run(String name, String surname, String nationality, String phoneNumber, String email, String birth) {
        Printer.printMessage("\nName: "+name);
        Printer.printMessage("Surname: "+surname);
        Printer.printMessage("Nationality: "+nationality);
        Printer.printMessage("Date Of Birth: "+birth);
        Printer.printMessage("Phone Number: "+phoneNumber);
        Printer.printMessage("Email: "+email);
    }
}
