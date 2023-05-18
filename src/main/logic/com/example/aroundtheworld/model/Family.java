package com.example.aroundtheworld.model;

import java.util.List;

public class Family extends GenericUser {
    private String name;
    private String imgSrc;
    private String city;
    private String address;
    private List<Animal> animals;
    private List<FamilyMember> members;
    private FamilyPreferences preferences;

    public Family(int id, String phoneNumber, String name, String city, String address, String email) {
        super(id, 2, phoneNumber, email);
        this.name = name;
        this.city = city;
        this.address = address;
    }

    public Family(String name, String imgSrc) {
        super();
        this.name = name;
        this.imgSrc = imgSrc;
    }

    public Family(int id, String phoneNumber, String name, String city, String address) {
        super(id, 2, phoneNumber, null);
        this.name = name;
        this.city = city;
        this.address = address;
    }

    public FamilyPreferences getPreferences() {
        return preferences;
    }

    public void setPreferences(FamilyPreferences preferences) {
        this.preferences = preferences;
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
