package com.example.aroundtheworld.bean;

import com.example.aroundtheworld.model.Animal;
import com.example.aroundtheworld.model.FamilyMember;

import java.util.List;

public class FamilyBean {
    private String name;
    private String imgSrc;
    private String city;
    private String address;
    private String house;
    private List<Animal> animals;
    private List<FamilyMember> members;
    private List<String> food;
    private List<String> hoobies;
    private int id;
    private String phone;

    private String email;

    public FamilyBean(String name, String imgSrc, String city, String address, String house, List<Animal> animals, List<FamilyMember> members, List<String> food, List<String> hoobies, int id, String phone, String email) {
        this.name = name;
        this.imgSrc = imgSrc;
        this.city = city;
        this.address = address;
        this.house = house;
        this.animals = animals;
        this.members = members;
        this.food = food;
        this.hoobies = hoobies;
        this.id = id;
        this.phone = phone;
        this.email = email;
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

    public int getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

}
