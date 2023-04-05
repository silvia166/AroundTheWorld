package com.example.aroundtheworld.bean;

import java.util.Date;

public class StudentBean {

    private String name;
    private String surname;
    private String nationality;
    private Date birth;
    private String email;
    private String phoneNumber;
    private int id;

    public StudentBean(String name, String surname, String nationality, Date birth, String email, String phoneNumber, int id) {
        this.name = name;
        this.surname = surname;
        this.nationality = nationality;
        this.birth = birth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
