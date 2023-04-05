package com.example.aroundtheworld.model;

public class Residence {

    private int id;
    private String name;
    private String address;
    private String distanceSchool;

    public Residence(int id, String name, String address, String distanceSchool) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.distanceSchool = distanceSchool;
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

    public String getDistanceSchool() {
        return distanceSchool;
    }
}
