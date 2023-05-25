package com.example.aroundtheworld.appcontroller;

import com.example.aroundtheworld.bean.AnimalBean;
import com.example.aroundtheworld.bean.FamilyBean;
import com.example.aroundtheworld.bean.FamilyMemberBean;
import com.example.aroundtheworld.dao.AnimalDAO;
import com.example.aroundtheworld.dao.FamilyDAO;
import com.example.aroundtheworld.dao.FamilyMemberDAO;
import com.example.aroundtheworld.dao.FamilyPreferencesDAO;
import com.example.aroundtheworld.model.FamilyPreferences;

import java.io.*;

public class AddFamilyController {
    private static final String CSV_FILE_NAME = "src/main/res/Users.csv";

    public void createFamily(FamilyBean familyBean) {
        FamilyDAO.addFamily(familyBean.getName(), familyBean.getPhone(), familyBean.getCity(), familyBean.getAddress(), familyBean.getImgSrc(), familyBean.getEmail());
        int id = FamilyDAO.retrieveFamilyID(familyBean.getName());

        for(AnimalBean animalBean: familyBean.getAnimals()){
            AnimalDAO.addAnimal(animalBean.getType(), animalBean.getQuantity(), id);
        }

        for(FamilyMemberBean familyMemberBean: familyBean.getMembers()){
            FamilyMemberDAO.addMember(familyMemberBean.getName(), familyMemberBean.getAge(), familyMemberBean.getParenthood(), id);
        }

        FamilyPreferences preferences = new FamilyPreferences();
        preferences.setHouse(familyBean.getHouse());
        preferences.setFood(familyBean.getVegetarian(), familyBean.getVegan());
        preferences.setHobbies(familyBean.getTravels(), familyBean.getSport(), familyBean.getBooks(), familyBean.getNature(), familyBean.getFilm(), familyBean.getVideoGames(), familyBean.getCooking());
        FamilyPreferencesDAO.addPreferences(preferences, id);

        String user = familyBean.getEmail();
        user = user.concat(",123,family");

        File file = new File(CSV_FILE_NAME);

        try {
            BufferedReader input = new BufferedReader(new StringReader(user));
            PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
            output.println(user);
            output.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
