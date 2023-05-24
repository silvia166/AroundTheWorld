package com.example.aroundtheworld.graphiccontroller.cli;
import com.example.aroundtheworld.appcontroller.AddFamilyController;
import com.example.aroundtheworld.bean.AnimalBean;
import com.example.aroundtheworld.bean.FamilyBean;
import com.example.aroundtheworld.bean.FamilyMemberBean;
import com.example.aroundtheworld.exception.CommandErrorException;
import com.example.aroundtheworld.exception.PhoneFormatException;
import com.example.aroundtheworld.viewcli.AddFamilyViewCLI;

import java.util.ArrayList;
import java.util.List;

public class AddFamilyCLIController {

    private AddFamilyViewCLI addFamilyViewCLI;
    private static final String NEW_MEMBER = "1";
    private static final String ADD_FAMILY = "2";
    private FamilyBean familyBean;
    private List<FamilyMemberBean> familyMemberList = new ArrayList<>();

    public void start() {
        this.addFamilyViewCLI = new AddFamilyViewCLI(this);
        this.addFamilyViewCLI.run();
    }

    public void createFamily(String name, String phone, String address, String city, String house, int food) throws PhoneFormatException {
        String email = name.toLowerCase();
        email = email.concat("@gmail.com");
        if(house.equals("single")){
            house = "Single";
        }else{
            house = "Shared";
        }
        this.familyBean = new FamilyBean(name, city, address, phone, email);
        this.familyBean.setHouse(house);
        switch (food){
            case 0 -> this.familyBean.setFamilyFood(1,0);
            case 1 -> this.familyBean.setFamilyFood(0,1);
            default -> this.familyBean.setFamilyFood(0,0);
        }
    }

    public void setFamilyHobbies(int travels, int sport, int nature, int books, int film, int videoGames, int cooking) {
        this.familyBean.setFamilyHobbies(travels,sport,books,nature,film,videoGames,cooking);
    }

    public void setAnimals(int dog, int cat, int bird, int rodent, int reptile, int other) {
        List<AnimalBean> animalBeanList = new ArrayList<>();
        if (dog != 0){
            AnimalBean animalBean = new AnimalBean("Dog", dog);
            animalBeanList.add(animalBean);
        }
        if (cat != 0){
            AnimalBean animalBean = new AnimalBean("Cat", cat);
            animalBeanList.add(animalBean);
        }
        if (bird != 0){
            AnimalBean animalBean = new AnimalBean("Bird", bird);
            animalBeanList.add(animalBean);
        }
        if (rodent != 0){
            AnimalBean animalBean = new AnimalBean("Rodent", rodent);
            animalBeanList.add(animalBean);
        }
        if (reptile != 0){
            AnimalBean animalBean = new AnimalBean("Reptile", reptile);
            animalBeanList.add(animalBean);
        }
        if (other != 0){
            AnimalBean animalBean = new AnimalBean("Other", other);
            animalBeanList.add(animalBean);
        }

        this.familyBean.setAnimals(animalBeanList);
    }

    public int executeNewMember(String nextLine) throws CommandErrorException {
        int choice = 0;
        switch(nextLine){
            case  NEW_MEMBER -> choice = 1;
            case  ADD_FAMILY -> choice = 2;
            default -> throw new CommandErrorException();
        }
        return choice;
    }

    public void addMember(String nameM, String ageM, String parenthoodM) {
        FamilyMemberBean familyMemberBean = new FamilyMemberBean(nameM, Integer.parseInt(ageM), parenthoodM);
        familyMemberList.add(familyMemberBean);
    }

    public void addFamily() {
        this.familyBean.setMembers(familyMemberList);
        AddFamilyController addFamilyController = new AddFamilyController();
        addFamilyController.createFamily(this.familyBean);
    }
}
