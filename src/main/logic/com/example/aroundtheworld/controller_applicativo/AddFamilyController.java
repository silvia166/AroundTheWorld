package com.example.aroundtheworld.controller_applicativo;

import com.example.aroundtheworld.bean.NewFamilyBean;
import com.example.aroundtheworld.dao.AnimalDAO;
import com.example.aroundtheworld.dao.FamilyDAO;
import com.example.aroundtheworld.dao.FamilyMemberDAO;
import com.example.aroundtheworld.dao.FamilyPreferencesDAO;

import java.lang.reflect.Member;

public class AddFamilyController {

    public void createFamily(NewFamilyBean newFamilyBean) {

        FamilyDAO.addFamily(newFamilyBean.getName(), newFamilyBean.getPhone(), newFamilyBean.getCity(), newFamilyBean.getAddress(), newFamilyBean.getImgSrc(), newFamilyBean.getEmail());
        int id = FamilyDAO.retrieveFamilyID(newFamilyBean.getName());
        AnimalDAO.addAnimal(newFamilyBean.getAnimals(), id);
        FamilyMemberDAO.addMember(newFamilyBean.getMembers(), id);
        FamilyPreferencesDAO.addPreferences(newFamilyBean.getFamilyPrefernces(), id);

    }
}
