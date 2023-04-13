package com.example.aroundtheworld.exception;

public class TravelDateException extends Exception{
    public TravelDateException(String message) {
        super("The arrival must be at least 7 days from today and your stay must last more than 1 week! Please check your "+ message);
    }
}
