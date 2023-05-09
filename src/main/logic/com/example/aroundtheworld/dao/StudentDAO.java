package com.example.aroundtheworld.dao;

import com.example.aroundtheworld.connection.ConnectionDB;
import com.example.aroundtheworld.dao.queries.CRUDQueries;
import com.example.aroundtheworld.dao.queries.SimpleQueries;
import com.example.aroundtheworld.exception.ConnectionDbException;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.model.Student;

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
    private static final String EMAIL = "email";

    private StudentDAO(){}

    public static Student retrieveStudent(String username, int id) throws NotFoundException{
        Statement stmt;
        Student student = null;

        try{
            stmt = ConnectionDB.getConnection();
            ResultSet resultSet = null;

            if(id == 0){
                resultSet = SimpleQueries.retrieveStudent(stmt, username);
            } else if(username == null){
                resultSet = SimpleQueries.retrieveStudentById(stmt, id);
            }

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
                String email = resultSet.getString(EMAIL);

                student = new Student(name, surname, nationality, birth, email, phoneNumber, studentId);

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

    public static String getNameById(int idStudent) {
        Statement stmt;
        String studentName = null;

        try{
            stmt = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.retrieveStudentNameById(stmt, idStudent);

            if(!resultSet.first()){
                throw new NotFoundException("No student found with id: " + idStudent);
            }

            resultSet.first();
            do {
                String name = resultSet.getString(NAME);
                String surname = resultSet.getString(SURNAME);
                studentName = name;
                studentName = studentName.concat(" ");
                studentName = studentName.concat(surname);

            } while(resultSet.next());

            resultSet.close();

        } catch(SQLException | ConnectionDbException | NotFoundException e) {
            e.printStackTrace();
        }
        return studentName;
    }

}
