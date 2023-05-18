package com.example.aroundtheworld.dao.queries;

import com.example.aroundtheworld.model.FamilyPreferences;
import com.example.aroundtheworld.model.FamilyRequest;
import com.example.aroundtheworld.model.ResidenceRequest;

import java.sql.*;

public class CRUDQueries {

    private static PreparedStatement preparedStatement = null;
    private CRUDQueries() {
    }

    public static int insertStudent(Connection connection, String name, String surname, String birth, String nationality, String phoneNumber, String email) throws SQLException {
        String insertStatement = "INSERT INTO student (name, surname, nationality, birth, email, phoneNumber) VALUES (?, ?, ?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(insertStatement, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1,name);
        preparedStatement.setString(2,surname);
        preparedStatement.setString(3,nationality);
        preparedStatement.setString(4,birth);
        preparedStatement.setString(5,email);
        preparedStatement.setString(6,phoneNumber);
        return preparedStatement.executeUpdate();
    }

    public static int insertUser(Connection connection, String email, String password, String role) throws SQLException {
        String insertStatement = "INSERT INTO user (username, password, role) VALUES (?, ?, ?)";
        preparedStatement = connection.prepareStatement(insertStatement, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1,email);
        preparedStatement.setString(2,password);
        preparedStatement.setString(3,role);
        return preparedStatement.executeUpdate();
    }

    public static int insertFamily(Connection connection, String name, String phone, String city, String address, String email, String imgSrc) throws SQLException {
        String insertStatement = "INSERT INTO family (name, phoneNumber, city, address, photo, email) VALUES (?, ?, ?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(insertStatement, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1,name);
        preparedStatement.setString(2,phone);
        preparedStatement.setString(3,city);
        preparedStatement.setString(4,address);
        preparedStatement.setString(5,imgSrc);
        preparedStatement.setString(6,email);
        return preparedStatement.executeUpdate();
    }

    public static int insertPreferences(Connection connection, int id, FamilyPreferences preferences) throws SQLException {
        String insertStatement = "INSERT INTO familyPreferences (idFamily, house, vegetarian, vegan, travels, sport, books, nature, film, videogames, cooking) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(insertStatement, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1,id);
        preparedStatement.setString(2,preferences.getHouse());
        preparedStatement.setInt(3,preferences.getVegetarian());
        preparedStatement.setInt(4,preferences.getVegan());
        preparedStatement.setInt(5,preferences.getTravels());
        preparedStatement.setInt(6,preferences.getSport());
        preparedStatement.setInt(7,preferences.getBooks());
        preparedStatement.setInt(8,preferences.getNature());
        preparedStatement.setInt(9,preferences.getFilm());
        preparedStatement.setInt(10,preferences.getVideoGames());
        preparedStatement.setInt(11,preferences.getCooking());
        return preparedStatement.executeUpdate();
    }
    public static int insertAnimal(Connection connection, int id, String type, int quantity) throws SQLException {
        String insertStatement = "INSERT INTO familyAnimal (idFamily, type, quantity) VALUES (?, ?, ?)";
        preparedStatement = connection.prepareStatement(insertStatement, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1,id);
        preparedStatement.setString(2,type);
        preparedStatement.setInt(3,quantity);
        return preparedStatement.executeUpdate();
    }

    public static int insertMember(Connection connection, int id, String name, String birth, String parenthood) throws SQLException {
        String insertStatement = "INSERT INTO familyMember (idFamily, name, birth, parenthood) VALUES (?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(insertStatement, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1,id);
        preparedStatement.setString(2,name);
        preparedStatement.setString(3,birth);
        preparedStatement.setString(4,parenthood);
        return preparedStatement.executeUpdate();
    }

