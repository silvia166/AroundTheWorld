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

    public String getCityBean() {
        return city;
    }

    public String getArrivalBean() {
        return arrival;
    }

    public String getDepartureBean() {
        return departure;
    }

    public int getSiblingsBean() {
        return siblings;
    }

    public int getAnimalsBean() {
        return animals;
    }

    public FamilyPreferences getFamilyPreferencesBean() {
        return familyPreferences;
    }

    public int getIdStudentBean() {
        return idStudent;
    }

    public int getIdFamilyBean() {
        return idFamily;
    }

    public float getCompatibilityBean() {
        return compatibility;
    }
}
