package com.example.aroundtheworld.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("\nUser not found.");
    }
}
