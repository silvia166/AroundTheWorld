package com.example.aroundtheworld.dao;

import com.example.aroundtheworld.connection.ConnectionDB;
import com.example.aroundtheworld.dao.queries.CRUDQueries;
import com.example.aroundtheworld.dao.queries.SimpleQueries;
import com.example.aroundtheworld.exception.ConnectionDbException;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.model.Student;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentDAO {
    private static final String ID = "idStudent";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String NATIONALITY = "nationality";
    private static final String BIRTH = "birth";
    private static final String PHONE = "phoneNumber";

    private StudentDAO(){}

    public static Student retrieveStudent(String username) throws NotFoundException{
        Statement stmt;
        Student student = null;

        try{
            stmt = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.retrieveStudent(stmt, username);

            if(!resultSet.first()){
                throw new NotFoundException("No student found with username: " + username);
            }

            resultSet.first();
            do {
                int studentId = resultSet.getInt(ID);
                String phoneNumber = resultSet.getString(PHONE);
                String name = resultSet.getString(NAME);
                String surname = resultSet.getString(SURNAME);
                String nationality = resultSet.getString(NATIONALITY);
                String birth = String.valueOf(resultSet.getDate(BIRTH));

                student = new Student(name, surname, nationality, birth, username, phoneNumber, studentId);

            } while(resultSet.next());

            resultSet.close();

        } catch(SQLException | ConnectionDbException e) {
            e.printStackTrace();
        }
        return student;

    }


    public static void addStudent(String name, String surname, String birth, String nationality, String phoneNumber, String email, String password) {
        Statement stmt;

        try{
            stmt = ConnectionDB.getConnection();

            CRUDQueries.insertUser(stmt, email, password, "student");
            CRUDQueries.insertStudent(stmt, name, surname, birth, nationality, phoneNumber, email);

        } catch(SQLException | ConnectionDbException e) {
            e.printStackTrace();
        }

    }
}
