package com.example.stripe.stripe_depended.converter.order;

import com.stripe.model.OrderItem;
import com.example.stripe.api.model.order.OrderItemModel;
import com.example.stripe.api.model.order.OrderItemType;
import com.example.stripe.impl.model.order.OrderItemModelImpl;
import com.example.stripe.stripe_depended.converter.Converter;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class OrderItemConverter implements Converter<OrderItemModel, OrderItem> {
    @Override
    public Map<String, Object> convertModelToParams(OrderItemModel model) {
        Map<String, Object> params = new LinkedHashMap<>();

        if (model.getType() != null) {
            params.put(OrderItemFields.TYPE, model.getType().name().toLowerCase());
        }

        if (model.getQuantity() != null) {
            if (model.getType() == null || !model.getType().equals(OrderItemType.SKU)) {
                throw new IllegalStateException("quantity can be present only if type is " + OrderItemType.SKU);
            }
            params.put(OrderItemFields.QUANTITY, model.getQuantity().toString());
        }

        if (model.getAmount() != null) {
            params.put(OrderItemFields.AMOUNT, model.getAmount().longValue());
        }
        if (model.getCurrency() != null) {
            params.put(OrderItemFields.CURRENCY, model.getCurrency());
        }
        if (model.getDescription() != null) {
            params.put(OrderItemFields.DESCRIPTION, model.getDescription());
        }
        if (model.getParentId() != null) {
            params.put(OrderItemFields.PARENT, model.getParentId());
        }

        return params;
    }

    @Override
    public OrderItemModel convertParamsToModel(Map<String, Object> params) {
        OrderItemModel model = new OrderItemModelImpl();
        if (params.get(OrderItemFields.TYPE) != null) {
            model.setType(OrderItemType.valueOf(((String) params.get(OrderItemFields.TYPE)).toUpperCase()));
        }
        if (params.get(OrderItemFields.PARENT) != null) {
            model.setParentId((String) params.get(OrderItemFields.PARENT));
        }
        if (params.get(OrderItemFields.DESCRIPTION) != null) {
            model.setDescription((String) params.get(OrderItemFields.DESCRIPTION));
        }
        if (params.get(OrderItemFields.CURRENCY) != null) {
            String currencyString = (String) params.get(OrderItemFields.CURRENCY);
            model.setCurrency(currencyString);
        }
        if (params.get(OrderItemFields.AMOUNT) != null) {
            model.setAmount(Integer.parseInt(params.get(OrderItemFields.AMOUNT).toString()));
        }
        if (params.get(OrderItemFields.QUANTITY) != null) {
            model.setQuantity(Integer.parseInt(params.get(OrderItemFields.QUANTITY).toString()));
        }

        return model;
    }

    @Override
    public OrderItemModel convertEntityToModel(OrderItem entity) {
        Map<String, Object> params = new HashMap<>();
        if (entity.getAmount() != null) {
            params.put(OrderItemFields.AMOUNT, entity.getAmount());
        }
        if (entity.getCurrency() != null) {
            params.put(OrderItemFields.CURRENCY, entity.getCurrency());
        }
        if (entity.getDescription() != null) {
            params.put(OrderItemFields.DESCRIPTION, entity.getDescription());
        }
        if (entity.getParent() != null) {
            params.put(OrderItemFields.PARENT, entity.getParent());
        }
        if (entity.getQuantity() != null) {
            params.put(OrderItemFields.QUANTITY, entity.getQuantity());
        }
        if (entity.getType() != null) {
            params.put(OrderItemFields.TYPE, entity.getType());
        }
        return convertParamsToModel(params);
    }
}
