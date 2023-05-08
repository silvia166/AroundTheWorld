package com.example.aroundtheworld.controller_applicativo;

import com.example.aroundtheworld.bean.AnimalBean;
import com.example.aroundtheworld.bean.FamilyBean;
import com.example.aroundtheworld.bean.FamilyMemberBean;
import com.example.aroundtheworld.dao.AnimalDAO;
import com.example.aroundtheworld.dao.FamilyDAO;
import com.example.aroundtheworld.dao.FamilyMemberDAO;
import com.example.aroundtheworld.dao.FamilyPreferencesDAO;
import com.example.aroundtheworld.model.FamilyPreferences;

public class AddFamilyController {

    public void createFamily(FamilyBean familyBean) {
        FamilyDAO.addFamily(familyBean.getNameBean(), familyBean.getPhoneBean(), familyBean.getCityBean(), familyBean.getAddressBean(), familyBean.getImgSrcBean(), familyBean.getEmailBean());
        int id = FamilyDAO.retrieveFamilyID(familyBean.getNameBean());

        for(AnimalBean animalBean: familyBean.getAnimalsBean()){
            AnimalDAO.addAnimal(animalBean.getType(), animalBean.getQuantity(), id);
        }

        for(FamilyMemberBean familyMemberBean: familyBean.getMembersBean()){
            FamilyMemberDAO.addMember(familyMemberBean.getName(), familyMemberBean.getAge(), familyMemberBean.getParenthood(), id);
        }

        FamilyPreferences preferences = new FamilyPreferences();
        preferences.setHouse(familyBean.getHouse());
        preferences.setFood(familyBean.getVegetarian(), familyBean.getVegan());
        preferences.setHobbies(familyBean.getTravels(), familyBean.getSport(), familyBean.getBooks(), familyBean.getNature(), familyBean.getFilm(), familyBean.getVideoGames(), familyBean.getCooking());
        FamilyPreferencesDAO.addPreferences(preferences, id);
    }
}
