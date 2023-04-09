package com.example.aroundtheworld.model;

public class FamilyPreferences {

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
}
