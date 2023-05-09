package com.example.aroundtheworld.bean;

import com.example.aroundtheworld.engineering.observer.Observer;
import com.example.aroundtheworld.engineering.observer.Subject;
import com.example.aroundtheworld.model.Family;
import com.example.aroundtheworld.model.FamilyRequest;

import java.util.ArrayList;
import java.util.List;

public class FamilyRequestBean extends Subject {

    private int id;
    private String city;
    private String arrival;
    private String departure;
    private int siblings;
    private int animals;
    private int idStudent;

    private String studentName;
    private int idFamily;
    private float compatibility;

    private int status;

    //preferences
    private int vegetarian;
    private int vegan;
    private int travels;
    private int sport;
    private int books;
    private int nature;
    private int film;
    private int videoGames;
    private int cooking;
    private String house;

    public FamilyRequestBean(String city, String arrival, String departure, int siblings, int animals, int idStudent){
        this.city = city;
        this.arrival = arrival;
        this.departure = departure;
        this.siblings = siblings;
        this.animals = animals;
        this.idStudent = idStudent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String name) {
        this.studentName = name;
    }

    public void setCompatibility(float compatibility) {
        this.compatibility = compatibility;
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

    public int getIdStudentBean() {
        return idStudent;
    }

    public int getIdFamilyBean() {
        return idFamily;
    }

    public float getCompatibilityBean() {
        return compatibility;
    }

    public void setFood(int vegetarian, int vegan) {
        this.vegetarian = vegetarian;
        this.vegan = vegan;
    }

    public void setHobbies(int travels, int sport, int books, int nature, int film, int videoGames, int cooking) {
        this.travels = travels;
        this.sport = sport;
        this.books = books;
        this.nature = nature;
        this.film = film;
        this.videoGames = videoGames;
        this.cooking = cooking;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public int getVegetarian() {
        return vegetarian;
    }

    public int getVegan() {
        return vegan;
    }

    public int getTravels() {
        return travels;
    }

    public int getSport() {
        return sport;
    }

    public int getBooks() {
        return books;
    }

    public int getNature() {
        return nature;
    }

    public int getFilm() {
        return film;
    }

    public int getVideoGames() {
        return videoGames;
    }

    public int getCooking() {
        return cooking;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
