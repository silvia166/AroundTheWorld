package com.example.aroundtheworld.dao.queries;

import java.sql.*;

public class SimpleQueries {

    private static PreparedStatement preparedStatement = null;
    private SimpleQueries() {}

    public static ResultSet checkUser(Connection connection, String username, String password) throws SQLException {
        String sql = "SELECT role FROM user WHERE username = ? AND password = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);
        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveStudent(Connection connection, String username) throws SQLException {
        String sql = "SELECT * FROM student WHERE email = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1,username);
        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveFamilyByUsername(Connection connection, String username) throws SQLException {
        String sql = "SELECT * FROM family WHERE email = ? ";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1,username);
        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveFamilyById(Connection connection, int idFamily) throws SQLException {
        String sql = "SELECT * FROM family WHERE idFamily = ? ";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1,idFamily);
        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveFamilyMember(Connection connection, int familyId) throws SQLException {
        String sql = "SELECT name, birth, parenthood FROM familyMember WHERE idFamily = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1,familyId);
        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveAnimal(Connection connection, int familyId) throws SQLException{
        String sql = "SELECT type, quantity FROM familyAnimal WHERE idFamily = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1,familyId);
        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveCity(Connection connection, String name) throws SQLException{
        String sql = "SELECT * FROM city WHERE name = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1,name);
        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveResidence(Connection connection, String city) throws SQLException{
        String sql = "SELECT * FROM residence WHERE city = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1,city);
        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveSchool(Connection connection, String city) throws SQLException{
        String sql = "SELECT * FROM school WHERE city = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1,city);
        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveFamilyID(Connection connection, String name) throws SQLException {
        String sql = "SELECT idFamily FROM family WHERE name = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1,name);
        return preparedStatement.executeQuery();
    }

    public static ResultSet retrievePreferences(Connection connection, int id) throws SQLException {
        String sql = "SELECT house, vegetarian, vegan, travels, sport, books, film, nature, cooking, videogames FROM familyPreferences WHERE idFamily = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1,id);
        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveFamiliesByCity(Connection connection, String city) throws SQLException {
        String sql = "SELECT idFamily, name, phoneNumber, address, photo FROM family WHERE city = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1,city);
        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveFamilyRequests(Connection connection, int id) throws SQLException {
        String sql = "SELECT * FROM familyRequest WHERE idFamily = ? AND (status = '" + 0 + "' OR status = '" + 1 + "')";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1,id);
        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveStudentNameById(Connection connection, int id) throws SQLException {
        String sql = "SELECT name, surname FROM student WHERE idStudent = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1,id);
        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveStudentById(Connection connection, int id) throws SQLException{
        String sql = "SELECT * FROM student WHERE idStudent = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1,id);
        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveIdResidence(Connection connection, String city) throws SQLException {
        String sql = "SELECT idResidence FROM residence WHERE city = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1,city);
        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveResidenceRequests(Connection connection) throws SQLException {
        String sql = "SELECT * FROM residenceRequest WHERE CURRENT_DATE() <= departure";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveResidencebyId(Connection connection, int idResidence) throws SQLException{
        String sql = "SELECT city FROM residence WHERE idResidence = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1,idResidence);
        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveRooms(Connection connection, int id, String arrival, String departure) throws SQLException{
        String sql = "SELECT number, type FROM room WHERE type = 'single' AND idResidence = ? AND (number, idResidence) not in (SELECT roomNumber, idResidence FROM residenceRequest WHERE status != 0 AND ((arrival >= ? AND arrival <= ?) OR (departure >= ? AND departure <= ?) OR (arrival <= ? AND departure >= ?) OR (arrival >= ? AND departure <= ?))) UNION SELECT number, type FROM room WHERE type = 'double' AND idResidence = ? AND (number, idResidence) not in ( SELECT DISTINCT number, room.idResidence FROM residencerequest AS r2 JOIN residencerequest AS r1  JOIN room ON r1.idResidence = r2.idResidence AND r1.roomNumber =r2.roomNumber AND r1.roomNumber = room.number AND r1.idResidence = room.idResidence WHERE type = 'double' AND r1.idStudent != r2.idStudent AND ((r1.arrival >= r2.arrival AND r1.arrival <= r2.departure) OR (r1.departure >= r2.arrival AND r1.departure <= r2.departure) OR (r1.arrival <= r2.arrival AND r1.departure >= r2.departure) OR (r1.arrival >= r2.arrival AND r1.departure <= r2.departure)) AND ((r1.arrival >= ? AND r1.arrival <= ?) OR (r1.departure >= ? AND r1.departure <= ?) OR (r1.arrival <= ? AND r1.departure >= ?) OR (r1.arrival >= ? AND r1.departure <= ?)))";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1,id);
        preparedStatement.setString(2,arrival);
        preparedStatement.setString(3,departure);
        preparedStatement.setString(4,arrival);
        preparedStatement.setString(5,departure);
        preparedStatement.setString(6,arrival);
        preparedStatement.setString(7,departure);
        preparedStatement.setString(8,arrival);
        preparedStatement.setString(9,departure);
        preparedStatement.setInt(10,id);
        preparedStatement.setString(11,arrival);
        preparedStatement.setString(12,departure);
        preparedStatement.setString(13,arrival);
        preparedStatement.setString(14,departure);
        preparedStatement.setString(15,arrival);
        preparedStatement.setString(16,departure);
        preparedStatement.setString(17,arrival);
        preparedStatement.setString(18,departure);
        return preparedStatement.executeQuery();
    }


    public static ResultSet retrieveStudentResidenceRequests(Connection connection, int id) throws SQLException{
        String sql = "SELECT * FROM residenceRequest WHERE CURRENT_DATE() <= departure AND status != 2 AND idStudent = ? ORDER BY status DESC";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1,id);
        return preparedStatement.executeQuery();
    }

    public static ResultSet selectDistinctRequest(Connection connection, int idStudent, String arrival, String departure) throws SQLException {
        String sql = "SELECT idRequest FROM residenceRequest WHERE idStudent = ? AND ((arrival > ? AND arrival < ?) OR (departure > ? AND departure < ?) OR (arrival < ? AND departure > ?) OR (arrival > ? AND departure < ?)) UNION SELECT idRequest FROM familyRequest WHERE idStudent = ? AND ((arrival > ? AND arrival < ?) OR (departure > ? AND departure < ?) OR (arrival < ? AND departure > ?) OR (arrival > ? AND departure < ?))";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1,idStudent);
        preparedStatement.setString(2,arrival);
        preparedStatement.setString(3,departure);
        preparedStatement.setString(4,arrival);
        preparedStatement.setString(5,departure);
        preparedStatement.setString(6,arrival);
        preparedStatement.setString(7,departure);
        preparedStatement.setString(8,arrival);
        preparedStatement.setString(9,departure);
        preparedStatement.setInt(10,idStudent);
        preparedStatement.setString(11,arrival);
        preparedStatement.setString(12,departure);
        preparedStatement.setString(13,arrival);
        preparedStatement.setString(14,departure);
        preparedStatement.setString(15,arrival);
        preparedStatement.setString(16,departure);
        preparedStatement.setString(17,arrival);
        preparedStatement.setString(18,departure);
        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveStudentFamilyRequests(Connection connection, int idStudent) throws SQLException {
        String sql = "SELECT idRequest, arrival, departure, city, status, idFamily FROM familyRequest WHERE CURRENT_DATE() <= departure AND status != 2 AND idStudent = ? ORDER BY status DESC";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1,idStudent);
        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveFamilyName(Connection connection, int idFamily) throws SQLException {
        String sql = "SELECT name, photo FROM family WHERE idFamily = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1,idFamily);
        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveBookingsByFamily(Connection connection, int idFamily) throws SQLException {
        String sql = "SELECT idRequest, arrival, departure, idStudent, rate FROM familyRequest WHERE status = 2 AND idFamily = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1,idFamily);
        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveFamilyBookingsByStudent(Connection connection, int idStudent) throws SQLException {
        String sql = "SELECT city, idRequest, arrival, departure, idFamily, rate FROM familyRequest WHERE status = 2 AND idStudent = ? ORDER BY rate ASC";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1,idStudent);
        return preparedStatement.executeQuery();
    }

    public static ResultSet retrieveResidenceBookingsByStudent(Connection connection, int idStudent) throws SQLException {
        String sql = "SELECT idResidence, idRequest, arrival, departure FROM residenceRequest WHERE status = 2 AND idStudent = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1,idStudent);
        return preparedStatement.executeQuery();
    }

    public static ResultSet getNumberOfRequests(Connection connection, int id) throws SQLException {
        String sql = "SELECT count(*) as requests FROM familyRequest WHERE idFamily = ?";
        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1,id);
        return preparedStatement.executeQuery();
    }
}
