package com.example.stripe.impl.exception;

public class FieldRequiredException extends RuntimeException {
    public FieldRequiredException(){}
    public FieldRequiredException(String fieldName) {
        super("Field \"" + fieldName + "\" is required");
    }
    public FieldRequiredException(String fieldName, String message) {
        super("Field \"" + fieldName + "\" is required: " + message);
    }
}
