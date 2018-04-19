package com.example.stripe.api.model.shipping;

import com.example.stripe.api.model.BaseModel;

public interface ShippingMethodModel extends BaseModel{
    Integer getAmount();
    void setAmount(Integer amount);

    String getDescription();
    void setDescription(String description);

    String getCurrency();
    void setCurrency(String currency);
}
