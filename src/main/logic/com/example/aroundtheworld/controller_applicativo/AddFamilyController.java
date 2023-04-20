package com.example.aroundtheworld.controller_applicativo;

import com.example.aroundtheworld.bean.FamilyBean;
import com.example.aroundtheworld.dao.AnimalDAO;
import com.example.aroundtheworld.dao.FamilyDAO;
import com.example.aroundtheworld.dao.FamilyMemberDAO;
import com.example.aroundtheworld.dao.FamilyPreferencesDAO;

public class AddFamilyController {

    public void createFamily(FamilyBean familyBean) {
        FamilyDAO.addFamily(familyBean.getNameBean(), familyBean.getPhoneBean(), familyBean.getCityBean(), familyBean.getAddressBean(), familyBean.getImgSrcBean(), familyBean.getEmailBean());
        int id = FamilyDAO.retrieveFamilyID(familyBean.getNameBean());
        AnimalDAO.addAnimal(familyBean.getAnimalsBean(), id);
        FamilyMemberDAO.addMember(familyBean.getMembersBean(), id);
        FamilyPreferencesDAO.addPreferences(familyBean.getFamilyPreferencesBean(), id);
    }
}
