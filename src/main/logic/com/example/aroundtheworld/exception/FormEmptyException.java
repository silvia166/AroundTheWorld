package com.example.aroundtheworld.exception;

public class FormEmptyException extends Exception{
    public FormEmptyException(String missingField) {
        super("Please complete the field: " + missingField);
    }
}
