package com.example.stripe.impl.model.sku;

import com.example.stripe.api.model.shipping.PackageDimensionsModel;
import com.example.stripe.api.model.sku.SKUModel;
import com.example.stripe.impl.model.BaseModelImpl;
import com.example.stripe.impl.model.sku.inventory.InventoryModel;

import java.util.Map;

public class SKUModelImpl extends BaseModelImpl implements SKUModel {
    private String currency;
    private InventoryModel inventory;
    private Integer price;
    private String productId;
    private Map<String, String> attributes;
    private PackageDimensionsModel packageDimensions;
    private Map<String, String> metadata;

    @Override
    public String getCurrency() {
        return currency;
    }

    @Override
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public InventoryModel getInventory() {
        return inventory;
    }

    @Override
    public void setInventory(InventoryModel inventory) {
        this.inventory = inventory;
    }

    @Override
    public Integer getPrice() {
        return price;
    }

    @Override
    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String getProductId() {
        return productId;
    }

    @Override
    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public Map<String, String> getAttributes() {
        return attributes;
    }

    @Override
    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    @Override
    public PackageDimensionsModel getPackageDimensions() {
        return packageDimensions;
    }

    @Override
    public void setPackageDimensions(PackageDimensionsModel packageDimensions) {
        this.packageDimensions = packageDimensions;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }
}
