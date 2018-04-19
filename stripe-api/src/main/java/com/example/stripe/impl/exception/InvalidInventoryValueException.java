package com.example.stripe.impl.exception;

public class InvalidInventoryValueException extends RuntimeException {
    public InvalidInventoryValueException(String msg) {
        super(msg);
    }
}
