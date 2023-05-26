package com.example.aroundtheworld.dao;

import com.example.aroundtheworld.dao.queries.SimpleQueries;
import com.example.aroundtheworld.engineering.Printer;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.model.Student;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class StudentDAOCSV extends StudentDAO{

    private static final String CSV_FILE_NAME = "src/main/res/Students.csv";
    private static final int IDSTUDENT = 0;
    private static final int NAME = 1;
    private static final int SURNAME = 2;
    private static final int NATIONALITY = 3;
    private static final int BIRTH = 4;
    private static final int EMAIL = 5;
    private static final int PHONE = 6;

    @Override
    public Student retrieveStudent(String username, int id) {

        Student student = null;

        try{
            File file = new File(CSV_FILE_NAME);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String row;
            String[] data;

            while((row = bufferedReader.readLine()) != null){
                data = row.split(",");
                if((id == 0 && data[EMAIL].equals(username)) || (username == null && data[IDSTUDENT].equals(id)) ) {
                    student = new Student(data[NAME], data[SURNAME], data[NATIONALITY], data[BIRTH], data[EMAIL], data[PHONE], Integer.parseInt(data[IDSTUDENT]));
                }
            }
            if(student == null){
                throw new NotFoundException("No student found");
            }
            bufferedReader.close();
        }catch(NotFoundException | IOException e){
            Printer.printError(e.getMessage());
        }

        return student;
    }

    @Override
    public String getNameById(int idStudent) {
        String studentName = null;

        try{
            File file = new File(CSV_FILE_NAME);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String row;
            String[] data;

            while((row = bufferedReader.readLine()) != null){
                data = row.split(",");
                if(data[IDSTUDENT].equals(idStudent)) {
                    String name = data[NAME];
                    String surname = data[SURNAME];
                    studentName = name;
                    studentName = studentName.concat(" ");
                    studentName = studentName.concat(surname);
                }
            }
            if(studentName == null){
                throw new NotFoundException("No student found");
            }
            bufferedReader.close();
        }catch(NotFoundException | IOException e){
            Printer.printError(e.getMessage());
        }

        return studentName;
    }
}
