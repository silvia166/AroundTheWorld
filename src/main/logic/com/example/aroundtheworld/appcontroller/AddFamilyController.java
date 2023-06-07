package com.example.aroundtheworld.appcontroller;

import com.example.aroundtheworld.bean.AnimalBean;
import com.example.aroundtheworld.bean.FamilyBean;
import com.example.aroundtheworld.bean.FamilyMemberBean;
import com.example.aroundtheworld.dao.AnimalDAO;
import com.example.aroundtheworld.dao.FamilyDAO;
import com.example.aroundtheworld.dao.FamilyMemberDAO;
import com.example.aroundtheworld.dao.FamilyPreferencesDAO;
import com.example.aroundtheworld.engineering.Printer;
import com.example.aroundtheworld.model.Family;
import com.example.aroundtheworld.model.FamilyPreferences;


public class AddFamilyController {

    public void createFamily(FamilyBean familyBean) {
        FamilyDAO.addFamily(familyBean.getName(), familyBean.getPhone(), familyBean.getCity(), familyBean.getAddress(), familyBean.getImgSrc(), familyBean.getEmail());
        Family family = FamilyDAO.retrieveFamilyID(familyBean.getEmail());

        for(AnimalBean animalBean: familyBean.getAnimals()){
            AnimalDAO.addAnimal(animalBean.getType(), animalBean.getQuantity(), family.getId());
        }

        for(FamilyMemberBean familyMemberBean: familyBean.getMembers()){
            FamilyMemberDAO.addMember(familyMemberBean.getName(), familyMemberBean.getAge(), familyMemberBean.getParenthood(), family.getId());
        }

        FamilyPreferences preferences = new FamilyPreferences();
        preferences.setHouse(familyBean.getHouse());
        preferences.setFood(familyBean.getVegetarian(), familyBean.getVegan());
        preferences.setHobbies(familyBean.getTravels(), familyBean.getSport(), familyBean.getBooks(), familyBean.getNature(), familyBean.getFilm(), familyBean.getVideoGames(), familyBean.getCooking());
        FamilyPreferencesDAO.addPreferences(preferences, family.getId());

    }
}
