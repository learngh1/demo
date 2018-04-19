package com.example.stripe.api.model.shipping;

import com.example.stripe.api.model.BaseModel;

public interface PackageDimensionsModel extends BaseModel{
    Double getHeight();
    void setHeight(Double height);

    Double getLength();
    void setLength(Double length);

    Double getWidth();
    void setWidth(Double width);

    Double getWeight();
    void setWeight(Double weight);
}
