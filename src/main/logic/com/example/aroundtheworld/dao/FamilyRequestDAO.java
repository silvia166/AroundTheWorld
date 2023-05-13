package com.example.aroundtheworld.dao;

import com.example.aroundtheworld.connection.ConnectionDB;
import com.example.aroundtheworld.dao.queries.CRUDQueries;
import com.example.aroundtheworld.dao.queries.SimpleQueries;
import com.example.aroundtheworld.engineering.ShowExceptionSupport;
import com.example.aroundtheworld.exception.ConnectionDbException;
import com.example.aroundtheworld.exception.DuplicateRequestException;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.model.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FamilyRequestDAO {

    private static final String ID = "idRequest";
    private static final String IDSTUD = "idStudent";
    private static final String IDFAM = "idFamily";
    private static final String ARRIVAL = "arrival";
    private static final String DEPARTURE = "departure";
    private static final String RATE = "rate";
    private static final String COMPATIBILITY = "compatibility";
    private static final String CITY = "city";
    private static final String HOUSE = "house";
    private static final String STATUS = "status";
    private static final String SIBLINGS = "siblings";
    private static final String ANIMALS = "animals";
    private static final String VEGETARIAN = "vegetarian";
    private static final String VEGAN = "vegan";
    private static final String TRAVELS = "travels";
    private static final String SPORT = "sport";
    private static final String BOOKS = "books";
    private static final String NATURE = "nature";
    private static final String FILM = "film";
    private static final String VIDEOGAMES = "videoGames";
    private static final String COOKING = "cooking";


    private FamilyRequestDAO() {
    }

    public static void newRequest(FamilyRequest familyRequest) throws DuplicateRequestException{

        Statement stmt;

        try {
            stmt = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.selectDistinctRequest(stmt, familyRequest.getIdStudent(), familyRequest.getArrival(), familyRequest.getDeparture());
            if(!resultSet.first()){
                CRUDQueries.insertRequest(stmt, familyRequest);
            } else {
                throw new DuplicateRequestException();
            }

        } catch (SQLException | ConnectionDbException e) {
            e.printStackTrace();
        }
    }

    public static List<FamilyRequest> retrieveRequests(int id) throws NotFoundException{
        Statement stmt;
        List<FamilyRequest> familyRequestsList = new ArrayList<>();
        FamilyRequest familyRequest;

        try {
            stmt = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.retrieveFamilyRequests(stmt, id);

            if (!resultSet.first()) {
                throw new NotFoundException("No request found for family with id: " + id);
            }

            resultSet.first();
            do {
                int requestId = resultSet.getInt(ID);
                int studentId = resultSet.getInt(IDSTUD);
                String city = resultSet.getString(CITY);
                Date arrival = resultSet.getDate(ARRIVAL);
                Date departure = resultSet.getDate(DEPARTURE);
                String house = resultSet.getString(HOUSE);
                int status = resultSet.getInt(STATUS);
                int siblings = resultSet.getInt(SIBLINGS);
                int animals = resultSet.getInt(ANIMALS);
                int vegetarian = resultSet.getInt(VEGETARIAN);
                int vegan = resultSet.getInt(VEGAN);
                int travels = resultSet.getInt(TRAVELS);
                int books = resultSet.getInt(BOOKS);
                int sport = resultSet.getInt(SPORT);
                int nature = resultSet.getInt(NATURE);
                int film = resultSet.getInt(FILM);
                int videoGames = resultSet.getInt(VIDEOGAMES);
                int cooking = resultSet.getInt(COOKING);
                String compatibility = resultSet.getString(COMPATIBILITY);

                familyRequest = new FamilyRequest(city,arrival.toString(),departure.toString(),siblings,animals,studentId,id);

                familyRequest.setCompatibility(Float.parseFloat(compatibility));
                familyRequest.setStatus(status);
                familyRequest.setId(requestId);

                FamilyPreferences preferences = new FamilyPreferences();
                preferences.setHouse(house);
                preferences.setHobbies(travels,sport,books,nature,film,videoGames,cooking);
                preferences.setFood(vegetarian,vegan);

                familyRequest.setFamilyPreferences(preferences);

                familyRequestsList.add(familyRequest);

            } while (resultSet.next());

            resultSet.close();

        } catch (SQLException | ConnectionDbException e) {
            e.printStackTrace();
        }

        return familyRequestsList;
    }

    public static void updateStatus(int status, int id) {
        Statement stmt;

        try {
            stmt = ConnectionDB.getConnection();

            if(status == 1) {
                CRUDQueries.updateStatusRequest(stmt, status, id);
            }else if(status == 2){
                CRUDQueries.deleteRequest(stmt, id);
            }

        } catch (SQLException | ConnectionDbException e) {
            e.printStackTrace();
        }
    }
}
