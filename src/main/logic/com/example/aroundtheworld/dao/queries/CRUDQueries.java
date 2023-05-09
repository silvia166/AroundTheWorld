package com.example.aroundtheworld.dao.queries;

import com.example.aroundtheworld.model.FamilyPreferences;
import com.example.aroundtheworld.model.FamilyRequest;

import java.sql.SQLException;
import java.sql.Statement;

public class CRUDQueries {

    private CRUDQueries() {
    }

    public static void insertStudent(Statement stmt, String name, String surname, String birth, String nationality, String phoneNumber, String email) throws SQLException {
        String insertStatement = String.format("INSERT INTO student (name, surname, nationality, birth, email, phoneNumber) VALUES ('%s', '%s', '%s', '%s', '%s', '%s')", name, surname, nationality, birth, email, phoneNumber);
        stmt.executeUpdate(insertStatement);
    }

    public static void insertUser(Statement stmt, String email, String password, String role) throws SQLException {
        String insertStatement = String.format("INSERT INTO user (username, password, role) VALUES ('%s', '%s', '%s')", email, password, role);
        stmt.executeUpdate(insertStatement);
    }

    public static void insertFamily(Statement stmt, String name, String phone, String city, String address, String email, String imgSrc) throws SQLException {
        String insertStatement = String.format("INSERT INTO family (name, phoneNumber, city, address, photo, email) VALUES ('%s', '%s', '%s', '%s', '%s', '%s')", name, phone, city, address, imgSrc, email);
        stmt.executeUpdate(insertStatement);
    }

    public static void insertPreferences(Statement stmt, int id, FamilyPreferences preferences) throws SQLException {
        String insertStatement = String.format("INSERT INTO familyPreferences (idFamily, house, vegetarian, vegan, travels, sport, books, nature, film, videogames, cooking) VALUES (%d, '%s', %d, %d, %d, %d, %d, %d, %d, %d, %d)", id, preferences.getHouse(), preferences.getVegetarian(), preferences.getVegan(), preferences.getTravels(), preferences.getSport(), preferences.getBooks(), preferences.getNature(), preferences.getFilm(), preferences.getVideoGames(), preferences.getCooking());
        stmt.executeUpdate(insertStatement);
    }
    public static void insertAnimal(Statement stmt, int id, String type, int quantity) throws SQLException {
        String insertStatement = String.format("INSERT INTO familyAnimal (idFamily, type, quantity) VALUES (%d, '%s', %d)", id, type, quantity);
        stmt.executeUpdate(insertStatement);
    }

    public static void insertMember(Statement stmt, int id, String name, String birth, String parenthood) throws SQLException {
        String insertStatement = String.format("INSERT INTO familyMember (idFamily, name, birth, parenthood) VALUES (%d, '%s', '%s', '%s')", id, name, birth, parenthood);
        stmt.executeUpdate(insertStatement);
    }

    public static void insertRequest(Statement stmt, FamilyRequest familyRequest) throws SQLException {
        String insertStatement = String.format("INSERT INTO familyRequest (arrival, departure, city, house, status, siblings, animals, vegetarian, vegan, travels, books, sport, nature, film, videogames, cooking, idStudent, idFamily, compatibility) VALUES ('%s', '%s', '%s', '%s', %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, '%s')", familyRequest.getArrival(), familyRequest.getDeparture(), familyRequest.getCity(), familyRequest.getFamilyPreferences().getHouse(), 0, familyRequest.getSiblings(), familyRequest.getAnimals(), familyRequest.getFamilyPreferences().getVegetarian(), familyRequest.getFamilyPreferences().getVegan(), familyRequest.getFamilyPreferences().getTravels(), familyRequest.getFamilyPreferences().getBooks(), familyRequest.getFamilyPreferences().getSport(), familyRequest.getFamilyPreferences().getNature(), familyRequest.getFamilyPreferences().getFilm(), familyRequest.getFamilyPreferences().getVideoGames(), familyRequest.getFamilyPreferences().getCooking(), familyRequest.getIdStudent(), familyRequest.getIdFamily(), familyRequest.getCompatibility());
        stmt.executeUpdate(insertStatement);
    }

    public static void updateStatusRequest(Statement stmt, int status, int id) throws SQLException {
        String insertStatement = String.format("UPDATE familyRequest SET status = %d WHERE idRequest = %d", status,id);
        stmt.executeUpdate(insertStatement);
    }

    public static void deleteRequest(Statement stmt, int id) throws SQLException{
        String insertStatement = String.format("DELETE FROM familyRequest WHERE idRequest = %d", id);
        stmt.executeUpdate(insertStatement);
    }
}
