package com.example.aroundtheworld.dao;

import com.example.aroundtheworld.connection.ConnectionDB;
import com.example.aroundtheworld.dao.queries.CRUDQueries;
import com.example.aroundtheworld.dao.queries.SimpleQueries;
import com.example.aroundtheworld.exception.ConnectionDbException;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.model.Animal;
import com.example.aroundtheworld.model.Family;
import com.example.aroundtheworld.model.FamilyMember;
import com.example.aroundtheworld.model.FamilyPreferences;

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
    private static final String PHONE = "phoneNumber";
    private static final String PHOTO = "photo";

    private FamilyDAO() {}


    public static Family retrieveFamily(String username) throws NotFoundException {
        Statement stmt;
        Family family = null;
        List<FamilyMember> familyMembers = null;
        List<Animal> animals = null;
        List<String> food = new ArrayList<>();
        List<String> hobbies = new ArrayList<>();
        FamilyPreferences preferences = null;

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
                String photo = resultSet.getString(PHOTO);

                familyMembers = FamilyMemberDAO.retrieveMembers(familyId);
                animals = AnimalDAO.retrieveAnimal(familyId);
                preferences = FamilyPreferencesDAO.retrievePreferences(familyId);

                food = checkFood(preferences.getVegetarian(), preferences.getVegan());
                hobbies = checkHobbies(preferences.getTravels(), preferences.getBooks(), preferences.getFilm(), preferences.getVideoGames(), preferences.getNature(), preferences.getCooking(), preferences.getSport());


                family = new Family(familyId, phoneNumber, name, city, address, preferences.getHouse(), username);
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

    private static List<String> checkFood(int vegetarian, int vegan) {
        int noPref = 0;
        List<String> food = new ArrayList<>();

        if (vegetarian == 1) {
            food.add("Vegetarian");
            noPref = 1;
        }
        else if (vegan == 1) {
            food.add("Vegan");
            noPref = 1;
        }
        else {
            food.add("No Preferences");
        }
        return food;
    }

    public static void addFamily(String name, String phone, String city, String address, String imgSrc, String email) {

        Statement stmt;

        try{
            stmt = ConnectionDB.getConnection();

            CRUDQueries.insertUser(stmt, email, "123", "family");
            CRUDQueries.insertFamily(stmt, name, phone, city, address, email, imgSrc);

        } catch(SQLException | ConnectionDbException e) {
            e.printStackTrace();
        }

    }

    public static int retrieveFamilyID(String name) {

        Statement stmt;
        int id = 0;

        try{
            stmt = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.retrieveFamilyID(stmt, name);

            if (!resultSet.first()){
                throw new NotFoundException("No family found with name"+ name);
            }

            resultSet.first();

            id = resultSet.getInt(1);

            resultSet.close();


        } catch(SQLException | ConnectionDbException | NotFoundException e) {
            e.printStackTrace();
        }

        return id;
    }

}

