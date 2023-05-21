package com.example.aroundtheworld.graphiccontroller.cli;

import com.example.aroundtheworld.appcontroller.LoginController;
import com.example.aroundtheworld.bean.LoginBean;
import com.example.aroundtheworld.engineering.ShowExceptionSupport;
import com.example.aroundtheworld.exception.*;
import com.example.aroundtheworld.viewcli.LoginViewCLI;
public class LoginCLIController implements GraphicCLIController {
    private static final String LOGIN = "1";
    private static final String LOGIN_WITH_GOOGLE = "2";
    private static final String SIGN_UP = "3";
    private static final String DISCOVER_CITY = "4";
    private LoginViewCLI loginViewCLI;
    @Override
    public void start() {
        this.loginViewCLI = new LoginViewCLI(this);
        this.loginViewCLI.run();
    }

    public void executeCommand(String inputLine) throws CommandErrorException, NotImplementedException {
        switch (inputLine){
            case LOGIN -> this.loginViewCLI.getCredentials();
            case LOGIN_WITH_GOOGLE -> throw new NotImplementedException();
            case SIGN_UP -> {
                CreateAccountCLIController createAccountCLIController = new CreateAccountCLIController();
                createAccountCLIController.start();
                this.start();
            }
            case DISCOVER_CITY -> {
                CityCLIController cityCLIController = new CityCLIController();
                cityCLIController.start();
            }
            default -> throw new CommandErrorException();
        }
    }

    public void checkLogin(String email, String password) {
        try {
            LoginBean loginBean = new LoginBean(email, password);
            LoginController loginController = new LoginController();
            loginController.checkUser(loginBean);

            if (loginBean.getRole() == 1) {
                loginController.studentLogin(loginBean);
                StudentCLIController studentCLIController = new StudentCLIController();
                studentCLIController.start();
            }
            else if (loginBean.getRole() == 2) {
                loginController.familyLogin(loginBean);
                FamilyCLIController familyHomepageCLIController = new FamilyCLIController();
                familyHomepageCLIController.start();
            }
            else if (loginBean.getRole() == 3) {
                AgencyCLIController agencyHomepageCLIController = new AgencyCLIController();
                agencyHomepageCLIController.start();
            }
            else {
                throw new UserNotFoundException();
            }
        }
        catch (EmailFormatException | NotFoundException | UserNotFoundException e){
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
            start();
        }
    }
}
