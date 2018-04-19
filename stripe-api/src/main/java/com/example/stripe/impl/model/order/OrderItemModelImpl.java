package com.example.stripe.impl.model.order;

import com.example.stripe.api.model.order.OrderItemModel;
import com.example.stripe.api.model.order.OrderItemType;
import com.example.stripe.impl.model.BaseModelImpl;
import com.example.stripe.impl.util.ValidationUtil;

public class OrderItemModelImpl extends BaseModelImpl implements OrderItemModel {
    /**
     * must be a positive integer
     */
    private Integer amount;
    private String currency;
    private String description;
    private String parentId;
    /**
     * present only if type is "sku", must be a positive integer
     */
    private Integer quantity;
    private OrderItemType type;

    @Override
    public Integer getAmount() {
        return amount;
    }

    @Override
    public void setAmount(Integer amount) {
        if (type.equals(OrderItemType.DISCOUNT)) {
            ValidationUtil.validateNegative(amount);
        } else {
            ValidationUtil.validatePositive(amount);
        }
        this.amount = amount;
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
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getParentId() {
        return parentId;
    }

    @Override
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(Integer quantity) {
        ValidationUtil.validatePositive(quantity);
        this.quantity = quantity;
    }

    @Override
    public OrderItemType getType() {
        return type;
    }

    @Override
    public void setType(OrderItemType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "OrderItemModelImpl{" +
                "amount=" + amount +
                ", currency=" + currency +
                ", description='" + description + '\'' +
                ", parentId='" + parentId + '\'' +
                ", quantity=" + quantity +
                ", type=" + type +
                '}';
    }
}