    public static int insertRequest(Connection connection, FamilyRequest familyRequest) throws SQLException {
        String insertStatement = "INSERT INTO familyRequest (arrival, departure, city, house, status, siblings, animals, vegetarian, vegan, travels, books, sport, nature, film, videogames, cooking, idStudent, idFamily, compatibility) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(insertStatement, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1,familyRequest.getArrival());
        preparedStatement.setString(2,familyRequest.getDeparture());
        preparedStatement.setString(3,familyRequest.getCity());
        preparedStatement.setString(4,familyRequest.getFamilyPreferences().getHouse());
        preparedStatement.setInt(5,0);
        preparedStatement.setInt(6,familyRequest.getSiblings());
        preparedStatement.setInt(7,familyRequest.getAnimals());
        preparedStatement.setInt(8,familyRequest.getFamilyPreferences().getVegetarian());
        preparedStatement.setInt(9,familyRequest.getFamilyPreferences().getVegan());
        preparedStatement.setInt(10,familyRequest.getFamilyPreferences().getTravels());
        preparedStatement.setInt(11,familyRequest.getFamilyPreferences().getBooks());
        preparedStatement.setInt(12,familyRequest.getFamilyPreferences().getSport());
        preparedStatement.setInt(13,familyRequest.getFamilyPreferences().getNature());
        preparedStatement.setInt(14,familyRequest.getFamilyPreferences().getFilm());
        preparedStatement.setInt(15,familyRequest.getFamilyPreferences().getVideoGames());
        preparedStatement.setInt(16,familyRequest.getFamilyPreferences().getCooking());
        preparedStatement.setInt(17,familyRequest.getIdStudent());
        preparedStatement.setInt(18,familyRequest.getIdFamily());
        preparedStatement.setFloat(19,familyRequest.getCompatibility());
        return preparedStatement.executeUpdate();
    }

    public static int updateStatusRequest(Connection connection, int status, int id) throws SQLException {
        String insertStatement = "UPDATE familyRequest SET status = ? WHERE idRequest = ?";
        preparedStatement = connection.prepareStatement(insertStatement, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1,status);
        preparedStatement.setInt(2,id);
        return preparedStatement.executeUpdate();
    }

    public static int deleteRequest(Connection connection, int id) throws SQLException{
        String insertStatement = "DELETE FROM familyRequest WHERE idRequest = ?";
        preparedStatement = connection.prepareStatement(insertStatement, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1,id);
        return preparedStatement.executeUpdate();
    }

    public static int insertResidenceRequest(Connection connection, ResidenceRequest residenceRequest) throws SQLException {
        String insertStatement = "INSERT INTO residenceRequest (arrival, departure, idResidence, room, status, idStudent) VALUES (?, ?, ?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(insertStatement, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1,residenceRequest.getArrival());
        preparedStatement.setString(2,residenceRequest.getDeparture());
        preparedStatement.setInt(3,residenceRequest.getIdResidence());
        preparedStatement.setString(4,residenceRequest.getRoom());
        preparedStatement.setInt(5,0);
        preparedStatement.setInt(6,residenceRequest.getIdStudent());
        return preparedStatement.executeUpdate();
    }

    public static int updateResidenceRequest(Connection connection, int status, int id, int number) throws SQLException {
        String insertStatement = "UPDATE residenceRequest SET status = ?, roomNumber = ? WHERE idRequest = ?";
        preparedStatement = connection.prepareStatement(insertStatement, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1,status);
        preparedStatement.setInt(2,number);
        preparedStatement.setInt(3,id);
        return preparedStatement.executeUpdate();
    }

    public static int updateResidenceRequestStatus(Connection connection, int status, int id) throws SQLException {
        String insertStatement = "UPDATE residenceRequest SET status = ? WHERE idRequest = ?";
        preparedStatement = connection.prepareStatement(insertStatement, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1,status);
        preparedStatement.setInt(2,id);
        return preparedStatement.executeUpdate();
    }

    public static int deleteResidenceRequest(Connection connection, int id) throws SQLException {
        String insertStatement = "DELETE FROM residenceRequest WHERE idRequest = ?";
        preparedStatement = connection.prepareStatement(insertStatement, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1,id);
        return preparedStatement.executeUpdate();
    }
}
