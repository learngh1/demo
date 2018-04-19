package com.example.stripe.stripe_depended.service;

import com.stripe.model.Order;
import com.stripe.net.RequestOptions;
import com.example.stripe.api.model.order.OrderModel;
import com.example.stripe.api.service.OrderService;
import com.example.stripe.stripe_depended.converter.Converter;
import com.example.stripe.stripe_depended.converter.order.OrderConverter;
import com.example.stripe.stripe_depended.converter.order.OrderFields;
import com.example.stripe.impl.model.StripeAuthProvider;
import com.example.stripe.stripe_depended.util.StripeRequestOptionsUtil;
import com.example.stripe.impl.util.ValidationUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO: extract interface
 */
public class OrderServiceImpl implements OrderService {
    private List<String> allowedFieldsForCreate = new ArrayList<>(Arrays.asList(
            OrderFields.CURRENCY,
            OrderFields.EMAIL,
            OrderFields.ITEMS,
            OrderFields.SHIPPING,
            OrderFields.METADATA
    ));

    private List<String> allowedFieldsForUpdate = new ArrayList<>(Arrays.asList(
            OrderFields.ID,
            OrderFields.SELECTED_SHIPPING_METHOD,
            OrderFields.SHIPPING,
            OrderFields.STATUS,
            OrderFields.METADATA
    ));

    private Converter<OrderModel, Order> converter = new OrderConverter();

    @Override
    public OrderModel createOrder(OrderModel model, StripeAuthProvider authProvider) throws Exception{
        ValidationUtil.validateRequired(OrderFields.CURRENCY, model.getCurrency());
        ValidationUtil.validateRequired(OrderFields.ITEMS, model.getItems());
        if (model.getItems().isEmpty()) {
            throw new IllegalStateException("There must be supplied at least one item");
        }

        Map<String, Object> params = converter.convertModelToParams(model);

        ValidationUtil.validateParamIsAllowed(params.keySet(), allowedFieldsForCreate);

        Order entity = Order.create(params, StripeRequestOptionsUtil.getOptionsByAuth(authProvider));

        return converter.convertEntityToModel(entity);
    }

    @Override
    public OrderModel retrieve(String id, StripeAuthProvider authProvider) throws Exception {
        Order entity = Order.retrieve(id, StripeRequestOptionsUtil.getOptionsByAuth(authProvider));
        return converter.convertEntityToModel(entity);
    }

    @Override
    public OrderModel pay(String orderId, String paymentToken, String email, StripeAuthProvider authProvider, Integer applicationFee) throws Exception{
        ValidationUtil.validateRequired("orderId", orderId);
        ValidationUtil.validateRequired("paymentToken", paymentToken);
        ValidationUtil.validateRequired("email", email);

        RequestOptions requestOptions = StripeRequestOptionsUtil.getOptionsByAuth(authProvider);
        Order entity = Order.retrieve(orderId, requestOptions);
        Map<String, Object> paymentParams = new HashMap<>();
        paymentParams.put("email", email);
        paymentParams.put("source", paymentToken);
        if (applicationFee != null) {
            paymentParams.put("application_fee", applicationFee);
        }
        entity = entity.pay(paymentParams, requestOptions);

        return converter.convertEntityToModel(entity);
    }

    @Override
    public OrderModel updateOrder(OrderModel model, StripeAuthProvider authProvider) throws Exception {
        Map<String, Object> params = converter.convertModelToParams(model);
        ValidationUtil.validateParamIsAllowed(params.keySet(), allowedFieldsForUpdate);
        RequestOptions requestOptions = StripeRequestOptionsUtil.getOptionsByAuth(authProvider);
        Order entity = Order.retrieve(model.getId(), requestOptions);
        entity = entity.update(params, requestOptions);
        return converter.convertEntityToModel(entity);
    }
}
