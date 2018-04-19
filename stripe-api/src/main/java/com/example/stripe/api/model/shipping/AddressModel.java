package com.example.stripe.api.model.shipping;

import com.example.stripe.api.model.BaseModel;

public interface AddressModel extends BaseModel {
    String getCountry();
    void setCountry(String country);

    String getState();
    void setState(String state);

    String getCity();
    void setCity(String city);

    String getLine1();
    void setLine1(String line1);

    String getLine2();
    void setLine2(String line2);

    String getPostalCode();
    void setPostalCode(String postalCode);
}
