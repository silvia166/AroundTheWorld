package com.example.aroundtheworld.model;

public class FamilyRequest {

    private String city;
    private String arrival;
    private String departure;
    private int siblings;
    private int animals;
    private FamilyPreferences familyPreferences;
    private int idStudent;
    private int idFamily;
    private float compatibility;
    private int rate;

    public FamilyRequest(String city, String arrival, String departure, int siblings, int animals, int idStudent, int idFamily) {
        this.city = city;
        this.arrival = arrival;
        this.departure = departure;
        this.siblings = siblings;
        this.animals = animals;
        this.idStudent = idStudent;
        this.idFamily = idFamily;
    }

    public void setCompatibility(float compatibility) {
        this.compatibility = compatibility;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public void setFamilyPreferences(FamilyPreferences familyPreferences) {
        this.familyPreferences = familyPreferences;
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
