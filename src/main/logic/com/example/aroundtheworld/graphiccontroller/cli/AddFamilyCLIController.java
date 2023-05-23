package com.example.aroundtheworld.graphiccontroller.cli;
import com.example.aroundtheworld.bean.AnimalBean;
import com.example.aroundtheworld.bean.FamilyBean;
import com.example.aroundtheworld.bean.FamilyMemberBean;
import com.example.aroundtheworld.exception.PhoneFormatException;
import com.example.aroundtheworld.viewcli.AddFamilyViewCLI;

import java.util.ArrayList;
import java.util.List;

public class AddFamilyCLIController {

    private AddFamilyViewCLI addFamilyViewCLI;
    private FamilyBean familyBean;
    private List<FamilyMemberBean> familyMemberList;

    public void start() {
        this.addFamilyViewCLI = new AddFamilyViewCLI(this);
        this.addFamilyViewCLI.run();
    }

    public void createFamily(String name, String phone, String address, String city, String house) throws PhoneFormatException {
        String email = name.toLowerCase();
        email = email.concat("@gmail.com");
        this.familyBean = new FamilyBean(name, city, address, phone, email);

    }

    public void setFamilyHobbies(int travels, int sport, int nature, int books, int film, int videoGames, int cooking, int food) {
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

}
