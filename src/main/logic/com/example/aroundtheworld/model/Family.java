package com.example.aroundtheworld.model;

import java.util.List;

public class Family extends GenericUser {
    private String name;
    private String imgSrc;
    private String city;
    private String address;
    private String house;
    private List<Animal> animals;
    private List<FamilyMember> members;
    private List<String> food;
    private List<String> hoobies;


    public Family(int id, String phoneNumber, String name, String city, String address, String house, String email) {
        super(id, 2, phoneNumber, email);
        this.name = name;
        this.city = city;
        this.address = address;
        this.house = house;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public void setMembers(List<FamilyMember> members) {
        this.members = members;
    }

    public void setFood(List<String> food) {
        this.food = food;
    }

    public void setHoobies(List<String> hoobies) {
        this.hoobies = hoobies;
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

    public String getHouse() {
        return house;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public List<FamilyMember> getMembers() {
        return members;
    }

    public List<String> getFood() {
        return food;
    }

    public List<String> getHoobies() {
        return hoobies;
    }

}
