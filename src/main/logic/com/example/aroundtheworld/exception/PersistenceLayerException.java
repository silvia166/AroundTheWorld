package com.example.aroundtheworld.exception;

public class PersistenceLayerException extends Exception{

    private final String message;

    public PersistenceLayerException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
