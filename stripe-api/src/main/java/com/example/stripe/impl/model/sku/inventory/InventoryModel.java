package com.example.stripe.impl.model.sku.inventory;

import java.io.Serializable;

public class InventoryModel implements Serializable {
    private Integer quantity;
    private InventoryType type;
    private InventoryValue value;

    public InventoryModel(){}

    public InventoryModel(Integer quantity, InventoryType type, InventoryValue value) {
        this.quantity = quantity;
        this.type = type;
        this.value = value;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public InventoryType getType() {
        return type;
    }

    public void setType(InventoryType type) {
        this.type = type;
    }

    public InventoryValue getValue() {
        return value;
    }

    public void setValue(InventoryValue value) {
        this.value = value;
    }
}
