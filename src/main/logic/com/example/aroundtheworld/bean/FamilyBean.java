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

    public FamilyBean(String name, String city, String address, String house, int id, String phone, String email) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.house = house;
        this.id = id;
        this.phone = phone;
        this.email = email;
    }

    public FamilyBean(String name, String city, String address, String house, String phone, String email) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.house = house;
        this.phone = phone;
        this.email = email;
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
