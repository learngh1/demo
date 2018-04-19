package com.example.stripe.stripe_depended.converter.shipping;

import com.stripe.model.Address;
import com.stripe.model.ShippingDetails;
import com.example.stripe.api.model.shipping.AddressModel;
import com.example.stripe.api.model.shipping.ShippingDetailsModel;
import com.example.stripe.stripe_depended.converter.Converter;
import com.example.stripe.impl.model.shipping.ShippingDetailsFields;
import com.example.stripe.impl.model.shipping.ShippingDetailsModelImpl;
import com.example.stripe.impl.util.ValidationUtil;

import java.util.HashMap;
import java.util.Map;

public class ShippingDetailsConverter implements Converter<ShippingDetailsModel, ShippingDetails> {

    private Converter<AddressModel, Address> addressConverter = new AddressConverter();

    @Override
    public Map<String, Object> convertModelToParams(ShippingDetailsModel model) {
        ValidationUtil.validateRequired(ShippingDetailsFields.ADDRESS, model.getAddress());
        Map<String, Object> params = new HashMap<>();

        params.put(ShippingDetailsFields.ADDRESS, addressConverter.convertModelToParams(model.getAddress()));

        if (model.getName() != null) {
            params.put(ShippingDetailsFields.NAME, model.getName());
        }
        if (model.getPhone() != null) {
            params.put(ShippingDetailsFields.PHONE, model.getPhone());
        }

        return params;
    }

    @Override
    public ShippingDetailsModel convertParamsToModel(Map<String, Object> params) {
        ShippingDetailsModel model = new ShippingDetailsModelImpl();
        if (params.get(ShippingDetailsFields.ADDRESS) != null) {
            model.setAddress(addressConverter.convertParamsToModel((Map<String, Object>) params.get(ShippingDetailsFields.ADDRESS)));
        }
        if (params.get(ShippingDetailsFields.NAME) != null) {
            model.setName((String) params.get(ShippingDetailsFields.NAME));
        }
        if (params.get(ShippingDetailsFields.PHONE) != null) {
            model.setPhone((String) params.get(ShippingDetailsFields.PHONE));
        }

        return model;
    }

    @Override
    public ShippingDetailsModel convertEntityToModel(ShippingDetails entity) {
        Map<String, Object> params = new HashMap<>();

        if (entity.getName() != null) {
            params.put(ShippingDetailsFields.NAME, entity.getName());
        }
        if (entity.getPhone() != null) {
            params.put(ShippingDetailsFields.PHONE, entity.getPhone());
        }

        ShippingDetailsModel model = convertParamsToModel(params);
        model.setAddress(addressConverter.convertEntityToModel(entity.getAddress()));
        return model;
    }
}
