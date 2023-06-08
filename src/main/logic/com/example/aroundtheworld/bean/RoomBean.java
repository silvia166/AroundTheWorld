package com.example.aroundtheworld.bean;

public class RoomBean {
    private int number;
    private String type;

    public RoomBean(int number, String type) {
        this.number = number;
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }
}
