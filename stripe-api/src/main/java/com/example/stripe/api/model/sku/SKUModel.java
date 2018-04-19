package com.example.stripe.api.model.sku;

import com.example.stripe.api.model.BaseModel;
import com.example.stripe.api.model.shipping.PackageDimensionsModel;
import com.example.stripe.impl.model.sku.inventory.InventoryModel;

import java.util.Map;

public interface SKUModel extends BaseModel {
    String getCurrency();
    void setCurrency(String currency);

    InventoryModel getInventory();
    void setInventory(InventoryModel inventory);

    Integer getPrice();
    void setPrice(Integer price);

    String getProductId();
    void setProductId(String productId);

    Map<String, String> getAttributes();
    void setAttributes(Map<String, String> attributes);

    PackageDimensionsModel getPackageDimensions();
    void setPackageDimensions(PackageDimensionsModel packageDimensions);

    Map<String, String> getMetadata();
    void setMetadata(Map<String, String> metadata);
}
