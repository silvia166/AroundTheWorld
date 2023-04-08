package com.example.aroundtheworld.model;

public class FamilyPreferences {

    private int vegetarian;
    private int vegan;
    private int glutenFree;
    private int travels;
    private int sport;
    private int books;
    private int nature;
    private int film;
    private int videoGames;
    private int cooking;

    public FamilyPreferences() {
    }

    public void setFood(int vegetarian, int vegan, int glutenFree) {
        this.vegetarian = vegetarian;
        this.vegan = vegan;
        this.glutenFree = glutenFree;
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

    public int getVegetarian() {
        return vegetarian;
    }

    public int getVegan() {
        return vegan;
    }

    public int getGlutenFree() {
        return glutenFree;
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
