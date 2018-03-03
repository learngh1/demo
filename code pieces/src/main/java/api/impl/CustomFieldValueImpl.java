package com.example.api.impl;

import com.example.api.CustomFieldValue;

/**
 * Created by user on 17.10.2017.
 */
public class CustomFieldValueImpl implements CustomFieldValue{

    private String id;
    private String key;
    private Object value;


    public CustomFieldValueImpl(String id, String key, Object value) {
        this.id = id;
        this.key = key;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return "CustomFieldValueImpl{" +
                "id='" + id + '\'' +
                ", key='" + key + '\'' +
                ", value=" + value +
                ", valueClass=" + (value != null ? value.getClass() : null) + "}";
    }
}
