package com.example.stripe.impl.model.shipping;

import com.example.stripe.api.model.shipping.PackageDimensionsModel;
import com.example.stripe.impl.model.BaseModelImpl;

public class PackageDimensionsModelImpl extends BaseModelImpl implements PackageDimensionsModel {
    private Double height;
    private Double length;
    private Double width;
    private Double weight;

    public PackageDimensionsModelImpl() {}

    public PackageDimensionsModelImpl(Double height, Double length, Double width, Double weight) {
        this.height = height;
        this.length = length;
        this.width = width;
        this.weight = weight;
    }

    @Override
    public Double getHeight() {
        return height;
    }

    @Override
    public void setHeight(Double height) {
        this.height = height;
    }

    @Override
    public Double getLength() {
        return length;
    }

    @Override
    public void setLength(Double length) {
        this.length = length;
    }

    @Override
    public Double getWidth() {
        return width;
    }

    @Override
    public void setWidth(Double width) {
        this.width = width;
    }

    @Override
    public Double getWeight() {
        return weight;
    }

    @Override
    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
