package com.example.aroundtheworld.model;

public abstract class Request {
    private int id;
    private String city;
    private String arrival;
    private String departure;
    private int idStudent;
    private int status;

    protected Request(String city, String arrival, String departure, int idStudent, int status) {
        this.city = city;
        this.arrival = arrival;
        this.departure = departure;
        this.idStudent = idStudent;
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArrival() {
        return arrival;
    }

    public int getId() {
        return id;
    }

    public String getDeparture() {
        return departure;
    }

    public String getCity() {
        return city;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public int getStatus() {
        return status;
    }

}
