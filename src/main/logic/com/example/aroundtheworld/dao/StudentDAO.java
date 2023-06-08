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
    private static final int IDSTUDENT = 0;

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

        int lastId = 0;
        String line;
        String[] data;
        File file = new File(CSV_FILE_STUDENTS);

        //closing the resource readFromFile is handled automatically by the try-with-resources
        try (BufferedReader readFromFile = new BufferedReader(new FileReader(file))){

            while((line = readFromFile.readLine()) != null){
                data = line.split(",");
                lastId = Integer.parseInt(data[IDSTUDENT]);
            }

        } catch (IOException e) {
            Printer.printError(e.getMessage());
        }

        String student = String.valueOf(lastId+1);
        student = student.concat(",");
        student = student.concat(name);
        student = student.concat(",");
        student = student.concat(surname);
        student = student.concat(",");
        student = student.concat(nationality);
        student = student.concat(",");
        student = student.concat(birth);
        student = student.concat(",");
        student = student.concat(email);
        student = student.concat(",");
        student = student.concat(phoneNumber);

        File fileUser = new File(CSV_FILE_USERS);
        File fileStudent = new File(CSV_FILE_STUDENTS);

        //closing the resource outputUser is handled automatically by the try-with-resources
        try (PrintWriter outputUser = new PrintWriter(new BufferedWriter(new FileWriter(fileUser, true)))){
            PrintWriter outputStudent = new PrintWriter(new BufferedWriter(new FileWriter(fileStudent, true)));
            outputUser.println(user);
            outputStudent.println(student);
            outputStudent.close();
        } catch (IOException e) {
            Printer.printError(e.getMessage());
        }

    }
    public abstract Student getNameById(int idStudent);
    public abstract Student retrieveStudentById(int id);
    public abstract Student retrieveStudentByUsername(String username);
}
