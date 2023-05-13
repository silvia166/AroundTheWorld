package com.example.aroundtheworld.exception;

public class NoAvailableRoomsException extends Exception{
    public NoAvailableRoomsException() {
        super("No available rooms \n Reject the request");
    }
}
