package com.example.stripe.stripe_depended.converter.shipping;

import com.stripe.model.ShippingMethod;
import com.example.stripe.api.model.shipping.ShippingMethodModel;
import com.example.stripe.impl.model.shipping.ShippingMethodFields;
import com.example.stripe.impl.model.shipping.ShippingMethodModelImpl;
import com.example.stripe.stripe_depended.converter.Converter;

import java.util.HashMap;
import java.util.Map;

public class ShippingMethodConverter implements Converter<ShippingMethodModel, ShippingMethod> {
    @Override
    public Map<String, Object> convertModelToParams(ShippingMethodModel model) {
        Map<String, Object> params = new HashMap<>();
        if (model.getAmount() != null) {
            params.put(ShippingMethodFields.AMOUNT, model.getAmount());
        }
        if (model.getCurrency() != null) {
            params.put(ShippingMethodFields.CURRENCY, model.getCurrency());
        }
        if (model.getDescription() != null) {
            params.put(ShippingMethodFields.DESCRIPTION, model.getDescription());
        }
        if (model.getId() != null) {
            params.put(ShippingMethodFields.ID, model.getId());
        }
        return params;
    }

    @Override
    public ShippingMethodModel convertParamsToModel(Map<String, Object> params) {
        ShippingMethodModel model = new ShippingMethodModelImpl();

        if (params.get(ShippingMethodFields.AMOUNT) != null) {
            model.setAmount(Integer.parseInt(params.get(ShippingMethodFields.AMOUNT).toString()));
        }
        if (params.get(ShippingMethodFields.DESCRIPTION) != null) {
            model.setDescription((String) params.get(ShippingMethodFields.DESCRIPTION));
        }
        if (params.get(ShippingMethodFields.CURRENCY) != null) {
            model.setCurrency(params.get(ShippingMethodFields.CURRENCY).toString());
        }
        if (params.get(ShippingMethodFields.ID) != null) {
            model.setId((String) params.get(ShippingMethodFields.ID));
        }

        return model;
    }

    @Override
    public ShippingMethodModel convertEntityToModel(ShippingMethod entity) {
        Map<String, Object> params = new HashMap<>();

        if (entity.getId() != null) {
            params.put(ShippingMethodFields.ID, entity.getId());
        }
        if (entity.getAmount() != null) {
            params.put(ShippingMethodFields.AMOUNT, entity.getAmount());
        }
        if (entity.getCurrency() != null) {
            params.put(ShippingMethodFields.CURRENCY, entity.getCurrency());
        }
        if (entity.getDescription() != null) {
            params.put(ShippingMethodFields.DESCRIPTION, entity.getDescription());
        }
        return convertParamsToModel(params);
    }
}
