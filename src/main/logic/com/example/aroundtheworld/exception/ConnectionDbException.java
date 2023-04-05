package com.example.aroundtheworld.exception;

public class ConnectionDbException extends Exception{
    public ConnectionDbException() {
        super("Error during DB connection");
    }
}
