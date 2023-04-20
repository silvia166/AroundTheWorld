package com.example.aroundtheworld.model;

public class Student extends GenericUser {
    private String name;
    private String surname;
    private String nationality;
    private String dateOfBirth;


    public Student(String name, String surname, String nationality, String dateOfBirth, String email, String phone, int id) {

        super(id, 1, phone, email);
        this.name = name;
        this.surname = surname;
        this.nationality = nationality;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }

    public String getNationality() {
        return nationality;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }

}
