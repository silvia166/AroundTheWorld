package com.example.aroundtheworld.model;

public class ResidenceRequest {
    private int id;
    private String city;
    private String arrival;
    private String departure;
    private String room;
    private int idStudent;
    private int idResidence;
    private int status;

    public ResidenceRequest(String city, String arrival, String departure, String room, int idStudent, int status) {
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
}
