package com.example.aroundtheworld.graphiccontroller.cli;

import com.example.aroundtheworld.bean.CompatibleFamilyBean;
import com.example.aroundtheworld.bean.FamilyRequestBean;
import com.example.aroundtheworld.exception.CommandErrorException;
import com.example.aroundtheworld.viewcli.FamilyInfoViewCLI;

import java.util.List;

public class FamilyInfoCLIController implements GraphicCLIController{

    private static final String VIEW_PROFILE = "1";
    private static final String SEND_REQUEST = "2";

    private FamilyInfoViewCLI familyInfoViewCLI;
    public void displayCompatibleFamilies(List<CompatibleFamilyBean> compatibleFamilies, FamilyRequestBean familyRequestBean) {
        this.familyInfoViewCLI = new FamilyInfoViewCLI(this);
        for(CompatibleFamilyBean compatibleFamily: compatibleFamilies){
            familyInfoViewCLI.displayFamily(compatibleFamily.getName(),compatibleFamily.getId(),compatibleFamily.getCompatibility(),familyRequestBean.getCityBean());
        }
        familyInfoViewCLI.run();
    }
    @Override
    public void start() {}

    public int executeSelection(String nextLine) throws CommandErrorException {
        int choice;
        switch (nextLine){
            case VIEW_PROFILE -> choice = 1;
            case SEND_REQUEST -> choice = 2;
            default -> throw new CommandErrorException();
        }
        return choice;
    }
}
