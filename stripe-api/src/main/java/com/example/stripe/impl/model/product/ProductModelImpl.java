package com.example.stripe.impl.model.product;

import com.example.stripe.api.model.product.ProductType;
import com.example.stripe.api.model.product.ProductModel;
import com.example.stripe.api.model.shipping.PackageDimensionsModel;
import com.example.stripe.impl.model.BaseModelImpl;

import java.util.List;

public class ProductModelImpl extends BaseModelImpl implements ProductModel {
    private String name;
    private ProductType type;
    private String description;
    private List<String> attributes;
    private boolean shippable;
    private PackageDimensionsModel packageDimensions;

    public ProductModelImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public ProductType getType() {
        return type;
    }

    @Override
    public void setType(ProductType type) {
        this.type = type;
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
    public List<String> getAttributes() {
        return attributes;
    }

    @Override
    public void setAttributes(List<String> attributes) {
        this.attributes = attributes;
    }

    @Override
    public boolean isShippable() {
        return shippable;
    }

    @Override
    public void setShippable(boolean shippable) {
        this.shippable = shippable;
    }

    @Override
    public PackageDimensionsModel getPackageDimensions() {
        return packageDimensions;
    }

    @Override
    public void setPackageDimensions(PackageDimensionsModel packageDimensions) {
        this.packageDimensions = packageDimensions;
    }
}
