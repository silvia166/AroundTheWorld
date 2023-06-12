package com.example.aroundtheworld.model;

import java.util.ArrayList;
import java.util.List;

public class Family extends GenericUser {
    private String name;
    private String imgSrc;
    private String city;
    private String address;
    private List<Animal> animals;
    private List<FamilyMember> members;
    private FamilyPreferences familyPreferences;
    public Family() {}
    public Family(int id, String phoneNumber, String name, String city, String address, String email) {
        super(id, phoneNumber, email);
        this.name = name;
        this.city = city;
        this.address = address;
    }

    public Family(int id, String phoneNumber, String name, String city, String address) {
        super(id, phoneNumber, null);
        this.name = name;
        this.city = city;
        this.address = address;
    }

    //per la relazione di composizione devo effettuare una clonazione delle istanze di FamilyPrefrences, Animal e FamilyMember passate come parametri
    public void setPreferences(FamilyPreferences familyPreferences) {
        this.familyPreferences = new FamilyPreferences();
        this.familyPreferences.setFood(familyPreferences.getVegetarian(),familyPreferences.getVegan());
        this.familyPreferences.setHouse(familyPreferences.getHouse());
        this.familyPreferences.setHobbies(familyPreferences.getTravels(),familyPreferences.getSport(),familyPreferences.getBooks(),familyPreferences.getNature(),familyPreferences.getFilm(),familyPreferences.getVideoGames(),familyPreferences.getCooking());
    }

    public void setAnimals(List<Animal> animals) {
        Animal newAnimal;
        this.animals = new ArrayList<>();
        for (Animal animal : animals){
            newAnimal = new Animal(animal.getType(),animal.getQuantity());
            this.animals.add(newAnimal);
        }
    }
    public void setMembers(List<FamilyMember> members) {
        FamilyMember familyMember;
        this.members = new ArrayList<>();
        for (FamilyMember member : members){
            familyMember = new FamilyMember(member.getName(), member.getAge(), member.getParenthood());
            this.members.add(familyMember);
        }
    }
    public void setName(String name) {
        this.name = name;
    }
    public FamilyPreferences getPreferences() {
        return familyPreferences;
    }
    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }
    public String getName() {
        return name;
    }
    public String getImgSrc() {
        return imgSrc;
    }
    public String getCity() {
        return city;
    }
    public String getAddress() {
        return address;
    }
    public List<Animal> getAnimals() {
        return animals;
    }
    public List<FamilyMember> getMembers() {
        return members;
    }

}
