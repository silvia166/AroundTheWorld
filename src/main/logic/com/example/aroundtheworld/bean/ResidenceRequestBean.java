package com.example.aroundtheworld.bean;

import com.example.aroundtheworld.engineering.observer.Subject;

public class ResidenceRequestBean extends Subject {
    private int id;
    private String city;
    private String arrival;
    private String departure;
    private String room;
    private int idStudent;
    private int idResidence;
    private int status;
    private String studentName;

    public ResidenceRequestBean(String city, String arrival, String departure, String room, int idStudent, int status) {
        this.city = city;
        this.arrival = arrival;
        this.departure = departure;
        this.room = room;
        this.idStudent = idStudent;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public int getIdResidence() {
        return idResidence;
    }

    public void setIdResidence(int idResidence) {
        this.idResidence = idResidence;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getArrival() {
        return arrival;
    }

    public String getDeparture() {
        return departure;
    }

    public String getRoom() { return room; }

    public void setStatus(int status) {
        this.status = status;
    }
}
