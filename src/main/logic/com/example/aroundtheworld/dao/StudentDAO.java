package com.example.aroundtheworld.dao;

import com.example.aroundtheworld.model.Student;

public interface StudentDAO {
    Student retrieveStudent(String username, int id);
    void addStudent(String name, String surname, String birth, String nationality, String phoneNumber, String email, String password);
    String getNameById(int idStudent);
}
