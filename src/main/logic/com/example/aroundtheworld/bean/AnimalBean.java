package com.example.aroundtheworld.bean;

public class AnimalBean {
    private String type;
    private int quantity;

    public AnimalBean(String type, int quantity) {
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
