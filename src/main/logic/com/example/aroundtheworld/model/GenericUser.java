package com.example.aroundtheworld.model;

public abstract class GenericUser {
    private int id;
    private int role; // 1 student, 2 family
    private String phoneNumber;
    private String email;

    protected GenericUser(int id, int role, String phoneNumber, String email) {
        this.id = id;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    protected GenericUser(){}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
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
