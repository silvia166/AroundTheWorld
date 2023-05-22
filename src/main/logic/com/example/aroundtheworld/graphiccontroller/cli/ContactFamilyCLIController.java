package com.example.aroundtheworld.graphiccontroller.cli;
import com.example.aroundtheworld.appcontroller.ContactFamilyController;
import com.example.aroundtheworld.bean.CompatibleFamilyBean;
import com.example.aroundtheworld.bean.FamilyRequestBean;
import com.example.aroundtheworld.engineering.Session;
import com.example.aroundtheworld.engineering.ShowExceptionSupport;
import com.example.aroundtheworld.exception.MessageException;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.viewcli.ContactFamilyFormViewCLI;

import java.util.List;

public class ContactFamilyCLIController extends FormCLIController {

    private ContactFamilyFormViewCLI contactFamilyFormViewCLI;
    private FamilyRequestBean familyRequestBean;

    @Override
    public void start() {
        this.contactFamilyFormViewCLI = new ContactFamilyFormViewCLI(this);
        this.contactFamilyFormViewCLI.run();
    }

    public void setMainRequestInfo(String city, String arrival, String departure, int siblings, int animals, String house, int food) {
        int idStudent = Session.getCurrentSession().getStudentBean().getId();
        this.familyRequestBean = new FamilyRequestBean(city,arrival, departure,siblings,animals,idStudent);
        this.familyRequestBean.setHouse(house);
        switch (food){
            case 0 -> this.familyRequestBean.setFood(1,0);
            case 1 -> this.familyRequestBean.setFood(0,1);
            default -> this.familyRequestBean.setFood(0,0);
        }
    }

    public void setRequestHobbies(int travels, int sport, int books, int nature, int film, int videoGames, int cooking) {
        this.familyRequestBean.setHobbies(travels,sport,books,nature,film,videoGames,cooking);
        ContactFamilyController contactFamilyController = new ContactFamilyController();
        List<CompatibleFamilyBean> compatibleFamilies = null;
        try {
            compatibleFamilies = contactFamilyController.getCompatibleFamilies(familyRequestBean);
            FamilyInfoCLIController familyInfoCLIController = new FamilyInfoCLIController();
            familyInfoCLIController.displayCompatibleFamilies(compatibleFamilies,familyRequestBean);
        } catch (MessageException | NotFoundException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
        }
    }

}
