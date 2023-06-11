package com.example.aroundtheworld.graphiccontroller.cli;

import com.example.aroundtheworld.appcontroller.LogoutController;
import com.example.aroundtheworld.exception.CommandErrorException;
import com.example.aroundtheworld.viewcli.AgencyViewCLI;


public class AgencyCLIController implements GraphicCLIController {
    private static final String ADD_FAMILY = "1";
    private static final String MANAGE_REQUEST = "2";
    private static final String LOGOUT = "3";

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
                AgencyRequestCLIController agencyRequestCLIController = new AgencyRequestCLIController();
                agencyRequestCLIController.start();
            }
            case LOGOUT -> {
                LogoutController logoutController = new LogoutController();
                logoutController.logout();
                LoginCLIController loginCLIController = new LoginCLIController();
                loginCLIController.start();
            }
            default -> throw new CommandErrorException();
        }
    }
}
