package com.example.aroundtheworld.dao.queries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SimpleQueries {

    private SimpleQueries() {
    }

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
}
