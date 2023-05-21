package com.example.aroundtheworld.graphiccontroller.cli;
import com.example.aroundtheworld.exception.*;
import com.example.aroundtheworld.viewcli.StudentViewCLI;

public class StudentCLIController implements GraphicCLIController{

    private static final String BOOK_RESIDENCE = "1";
    private static final String CONTACT_FAMILY = "2";
    private static final String REQUEST_QUOTE = "3";
    private static final String MANAGE_REQUESTS = "4";
    private static final String VIEW_TRAVELS = "5";
    private static final String VIEW_PROFILE = "6";
    private static final String LOGOUT = "7";
    StudentViewCLI studentViewCLI;
    @Override
    public void start() {
        this.studentViewCLI = new StudentViewCLI(this);
        this.studentViewCLI.run();
    }

    public void executeCommand(String inputLine) throws CommandErrorException {
        switch (inputLine){
            case BOOK_RESIDENCE -> {
                BookResidenceCLIController bookResidenceCLIController = new BookResidenceCLIController();
                bookResidenceCLIController.start();
                this.start();
            }
            case CONTACT_FAMILY -> {
                ContactFamilyCLIController contactFamilyCLIController = new ContactFamilyCLIController();
                contactFamilyCLIController.start();
                }
            case REQUEST_QUOTE -> {}
            case MANAGE_REQUESTS -> {}
            case VIEW_TRAVELS -> {}
            case VIEW_PROFILE -> {}
            case LOGOUT -> {}
            default -> throw new CommandErrorException();
        }
    }
}
