package com.example.aroundtheworld.controller_applicativo;

import com.example.aroundtheworld.bean.CompatibleFamilyBean;
import com.example.aroundtheworld.bean.FamilyBean;
import com.example.aroundtheworld.bean.FamilyRequestBean;
import com.example.aroundtheworld.controller_grafico.ContactFamilyGraphicControllerJavaFX;
import com.example.aroundtheworld.controller_grafico.FamilyListGraphicControllerJavaFX;
import com.example.aroundtheworld.dao.FamilyDAO;
import com.example.aroundtheworld.dao.FamilyRequestDAO;
import com.example.aroundtheworld.engineering.Session;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.model.Family;
import com.example.aroundtheworld.model.FamilyMember;
import com.example.aroundtheworld.model.FamilyPreferences;
import com.example.aroundtheworld.model.FamilyRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContactFamilyController {

    public float calculateCompatibility(FamilyRequestBean familyRequestBean, Family family){
        double compatibility;
        int siblings = 0;
        int checked = checkPreferences(familyRequestBean.getFamilyPreferences(), family.getPreferences());

        if(family.getAnimals().isEmpty() && familyRequestBean.getAnimals() == 0){
            checked++;
        }
        for (FamilyMember member : family.getMembers()) {
            if ((member.getParenthood().equals("Sister") || member.getParenthood().equals("Brother") || member.getParenthood().equals("Cousin")) && siblings == 0) {
                siblings = 1;
            }
        }
        if (siblings == familyRequestBean.getSiblings()){
            checked++;
        }
        compatibility = (double) checked/12 *100;
        compatibility = Math.round(compatibility*100.0)/100.0;
        return (float) compatibility;
    }

    private int checkPreferences(FamilyPreferences requestPref, FamilyPreferences familyPref) {
        int checked = 0;

        if(requestPref.getHouse().equals(familyPref.getHouse())){
            checked++;
        }
        if(requestPref.getVegetarian() == familyPref.getVegetarian()){
            checked++;
        }
        if(requestPref.getVegan() == familyPref.getVegan()){
            checked++;
        }
        if(requestPref.getTravels() == familyPref.getTravels()){
            checked++;
        }
        if(requestPref.getBooks() == familyPref.getBooks()){
            checked++;
        }
        if(requestPref.getFilm() == familyPref.getFilm()){
            checked++;
        }
        if(requestPref.getSport() == familyPref.getSport()){
            checked++;
        }
        if(requestPref.getNature() == familyPref.getNature()){
            checked++;
        }
        if(requestPref.getCooking() == familyPref.getCooking()){
            checked++;
        }
        if(requestPref.getVideoGames() == familyPref.getVideoGames()){
            checked++;
        }
        return checked;
    }

    public List<CompatibleFamilyBean> getCompatibleFamilies(FamilyRequestBean familyRequestBean) {
        float compatibility;
        List<CompatibleFamilyBean> compatibleFamilies = new ArrayList<>();
        CompatibleFamilyBean compatibleFamilyBean;
        List<Family> familyList = FamilyDAO.retrieveFamilies(familyRequestBean.getCity());
        for (Family family: familyList){
            compatibility = calculateCompatibility(familyRequestBean,family);
            compatibleFamilyBean = new CompatibleFamilyBean(family.getId(),family.getName(), family.getImgSrc(), compatibility);
            compatibleFamilies.add(compatibleFamilyBean);
        }
        return compatibleFamilies;
    }

    public FamilyBean getFamilyProfile(CompatibleFamilyBean compatibleFamilyBean) throws NotFoundException {
        Family family = FamilyDAO.retrieveFamily(compatibleFamilyBean.getEmail());

        FamilyBean familyBean = new FamilyBean(family.getName(), family.getCity(), family.getAddress(), family.getId(), family.getPhoneNumber(), family.getEmail());
        familyBean.setFamilyPreferences(family.getPreferences());
        familyBean.setAnimals(family.getAnimals());
        familyBean.setMembers(family.getMembers());
        familyBean.setImgSrc(family.getImgSrc());

        return familyBean;
    }

    public void saveRequest(FamilyRequestBean familyRequestBean) throws IOException {
        FamilyRequest familyRequest;
        familyRequest = new FamilyRequest(familyRequestBean.getCity(), familyRequestBean.getArrival(), familyRequestBean.getDeparture(), familyRequestBean.getSiblings(), familyRequestBean.getAnimals(), familyRequestBean.getIdStudent(), familyRequestBean.getIdFamily(), familyRequestBean.getCompatibility());
        familyRequest.setFamilyPreferences(familyRequestBean.getFamilyPreferences());
        FamilyRequestDAO.newRequest(familyRequest);

        FamilyListGraphicControllerJavaFX familyListGraphicControllerJavaFX = new FamilyListGraphicControllerJavaFX();
        familyListGraphicControllerJavaFX.savedRequest();
    }
}
