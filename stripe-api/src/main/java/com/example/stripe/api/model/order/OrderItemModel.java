package com.example.stripe.api.model.order;

import com.example.stripe.api.model.BaseModel;

public interface OrderItemModel extends BaseModel {
    Integer getAmount();
    void setAmount(Integer amount);

    String getCurrency();
    void setCurrency(String currency);

    String getDescription();
    void setDescription(String description);

    String getParentId();
    void setParentId(String parentId);

    Integer getQuantity();
    void setQuantity(Integer quantity);

    OrderItemType getType();
    void setType(OrderItemType type);
}