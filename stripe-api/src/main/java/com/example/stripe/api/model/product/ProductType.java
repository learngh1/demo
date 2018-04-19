package com.example.stripe.api.model.product;

public enum ProductType {
    SERVICE("service"), GOOD("good"), UNSUPPORTED_TYPE_EXAMPLE_FOR_TEST("unsupported type example for test");

    private final String value;

    ProductType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
