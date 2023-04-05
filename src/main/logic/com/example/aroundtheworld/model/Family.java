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


    public Family(int id, int type, String phoneNumber, String name, String imgSrc, String city, String address, String house, List<Animal> animals, List<FamilyMember> members, List<String> food, List<String> hoobies, String email) {
        super(id, type, phoneNumber, email);
        this.name = name;
        this.imgSrc = imgSrc;
        this.city = city;
        this.address = address;
        this.house = house;
        this.animals = animals;
        this.members = members;
        this.food = food;
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
