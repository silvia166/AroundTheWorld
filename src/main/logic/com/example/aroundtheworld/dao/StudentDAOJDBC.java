package com.example.aroundtheworld.dao;

import com.example.aroundtheworld.connection.ConnectionDB;
import com.example.aroundtheworld.dao.queries.SimpleQueries;
import com.example.aroundtheworld.engineering.Printer;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.model.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAOJDBC extends StudentDAO{
    private static final String ID = "idStudent";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String NATIONALITY = "nationality";
    private static final String BIRTH = "birth";
    private static final String PHONE = "phoneNumber";
    private static final String EMAIL = "email";

    @Override
    public Student retrieveStudentById(int id) {
        Connection connection;
        Student student = null;

        try{
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.retrieveStudentById(connection, id);

            if(!resultSet.first()){
                throw new NotFoundException("No student found with id: " + id);
            }

            resultSet.first();
            do {
                student = setStudentData(resultSet);

            } while(resultSet.next());

            resultSet.close();

        } catch(SQLException | NotFoundException e) {
            Printer.printError(e.getMessage());
        }
        return student;
    }

    @Override
    public Student retrieveStudentByUsername(String username) {
        Connection connection;
        Student student = null;

        try{
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.retrieveStudent(connection, username);

            if(!resultSet.first()){
                throw new NotFoundException("No student found with username: " + username);
            }

            resultSet.first();
            do {
                student = setStudentData(resultSet);

            } while(resultSet.next());

            resultSet.close();

        } catch(SQLException | NotFoundException e) {
            Printer.printError(e.getMessage());
        }
        return student;
    }

    private static Student setStudentData(ResultSet resultSet) throws SQLException {
        int studentId = resultSet.getInt(ID);
        String phoneNumber = resultSet.getString(PHONE);
        String name = resultSet.getString(NAME);
        String surname = resultSet.getString(SURNAME);
        String nationality = resultSet.getString(NATIONALITY);
        String birth = String.valueOf(resultSet.getDate(BIRTH));
        String email = resultSet.getString(EMAIL);

        return new Student(name, surname, nationality, birth, email, phoneNumber, studentId);
    }

    @Override
    public Student getNameById(int idStudent) {
        Connection connection;
        Student student = null;

        try{
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.retrieveStudentNameById(connection, idStudent);

            if(!resultSet.first()){
                throw new NotFoundException("No student found with id: " + idStudent);
            }

            resultSet.first();
            do {
                String name = resultSet.getString(NAME);
                String surname = resultSet.getString(SURNAME);
                student = new Student(name, surname);

            } while(resultSet.next());

            resultSet.close();

        } catch(SQLException | NotFoundException e) {
            Printer.printError(e.getMessage());
        }
        return student;
    }

}
