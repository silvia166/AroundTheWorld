package com.example.aroundtheworld.model;

public class Animal {
    private String type;
    private int quantity;

    public Animal(String type, int quantity) {
        this.type = type;
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }
}
