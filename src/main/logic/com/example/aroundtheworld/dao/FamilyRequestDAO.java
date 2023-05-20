package com.example.aroundtheworld.dao;

import com.example.aroundtheworld.connection.ConnectionDB;
import com.example.aroundtheworld.dao.queries.CRUDQueries;
import com.example.aroundtheworld.dao.queries.SimpleQueries;
import com.example.aroundtheworld.exception.DuplicateRequestException;
import com.example.aroundtheworld.model.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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

        Connection connection;

        try {
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.selectDistinctRequest(connection, familyRequest.getIdStudent(), familyRequest.getArrival(), familyRequest.getDeparture());
            if(!resultSet.first()){
                CRUDQueries.insertRequest(connection, familyRequest);
            } else {
                throw new DuplicateRequestException();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<FamilyRequest> retrieveRequests(int id){
        Connection connection;
        List<FamilyRequest> familyRequestsList = new ArrayList<>();
        FamilyRequest familyRequest;

        try {
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.retrieveFamilyRequests(connection, id);

            if(resultSet.first()) {
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

                    String studentName = StudentDAO.getNameById(studentId);

                    familyRequest = new FamilyRequest(city, arrival.toString(), departure.toString(), siblings, animals, studentId, status);

                    familyRequest.setCompatibility(Float.parseFloat(compatibility));
                    familyRequest.setIdFamily(id);
                    familyRequest.setId(requestId);
                    familyRequest.setStudentName(studentName);

                    FamilyPreferences preferences = new FamilyPreferences();
                    preferences.setHouse(house);
                    preferences.setHobbies(travels, sport, books, nature, film, videoGames, cooking);
                    preferences.setFood(vegetarian, vegan);

                    familyRequest.setFamilyPreferences(preferences);

                    familyRequestsList.add(familyRequest);

                } while (resultSet.next());
            }

            resultSet.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return familyRequestsList;
    }

    public static void updateStatus(int status, int id) {
        Connection connection;
        try {
            connection = ConnectionDB.getConnection();
            CRUDQueries.updateStatusRequest(connection, status, id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteRequest(int id) {
        Connection connection;
        try {
            connection = ConnectionDB.getConnection();
            CRUDQueries.deleteRequest(connection, id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<FamilyRequest> retrieveStudentRequest(int idStudent) {
        Connection connection;
        List<FamilyRequest> familyRequestList = new ArrayList<>();
        FamilyRequest familyRequest;

        try {
            connection = ConnectionDB.getConnection();
            ResultSet resultSet = SimpleQueries.retrieveStudentFamilyRequests(connection, idStudent);

            if(resultSet.first()) {
                resultSet.first();
                do {
                    int requestId = resultSet.getInt(ID);
                    Date arrival = resultSet.getDate(ARRIVAL);
                    Date departure = resultSet.getDate(DEPARTURE);
                    int status = resultSet.getInt(STATUS);
                    int idFamily = resultSet.getInt(IDFAM);
                    String city = resultSet.getString(CITY);

                    familyRequest = new FamilyRequest(city, arrival.toString(), departure.toString(), idStudent, idFamily, status);
                    familyRequest.setId(requestId);

                    familyRequestList.add(familyRequest);

                } while (resultSet.next());
            }

            resultSet.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return familyRequestList;
    }

    public static List<FamilyRequest> retrieveTravelsByFamily(int idFamily) {
        Connection connection;
        List<FamilyRequest> familyRequestList = new ArrayList<>();
        FamilyRequest familyRequest;

        try {
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.retrieveBookingsByFamily(connection, idFamily);

            if(resultSet.first()) {
                resultSet.first();
                do {
                    int requestId = resultSet.getInt(ID);
                    Date arrival = resultSet.getDate(ARRIVAL);
                    Date departure = resultSet.getDate(DEPARTURE);
                    int idStudent = resultSet.getInt(IDSTUD);
                    int rate = resultSet.getInt(RATE);

                    familyRequest = new FamilyRequest(null, arrival.toString(), departure.toString(), idStudent, idFamily, 2);
                    familyRequest.setId(requestId);
                    familyRequest.setRate(rate);

                    familyRequestList.add(familyRequest);

                } while (resultSet.next());
            }

            resultSet.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return familyRequestList;
    }

    public static List<FamilyRequest> retrieveTravelsByStudent(int idStudent) {
        Connection connection;
        List<FamilyRequest> familyRequestList = new ArrayList<>();
        FamilyRequest familyRequest;

        try {
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.retrieveFamilyBookingsByStudent(connection, idStudent);

            if(resultSet.first()) {
                resultSet.first();
                do {
                    int requestId = resultSet.getInt(ID);
                    Date arrival = resultSet.getDate(ARRIVAL);
                    Date departure = resultSet.getDate(DEPARTURE);
                    int idFamily = resultSet.getInt(IDFAM);
                    int rate = resultSet.getInt(RATE);
                    String city = resultSet.getString(CITY);

                    familyRequest = new FamilyRequest(city, arrival.toString(), departure.toString(), idStudent, idFamily, 2);
                    familyRequest.setId(requestId);
                    familyRequest.setRate(rate);

                    familyRequestList.add(familyRequest);

                } while (resultSet.next());
            }

            resultSet.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return familyRequestList;
    }

    public static void updateRate(int rate, int idRequest) {
        Connection connection;
        try {
            connection = ConnectionDB.getConnection();
            CRUDQueries.updateRateRequest(connection, rate, idRequest);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
