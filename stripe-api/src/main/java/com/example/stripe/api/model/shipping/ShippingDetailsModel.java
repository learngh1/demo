package com.example.stripe.api.model.shipping;

import com.example.stripe.api.model.BaseModel;

public interface ShippingDetailsModel extends BaseModel{
    String getName();
    void setName(String name);

    String getPhone();
    void setPhone(String phone);

    AddressModel getAddress();
    void setAddress(AddressModel addressModel);
}
