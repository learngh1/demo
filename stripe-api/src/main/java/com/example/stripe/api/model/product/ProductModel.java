package com.example.stripe.api.model.product;

import com.example.stripe.api.model.BaseModel;
import com.example.stripe.api.model.shipping.PackageDimensionsModel;

import java.util.List;

public interface ProductModel extends BaseModel {
    String getName();
    void setName(String name);

    ProductType getType();
    void setType(ProductType type);

    String getDescription();
    void setDescription(String description);

    List<String> getAttributes();
    void setAttributes(List<String> attributes);

    boolean isShippable();
    void setShippable(boolean shippable);

    PackageDimensionsModel getPackageDimensions();
    void setPackageDimensions(PackageDimensionsModel packageDimensions);
}
