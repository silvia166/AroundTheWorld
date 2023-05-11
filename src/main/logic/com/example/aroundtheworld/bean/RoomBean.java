package com.example.aroundtheworld.bean;

public class RoomBean {
    private int number;
    private int idResidence;
    private String type;

    public RoomBean(int number, int idResidence, String type) {
        this.number = number;
        this.idResidence = idResidence;
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public int getIdResidence() {
        return idResidence;
    }

    public String getType() {
        return type;
    }
}
