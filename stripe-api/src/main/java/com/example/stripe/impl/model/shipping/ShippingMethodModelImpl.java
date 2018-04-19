package com.example.stripe.impl.model.shipping;

import com.example.stripe.api.model.shipping.ShippingMethodModel;
import com.example.stripe.impl.model.BaseModelImpl;

public class ShippingMethodModelImpl extends BaseModelImpl implements ShippingMethodModel {
    private Integer amount;
    private String description;
    private String currency;

    @Override
    public Integer getAmount() {
        return amount;
    }

    @Override
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getCurrency() {
        return currency;
    }

    @Override
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return super.toString() + ": id=" + getId() + ", amount=" + amount + ", currency=" + currency + ", description=" + description;
    }
}
