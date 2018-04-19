package com.example.stripe;

import com.example.stripe.api.model.order.OrderItemModel;
import com.example.stripe.api.model.order.OrderItemType;
import com.example.stripe.impl.model.order.OrderItemModelImpl;
import org.junit.Test;

public class OrderItemModelTest {
    @Test(expected = IllegalArgumentException.class)
    public void testAmountPositiveValidatoinIfNotDiscount() {
        OrderItemModel item = new OrderItemModelImpl();
        item.setType(OrderItemType.SKU);
        item.setAmount(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAmountNegativeValidatoinIfDiscount() {
        OrderItemModel item = new OrderItemModelImpl();
        item.setType(OrderItemType.DISCOUNT);
        item.setAmount(10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQuantityPositiveValidatoin() {
        new OrderItemModelImpl().setQuantity(-1);
    }
}
