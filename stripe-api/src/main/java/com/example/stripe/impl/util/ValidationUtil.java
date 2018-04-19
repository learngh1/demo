package com.example.stripe.impl.util;

import com.example.stripe.impl.exception.FieldRequiredException;
import com.example.stripe.impl.exception.UnsupportedParamException;

import java.util.Collection;
import java.util.List;

public class ValidationUtil {
    public static void validateRequired(String fieldName, Object o) {
        if (o == null) {
            throw new FieldRequiredException(fieldName);
        }
    }

    public static void validateNegative(Number number) {
        if (number != null && number.intValue() >= 0) {
            throw new IllegalArgumentException("Must be a negative integer, given: " + number);
        }
    }

    public static void validatePositive(Number number) {
        if (number != null && number.intValue() < 0) {
            throw new IllegalArgumentException("Must be a positive integer, given: " + number);
        }
    }

    public static void validateParamIsAllowed(Collection<String> params, List<String> allowed) {
        for (String param : params) {
            if (!allowed.contains(param)) {
                throw new UnsupportedParamException("param is unsupported: " + param + ", allowed params: " + allowed);
            }
        }
    }
}
