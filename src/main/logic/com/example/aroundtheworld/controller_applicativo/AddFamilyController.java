package com.example.aroundtheworld.controller_applicativo;

import com.example.aroundtheworld.bean.FamilyBean;
import com.example.aroundtheworld.dao.AnimalDAO;
import com.example.aroundtheworld.dao.FamilyDAO;
import com.example.aroundtheworld.dao.FamilyMemberDAO;
import com.example.aroundtheworld.dao.FamilyPreferencesDAO;

public class AddFamilyController {

    public void createFamily(FamilyBean familyBean) {

        FamilyDAO.addFamily(familyBean.getName(), familyBean.getPhone(), familyBean.getCity(), familyBean.getAddress(), familyBean.getImgSrc(), familyBean.getEmail());
        int id = FamilyDAO.retrieveFamilyID(familyBean.getName());
        AnimalDAO.addAnimal(familyBean.getAnimals(), id);
        FamilyMemberDAO.addMember(familyBean.getMembers(), id);
        FamilyPreferencesDAO.addPreferences(familyBean.getFamilyPreferences(), id);

    }
}
