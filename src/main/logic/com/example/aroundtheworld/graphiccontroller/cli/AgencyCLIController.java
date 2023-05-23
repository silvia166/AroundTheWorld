package com.example.aroundtheworld.graphiccontroller.cli;

import com.example.aroundtheworld.engineering.Printer;
import com.example.aroundtheworld.exception.CommandErrorException;
import com.example.aroundtheworld.viewcli.AddFamilyViewCLI;
import com.example.aroundtheworld.viewcli.AgencyViewCLI;
import com.example.aroundtheworld.viewcli.StudentViewCLI;

public class AgencyCLIController implements GraphicCLIController {
    private static final String ADD_FAMILY = "1";
    private static final String MANAGE_REQUEST = "2";

    private AgencyViewCLI agencyViewCLI;
    @Override
    public void start() {
        this.agencyViewCLI = new AgencyViewCLI(this);
        this.agencyViewCLI.run();
    }

    public void executeCommand(String inputLine) throws CommandErrorException {
        switch (inputLine){
            case ADD_FAMILY -> {
                AddFamilyCLIController addFamilyCLIController = new AddFamilyCLIController();
                addFamilyCLIController.start();
                this.start();
            }
            case MANAGE_REQUEST -> {
                ContactFamilyCLIController contactFamilyCLIController = new ContactFamilyCLIController();
                contactFamilyCLIController.start();
                this.start();
            }
            default -> throw new CommandErrorException();
        }
    }
}
