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

        String last_id = null;
        String line;
        String[] data;
        try {
            File file = new File(CSV_FILE_STUDENTS);
            BufferedReader readFromFile = new BufferedReader(new FileReader(file));

            while((line = readFromFile.readLine()) != null){
                data = line.split(",");
                last_id = data[IDSTUDENT];
            }

            readFromFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int count = Integer.parseInt(last_id) + 1;
        String student = String.valueOf(count);
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

        File file_user = new File(CSV_FILE_USERS);
        File file_student = new File(CSV_FILE_STUDENTS);

        try {
            PrintWriter output_user = new PrintWriter(new BufferedWriter(new FileWriter(file_user, true)));
            PrintWriter output_student = new PrintWriter(new BufferedWriter(new FileWriter(file_student, true)));
            output_user.println(user);
            output_user.close();
            output_student.println(student);
            output_student.close();
        } catch (IOException e) {
            Printer.printError(e.getMessage());
        }

    }
    public abstract String getNameById(int idStudent);
}
