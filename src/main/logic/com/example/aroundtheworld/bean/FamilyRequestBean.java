package com.example.aroundtheworld.bean;

import com.example.aroundtheworld.exception.MessageException;
import com.example.aroundtheworld.model.FamilyPreferences;

import java.time.LocalDate;

public class FamilyRequestBean {

    private String city;
    private LocalDate arrival;
    private LocalDate departure;
    private int siblings;
    private int animals;
    private FamilyPreferences familyPreferences;
    private int idStudent;
    private int idFamily;
    private float compatibility;

    public FamilyRequestBean(String city, LocalDate arrival, LocalDate departure, int siblings, int animals, int idStudent) throws MessageException {
        this.city = city;
        this.setTravelDays(arrival,departure);
        this.siblings = siblings;
        this.animals = animals;
        this.idStudent = idStudent;
    }

    private void setTravelDays(LocalDate arrival, LocalDate departure) throws MessageException {
        LocalDate currentDate = LocalDate.now();
        if(arrival.isBefore(currentDate.plusDays(7))){
            throw new MessageException("Arrival must be at least \n 7 days from today!");
        }
        if(departure.isBefore(arrival.plusDays(7))){
            throw new MessageException("Departure must be at least \n 7 days from arrival!");
        }
        this.arrival = arrival;
        this.departure = departure;
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

    public LocalDate getArrival() {
        return arrival;
    }

    public LocalDate getDeparture() {
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
