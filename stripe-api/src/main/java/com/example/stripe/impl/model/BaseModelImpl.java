package com.example.stripe.impl.model;

import com.example.stripe.api.model.BaseModel;

public class BaseModelImpl implements BaseModel{
    private String id;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }
}
