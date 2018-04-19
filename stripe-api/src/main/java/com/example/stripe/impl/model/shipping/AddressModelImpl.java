package com.example.stripe.impl.model.shipping;

import com.example.stripe.api.model.shipping.AddressModel;
import com.example.stripe.impl.model.BaseModelImpl;

import java.util.Objects;

public class AddressModelImpl extends BaseModelImpl implements AddressModel {
    private String country;
    private String state;
    private String city;
    private String line1;
    private String line2;
    private String postalCode;

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String getState() {
        return state;
    }

    @Override
    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String getLine1() {
        return line1;
    }

    @Override
    public void setLine1(String line1) {
        this.line1 = line1;
    }

    @Override
    public String getLine2() {
        return line2;
    }

    @Override
    public void setLine2(String line2) {
        this.line2 = line2;
    }

    @Override
    public String getPostalCode() {
        return postalCode;
    }

    @Override
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "AddressModelImpl{" +
                "country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", line1='" + line1 + '\'' +
                ", line2='" + line2 + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressModelImpl that = (AddressModelImpl) o;
        return Objects.equals(country, that.country) &&
                Objects.equals(state, that.state) &&
                Objects.equals(city, that.city) &&
                Objects.equals(line1, that.line1) &&
                Objects.equals(line2, that.line2) &&
                Objects.equals(postalCode, that.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, state, city, line1, line2, postalCode);
    }
}
