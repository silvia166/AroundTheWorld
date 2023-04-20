package com.example.aroundtheworld.bean;

import com.example.aroundtheworld.model.FamilyPreferences;


public class FamilyRequestBean {

    private String city;
    private String arrival;
    private String departure;
    private int siblings;
    private int animals;
    private FamilyPreferences familyPreferences;
    private int idStudent;
    private int idFamily;
    private float compatibility;

    public FamilyRequestBean(String city, String arrival, String departure, int siblings, int animals, int idStudent){
        this.city = city;
        this.arrival = arrival;
        this.departure = departure;
        this.siblings = siblings;
        this.animals = animals;
        this.idStudent = idStudent;
    }


    public void setCompatibility(float compatibility) {
        this.compatibility = compatibility;
    }

    public void setFamilyPreferences(FamilyPreferences familyPreferences) {
        this.familyPreferences = familyPreferences;
    }

    public void setIdFamily(int idFamily) {
        this.idFamily = idFamily;
    }

    public String getCity() {
        return city;
    }

    public String getArrival() {
        return arrival;
    }

    public String getDeparture() {
        return departure;
    }

    public int getSiblings() {
        return siblings;
    }

    public int getAnimals() {
        return animals;
    }

    public FamilyPreferences getFamilyPreferences() {
        return familyPreferences;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public int getIdFamily() {
        return idFamily;
    }

    public float getCompatibility() {
        return compatibility;
    }
}
