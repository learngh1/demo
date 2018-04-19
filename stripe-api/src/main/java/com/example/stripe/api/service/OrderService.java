package com.example.stripe.api.service;

import com.example.stripe.api.model.order.OrderModel;
import com.example.stripe.impl.model.StripeAuthProvider;

public interface OrderService {
    OrderModel createOrder(OrderModel model, StripeAuthProvider authProvider) throws Exception;

    OrderModel retrieve(String id, StripeAuthProvider authProvider) throws Exception;

    OrderModel pay(String orderId, String paymentToken, String email, StripeAuthProvider authProvider, Integer applicationFee) throws Exception;

    OrderModel updateOrder(OrderModel model, StripeAuthProvider authProvider) throws Exception;
}
