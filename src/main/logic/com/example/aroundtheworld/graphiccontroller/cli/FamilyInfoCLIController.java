package com.example.aroundtheworld.graphiccontroller.cli;

import com.example.aroundtheworld.appcontroller.ContactFamilyController;
import com.example.aroundtheworld.bean.*;
import com.example.aroundtheworld.engineering.ScannerSupport;
import com.example.aroundtheworld.engineering.Session;
import com.example.aroundtheworld.engineering.ShowExceptionSupport;
import com.example.aroundtheworld.exception.CommandErrorException;
import com.example.aroundtheworld.exception.DuplicateRequestException;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.viewcli.FamilyInfoViewCLI;
import com.example.aroundtheworld.viewcli.FamilyViewCLI;

import java.util.List;

public class FamilyInfoCLIController implements GraphicCLIController{

    private static final String VIEW_PROFILE = "1";
    private static final String SEND_REQUEST = "2";

    private FamilyInfoViewCLI familyInfoViewCLI;
    private List<CompatibleFamilyBean> compatibleFamilyBeanList;
    private FamilyRequestBean familyRequestBean;

    public void displayCompatibleFamilies(List<CompatibleFamilyBean> compatibleFamilies, FamilyRequestBean familyRequestBean) throws NotFoundException {
        this.familyInfoViewCLI = new FamilyInfoViewCLI(this);
        this.familyRequestBean = familyRequestBean;
        this.compatibleFamilyBeanList = compatibleFamilies;
        for(CompatibleFamilyBean compatibleFamily: compatibleFamilies){
            familyInfoViewCLI.displayFamily(compatibleFamily.getName(),compatibleFamily.getId(),compatibleFamily.getCompatibility(),familyRequestBean.getCityBean());
        }
        familyInfoViewCLI.run();
    }
    @Override
    public void start() {
        this.familyInfoViewCLI = new FamilyInfoViewCLI(this);
        FamilyBean familyBean = Session.getCurrentSession().getFamilyBean();

        familyInfoViewCLI.displayProfile(familyBean.getName(),familyBean.getAddress(),familyBean.getPhone(),familyBean.getVegan(),familyBean.getVegetarian(), familyBean.getHouse());
        familyInfoViewCLI.displayFamilyHobbies(familyBean.getBooks(),familyBean.getVideoGames(),familyBean.getFilm(),familyBean.getCooking(),familyBean.getNature(),familyBean.getSport(),familyBean.getTravels());
        for(AnimalBean animal : familyBean.getAnimals()){
            familyInfoViewCLI.displayAnimal(animal.getType(), animal.getQuantity());
        }
        int count = 0;
        for(FamilyMemberBean member : familyBean.getMembers()){
            count+=1;
            familyInfoViewCLI.displayMember(count, member.getName(), member.getAge(), member.getParenthood());
        }

        familyInfoViewCLI.displayContinueMessage();
        ScannerSupport.waitEnter();
        FamilyCLIController familyCLIController = new FamilyCLIController();
        familyCLIController.start();
    }

    public int executeSelection(String nextLine) throws CommandErrorException {
        int choice;
        switch (nextLine){
            case VIEW_PROFILE -> choice = 1;
            case SEND_REQUEST -> choice = 2;
            default -> throw new CommandErrorException();
        }
        return choice;
    }

    public void viewProfile(int idFamily) throws NotFoundException {

        FamilyBean familyBean = null;
        ContactFamilyController contactFamilyController = new ContactFamilyController();
        for(CompatibleFamilyBean compatibleFamily: compatibleFamilyBeanList){
            if(compatibleFamily.getId() == idFamily){
                familyBean = contactFamilyController.getFamilyProfile(compatibleFamily);
            }
        }

        familyInfoViewCLI.displayProfile(familyBean.getName(), familyBean.getAddress(), familyBean.getPhone(), familyBean.getVegan(), familyBean.getVegetarian(), familyBean.getHouse());
        familyInfoViewCLI.displayFamilyHobbies(familyBean.getBooks(), familyBean.getVideoGames(), familyBean.getFilm(), familyBean.getCooking(), familyBean.getNature(), familyBean.getSport(), familyBean.getTravels());
        for(AnimalBean animal : familyBean.getAnimals()){
            familyInfoViewCLI.displayAnimal(animal.getType(), animal.getQuantity());
        }
        int count = 0;
        for(FamilyMemberBean member : familyBean.getMembers()){
            count+=1;
            familyInfoViewCLI.displayMember(count, member.getName(), member.getAge(), member.getParenthood());
        }

        familyInfoViewCLI.displayContinueMessage();
        ScannerSupport.waitEnter();
        displayCompatibleFamilies(compatibleFamilyBeanList, familyRequestBean);
    }

    public void sendRequest(int idFamily) {
        ContactFamilyController contactFamilyController = new ContactFamilyController();
        this.familyRequestBean.setIdFamily(idFamily);
        for(CompatibleFamilyBean compatibleFamily: compatibleFamilyBeanList){
            if(compatibleFamily.getId() == idFamily){
                this.familyRequestBean.setCompatibility(compatibleFamily.getCompatibility());
            }
        }
        try {
            contactFamilyController.saveRequest(familyRequestBean);
            familyInfoViewCLI.printRequestSent();
        } catch (DuplicateRequestException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
        }
    }
}
