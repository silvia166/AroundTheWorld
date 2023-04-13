package com.example.aroundtheworld.controller_applicativo;

import com.example.aroundtheworld.bean.CompatibleFamilyBean;
import com.example.aroundtheworld.bean.FamilyRequestBean;
import com.example.aroundtheworld.dao.FamilyDAO;
import com.example.aroundtheworld.model.Family;
import com.example.aroundtheworld.model.FamilyMember;
import com.example.aroundtheworld.model.FamilyPreferences;

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
}
