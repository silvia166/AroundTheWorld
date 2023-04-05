package com.example.aroundtheworld.model;

public class School {

    private int id;
    private String name;
    private String address;
    private String hours;
    private String courses;

    public School(int id, String name, String address, String hours, String courses) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.hours = hours;
        this.courses = courses;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getHours() {
        return hours;
    }

    public String getCourses() {
        return courses;
    }
}
