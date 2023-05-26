package com.example.aroundtheworld.dao;

import com.example.aroundtheworld.connection.ConnectionDB;
import com.example.aroundtheworld.dao.queries.CRUDQueries;
import com.example.aroundtheworld.engineering.Printer;
import com.example.aroundtheworld.model.Student;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class StudentDAO {
    private static final String CSV_FILE_USERS = "src/main/res/Users.csv";
    private static final String CSV_FILE_STUDENTS = "src/main/res/Students.csv";
    public abstract Student retrieveStudent(String username, int id);
    public void addStudent(String name, String surname, String birth, String nationality, String phoneNumber, String email, String password){
        //JDBC
        Connection connection;

        try{
            connection = ConnectionDB.getConnection();

            CRUDQueries.insertUser(connection, email, password, "student");
            CRUDQueries.insertStudent(connection, name, surname, birth, nationality, phoneNumber, email);

        } catch(SQLException e) {
            Printer.printError(e.getMessage());
        }

        //CSV
        String user = email;
        user = user.concat(",");
        user = user.concat(password);
        user = user.concat(",student");

        File file_user = new File(CSV_FILE_USERS);
        File file_student = new File(CSV_FILE_STUDENTS);

        try {
            PrintWriter output_user = new PrintWriter(new BufferedWriter(new FileWriter(file_student, true)));
            PrintWriter output_student = new PrintWriter(new BufferedWriter(new FileWriter(file_user, true)));
            output_user.println(user);
            output_user.close();
            output_student.println();
            output_student.close();
        } catch (IOException e) {
            Printer.printError(e.getMessage());
        }

    }
    public abstract String getNameById(int idStudent);
}
