package com.example.aroundtheworld.appcontroller;

import com.example.aroundtheworld.bean.AnimalBean;
import com.example.aroundtheworld.bean.FamilyBean;
import com.example.aroundtheworld.bean.FamilyMemberBean;
import com.example.aroundtheworld.dao.FamilyDAO;
import com.example.aroundtheworld.model.Animal;
import com.example.aroundtheworld.model.Family;
import com.example.aroundtheworld.model.FamilyMember;
import com.example.aroundtheworld.model.FamilyPreferences;

import java.util.ArrayList;
import java.util.List;


public class AddFamilyController {

    public void createFamily(FamilyBean familyBean) {
        List<Animal> animals = new ArrayList<>();
        List<FamilyMember> familyMembers = new ArrayList<>();

        Family family = new Family(0, familyBean.getPhone(), familyBean.getName(), familyBean.getCity(), familyBean.getAddress(), familyBean.getEmail());

        FamilyPreferences preferences = new FamilyPreferences();
        preferences.setHouse(familyBean.getHouse());
        preferences.setFood(familyBean.getVegetarian(), familyBean.getVegan());
        preferences.setHobbies(familyBean.getTravels(), familyBean.getSport(), familyBean.getBooks(), familyBean.getNature(), familyBean.getFilm(), familyBean.getVideoGames(), familyBean.getCooking());

        family.setPreferences(preferences);

        for(AnimalBean animalBean: familyBean.getAnimals()){
            Animal animal = new Animal(animalBean.getType(), animalBean.getQuantity());
            animals.add(animal);
        }

        for(FamilyMemberBean familyMemberBean: familyBean.getMembers()){
            FamilyMember familyMember = new FamilyMember(familyMemberBean.getName(), familyMemberBean.getAge(), familyMemberBean.getParenthood());
            familyMembers.add(familyMember);
        }

        family.setAnimals(animals);
        family.setMembers(familyMembers);
        family.setImgSrc(familyBean.getImgSrc());

        FamilyDAO.addFamily(family);

    }
}
