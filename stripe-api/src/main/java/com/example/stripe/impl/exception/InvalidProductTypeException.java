package com.example.stripe.impl.exception;

public class InvalidProductTypeException extends RuntimeException {
    public InvalidProductTypeException() {
    }

    public InvalidProductTypeException(String fieldName) {
        super("Field \"" + fieldName + "\" is required");
    }
}
