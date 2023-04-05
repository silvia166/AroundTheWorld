package com.example.aroundtheworld.dao;

import com.example.aroundtheworld.connection.ConnectionDB;
import com.example.aroundtheworld.dao.queries.SimpleQueries;
import com.example.aroundtheworld.exception.ConnectionDbException;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.model.Animal;
import com.example.aroundtheworld.model.Family;
import com.example.aroundtheworld.model.FamilyMember;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FamilyDAO {

    private static final String ID = "idFamily";
    private static final String NAME = "name";
    private static final String CITY = "city";
    private static final String ADDRESS = "address";
    private static final String HOUSE = "house";
    private static final String PHONE = "phoneNumber";
    private static final String VEGETARIAN = "vegetarian";
    private static final String VEGAN = "vegan";
    private static final String GLUTENFREE = "glutenFree";
    private static final String TRAVELS = "travels";
    private static final String SPORT = "sport";
    private static final String BOOKS = "books";
    private static final String NATURE = "nature";
    private static final String FILM = "film";
    private static final String VIDEOGAMES = "videoGames";
    private static final String COOKING = "cooking";
    private static final String PHOTO = "photo";

    private FamilyDAO() {}


    public static Family retrieveFamily(String username) throws NotFoundException {
        Statement stmt;
        Family family = null;
        List<FamilyMember> familyMembers = null;
        List<Animal> animals = null;
        List<String> food = new ArrayList<>();
        List<String> hobbies = new ArrayList<>();

        try{
            stmt = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.retrieveFamily(stmt, username);

            if(!resultSet.first()) {
                throw new NotFoundException("No family found with username: " + username);
            }

            resultSet.first();
            do{
                int familyId = resultSet.getInt(ID);
                String phoneNumber = resultSet.getString(PHONE);
                String name = resultSet.getString(NAME);
                String city = resultSet.getString(CITY);
                String address = resultSet.getString(ADDRESS);
                String house = resultSet.getString(HOUSE);
                int vegetarian = resultSet.getInt(VEGETARIAN);
                int vegan = resultSet.getInt(VEGAN);
                int glutenFree = resultSet.getInt(GLUTENFREE);
                int travels = resultSet.getInt(TRAVELS);
                int books = resultSet.getInt(BOOKS);
                int film = resultSet.getInt(FILM);
                int nature = resultSet.getInt(NATURE);
                int videoGames = resultSet.getInt(VIDEOGAMES);
                int cooking = resultSet.getInt(COOKING);
                int sport = resultSet.getInt(SPORT);
                String photo = resultSet.getString(PHOTO);

                familyMembers = FamilyMemberDAO.retrieveMembers(familyId);
                animals = AnimalDAO.retrieveAnimal(familyId);

                food = checkFood(vegetarian, vegan, glutenFree);
                hobbies = checkHobbies(travels, books, film, videoGames, nature, cooking, sport);


                family = new Family(familyId,phoneNumber,name,city,address,house, username);
                family.setAnimals(animals);
                family.setMembers(familyMembers);
                family.setFood(food);
                family.setHoobies(hobbies);
                family.setImgSrc(photo);

            } while(resultSet.next());

            resultSet.close();

        }catch(SQLException | ConnectionDbException e){
            e.printStackTrace();
        }

        return family;

    }

    private static List<String> checkHobbies(int travels, int books, int film, int videoGames, int nature, int cooking, int sport) {

        List<String> hobbies = new ArrayList<>();

        if (travels == 1){
            hobbies.add("Travels");
        }
        if (books == 1){
            hobbies.add("Books");
        }
        if (film == 1){
            hobbies.add("Film");
        }
        if (videoGames == 1){
            hobbies.add("Video Games");
        }
        if (nature == 1){
            hobbies.add("Nature");
        }
        if (cooking == 1){
            hobbies.add("Cooking");
        }
        if (sport == 1){
            hobbies.add("Sport");
        }
        return hobbies;
    }

    private static List<String> checkFood(int vegetarian, int vegan, int glutenFree) {
        int noPref = 0;
        List<String> food = new ArrayList<>();

        if (vegetarian == 1) {
            food.add("Vegetarian");
            noPref = 1;
        }
        if (vegan == 1) {
            food.add("Vegan");
            noPref = 1;
        }
        if (glutenFree == 1) {
            food.add("Gluten Free");
            noPref = 1;
        }
        if (noPref == 0) {
            food.add("No Preferences");
        }
        return food;
    }
}

