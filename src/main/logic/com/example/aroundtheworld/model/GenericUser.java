package com.example.aroundtheworld.model;

public abstract class GenericUser {
    private int id;
    private int type; // 1 student, 2 family
    private String phoneNumber;
    private String email;

    protected GenericUser(int id, int type, String phoneNumber, String email) {
        this.id = id;
        this.type = type;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
