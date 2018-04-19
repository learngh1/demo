package com.example.stripe.impl.model.shipping;

import com.example.stripe.api.model.shipping.AddressModel;
import com.example.stripe.api.model.shipping.ShippingDetailsModel;
import com.example.stripe.impl.model.BaseModelImpl;

public class ShippingDetailsModelImpl extends BaseModelImpl implements ShippingDetailsModel {
    private String name;
    private String phone;
    private AddressModel address;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public AddressModel getAddress() {
        return address;
    }

    @Override
    public void setAddress(AddressModel address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "ShippingDetailsModelImpl{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address=" + address +
                '}';
    }
}
