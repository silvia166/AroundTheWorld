package com.example.aroundtheworld.dao.queries;

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
}
