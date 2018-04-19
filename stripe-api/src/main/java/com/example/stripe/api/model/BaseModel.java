package com.example.stripe.api.model;

import java.io.Serializable;

public interface BaseModel extends Serializable{
    String getId();
    void setId(String id);
}
