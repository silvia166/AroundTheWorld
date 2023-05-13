package com.example.aroundtheworld.dao.queries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SimpleQueries {
    private SimpleQueries() {}

    public static ResultSet checkUser(Statement stmt, String username, String password) throws SQLException {
        String sql = "SELECT role FROM user WHERE username = '" + username + "' AND password = '" + password + "' ";
        return stmt.executeQuery(sql);
    }

    public static ResultSet retrieveStudent(Statement stmt, String username) throws SQLException {
        String sql = "SELECT * FROM student WHERE email = '" + username + "' ";
        return stmt.executeQuery(sql);
    }

    public static ResultSet retrieveFamily(Statement stmt, String username) throws SQLException{
        String sql = "SELECT * FROM family WHERE email = '" + username + "' ";
        return stmt.executeQuery(sql);
    }

    public static ResultSet retrieveFamilyMember(Statement stmt, int familyId) throws SQLException{
        String sql = "SELECT name, birth, parenthood FROM familyMember WHERE idFamily = '" + familyId + "'";
        return stmt.executeQuery(sql);
    }

    public static ResultSet retrieveAnimal(Statement stmt, int familyId) throws SQLException{
        String sql = "SELECT type, quantity FROM familyAnimal WHERE idFamily = '" + familyId + "'";
        return stmt.executeQuery(sql);
    }

    public static ResultSet retrieveCity(Statement stmt, String name) throws SQLException{
        String sql = "SELECT * FROM city WHERE name = '" + name + "'";
        return stmt.executeQuery(sql);
    }

    public static ResultSet retrieveResidence(Statement stmt, String city) throws SQLException{
        String sql = "SELECT * FROM residence WHERE city = '" + city + "'";
        return stmt.executeQuery(sql);
    }

    public static ResultSet retrieveSchool(Statement stmt, String city) throws SQLException{
        String sql = "SELECT * FROM school WHERE city = '" + city + "'";
        return stmt.executeQuery(sql);
    }

    public static ResultSet retrieveFamilyID(Statement stmt, String name) throws SQLException {
        String sql = "SELECT idFamily FROM family WHERE name = '" + name + "'";
        return stmt.executeQuery(sql);
    }

    public static ResultSet retrievePreferences(Statement stmt, int id) throws SQLException {
        String sql = "SELECT house, vegetarian, vegan, travels, sport, books, film, nature, cooking, videogames FROM familyPreferences WHERE idFamily = '" + id + "'";
        return stmt.executeQuery(sql);
    }

    public static ResultSet retrieveFamilyIDByCity(Statement stmt, String city) throws SQLException {
        String sql = "SELECT idFamily, name, phoneNumber, address, photo FROM family WHERE city = '" + city + "'";
        return stmt.executeQuery(sql);
    }

    public static ResultSet retrieveFamilyRequests(Statement stmt, int id) throws SQLException {
        String sql = "SELECT * FROM familyRequest WHERE idFamily = '" + id + "' AND (status = '" + 0 + "' OR status = '" + 1 + "')";
        return stmt.executeQuery(sql);
    }

    public static ResultSet retrieveStudentNameById(Statement stmt, int id) throws SQLException {
        String sql = "SELECT name, surname FROM student WHERE idStudent = '" + id + "'";
        return stmt.executeQuery(sql);
    }

    public static ResultSet retrieveStudentById(Statement stmt, int id) throws SQLException{
        String sql = "SELECT * FROM student WHERE idStudent = '" + id + "'";
        return stmt.executeQuery(sql);
    }

    public static ResultSet retrieveIdResidence(Statement stmt, String city) throws SQLException {
        String sql = "SELECT idResidence FROM residence WHERE city = '" + city + "'";
        return stmt.executeQuery(sql);
    }

    public static ResultSet retrieveResidenceRequests(Statement stmt) throws SQLException {
        String sql = "SELECT * FROM residenceRequest WHERE CURRENT_DATE() <= departure";
        return stmt.executeQuery(sql);
    }

    public static ResultSet retrieveResidencebyId(Statement stmt, int idResidence) throws SQLException{
        String sql = "SELECT city FROM residence WHERE idResidence = '" + idResidence + "'";
        return stmt.executeQuery(sql);
    }

    public static ResultSet retrieveRooms(Statement stmt, int id, String arrival, String departure) throws SQLException{
        String sql = "SELECT number, type FROM room WHERE type = 'single' AND idResidence = '" + id + "' AND (number, idResidence) not in (SELECT roomNumber, idResidence FROM residenceRequest WHERE status != 0 AND ((arrival >= '" + arrival + "' AND arrival <= '" + departure + "') OR (departure >= '"+arrival+"' AND departure <= '"+ departure + "') OR (arrival <= '" + arrival + "' AND departure >= '" + departure + "') OR (arrival >= '"+ arrival +"' AND departure <= '" + departure +"'))) UNION SELECT number, type FROM room WHERE type = 'double' AND idResidence = '" + id + "' AND (number, idResidence) not in ( SELECT DISTINCT number, room.idResidence FROM residencerequest AS r2 JOIN residencerequest AS r1  JOIN room ON r1.idResidence = r2.idResidence AND r1.roomNumber =r2.roomNumber AND r1.roomNumber = room.number AND r1.idResidence = room.idResidence WHERE type = 'double' AND r1.idStudent != r2.idStudent AND ((r1.arrival >= r2.arrival AND r1.arrival <= r2.departure) OR (r1.departure >= r2.arrival AND r1.departure <= r2.departure) OR (r1.arrival <= r2.arrival AND r1.departure >= r2.departure) OR (r1.arrival >= r2.arrival AND r1.departure <= r2.departure)) AND ((r1.arrival >= '" + arrival + "' AND r1.arrival <= '" + departure + "') OR (r1.departure >= '" + arrival + "' AND r1.departure <= '" + departure + "') OR (r1.arrival <= '" + arrival + "' AND r1.departure >= '" + departure + "') OR (r1.arrival >= '" + arrival + "' AND r1.departure <= '" + departure + "')))";
        return stmt.executeQuery(sql);
    }


    public static ResultSet retrieveModifiedRequests(Statement stmt, int id) throws SQLException{
        String sql = "SELECT * FROM residenceRequest WHERE CURRENT_DATE() <= departure AND status = 1 AND idStudent = '"+ id + "'";
        return stmt.executeQuery(sql);
    }

    public static ResultSet selectDistinctRequest(Statement stmt, int idStudent, String arrival, String departure) throws SQLException {
        String sql = "SELECT idRequest FROM residenceRequest WHERE idStudent = '"+ idStudent + "' AND ((arrival > '" + arrival + "' AND arrival < '" + departure + "') OR (departure > '"+arrival+"' AND departure < '"+ departure + "') OR (arrival < '" + arrival + "' AND departure > '" + departure + "') OR (arrival > '"+ arrival +"' AND departure < '" + departure +"')) UNION SELECT idRequest FROM familyRequest WHERE idStudent = '"+ idStudent + "' AND ((arrival > '" + arrival + "' AND arrival < '" + departure + "') OR (departure > '"+arrival+"' AND departure < '"+ departure + "') OR (arrival < '" + arrival + "' AND departure > '" + departure + "') OR (arrival > '"+ arrival +"' AND departure < '" + departure +"'))";
        return stmt.executeQuery(sql);
    }

}
