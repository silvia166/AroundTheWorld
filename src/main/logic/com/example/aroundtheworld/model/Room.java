package com.example.aroundtheworld.model;

public class Room {
    private int idResidence;
    private int number;
    private String type;

    public Room(int idResidence, int number, String type) {
        this.idResidence = idResidence;
        this.number = number;
        this.type = type;
    }

    public int getIdResidence() {
        return idResidence;
    }

    public int getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }
}
