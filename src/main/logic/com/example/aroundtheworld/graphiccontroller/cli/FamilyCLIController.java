package com.example.aroundtheworld.graphiccontroller.cli;

import com.example.aroundtheworld.appcontroller.LogoutController;
import com.example.aroundtheworld.exception.CommandErrorException;
import com.example.aroundtheworld.viewcli.FamilyViewCLI;


public class FamilyCLIController implements GraphicCLIController {
    private static final String TRAVELS = "1";
    private static final String REQUEST = "2";
    private static final String PROFILE = "3";
    private static final String LOGOUT = "4";
    private FamilyViewCLI familyViewCLI;
    @Override
    public void start() {
        this.familyViewCLI = new FamilyViewCLI(this);
        this.familyViewCLI.run();
    }

    public void executeCommand(String inputLine) throws CommandErrorException {
        switch (inputLine){
            case TRAVELS -> {
                FamilyTravelsCLIController familyTravelsCLIController = new FamilyTravelsCLIController();
                familyTravelsCLIController.start();
            }
            case REQUEST -> {
                FamilyRequestCLIController familyRequestCLIController = new FamilyRequestCLIController();
                familyRequestCLIController.start();
            }
            case PROFILE -> {
                FamilyProfileCLIController familyProfileCLIController = new FamilyProfileCLIController();
                familyProfileCLIController.start();
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
