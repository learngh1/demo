package com.example.stripe;

import com.example.stripe.impl.model.order.OrderModelImpl;
import org.junit.Test;

public class OrderModelTest {
    @Test(expected = IllegalArgumentException.class)
    public void testAmountPositiveValidatoin() {
        new OrderModelImpl().setAmount(-1);
    }
}
