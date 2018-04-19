package com.example.stripe.stripe_depended.converter.shipping;

import com.stripe.model.Address;
import com.example.stripe.api.model.shipping.AddressModel;
import com.example.stripe.stripe_depended.converter.Converter;
import com.example.stripe.impl.model.shipping.AddressFields;
import com.example.stripe.impl.model.shipping.AddressModelImpl;

import java.util.HashMap;
import java.util.Map;

public class AddressConverter implements Converter<AddressModel, Address>{

    @Override
    public Map<String, Object> convertModelToParams(AddressModel model) {
        Map<String, Object> params = new HashMap<>();

        if (model.getCountry() != null) {
            params.put(AddressFields.COUNTRY, model.getCountry());
        }
        if (model.getState() != null) {
            params.put(AddressFields.STATE, model.getState());
        }
        if (model.getCity() != null) {
            params.put(AddressFields.CITY, model.getCity());
        }
        if (model.getLine1() != null) {
            params.put(AddressFields.LINE1, model.getLine1());
        }
        if (model.getLine2() != null) {
            params.put(AddressFields.LINE2, model.getLine2());
        }
        if (model.getPostalCode() != null) {
            params.put(AddressFields.POSTAL_CODE, model.getPostalCode());
        }

        return params;
    }

    @Override
    public AddressModel convertParamsToModel(Map<String, Object> params) {
        AddressModel model = new AddressModelImpl();

        if (params.get(AddressFields.COUNTRY) != null) {
            model.setCountry((String) params.get(AddressFields.COUNTRY));
        }
        if (params.get(AddressFields.STATE) != null) {
            model.setState((String) params.get(AddressFields.STATE));
        }
        if (params.get(AddressFields.CITY) != null) {
            model.setCity((String) params.get(AddressFields.CITY));
        }
        if (params.get(AddressFields.LINE1) != null) {
            model.setLine1((String) params.get(AddressFields.LINE1));
        }
        if (params.get(AddressFields.LINE2) != null) {
            model.setLine2((String) params.get(AddressFields.LINE2));
        }
        if (params.get(AddressFields.POSTAL_CODE) != null) {
            model.setPostalCode((String) params.get(AddressFields.POSTAL_CODE));
        }

        return model;
    }

    @Override
    public AddressModel convertEntityToModel(Address entity) {
        Map<String, Object> params = new HashMap<>();

        if (entity.getCountry() != null) {
            params.put(AddressFields.COUNTRY, entity.getCountry());
        }
        if (entity.getState() != null) {
            params.put(AddressFields.STATE, entity.getState());
        }
        if (entity.getCity() != null) {
            params.put(AddressFields.CITY, entity.getCity());
        }
        if (entity.getLine1() != null) {
            params.put(AddressFields.LINE1, entity.getLine1());
        }
        if (entity.getLine2() != null) {
            params.put(AddressFields.LINE2, entity.getLine2());
        }
        if (entity.getPostalCode() != null) {
            params.put(AddressFields.POSTAL_CODE, entity.getPostalCode());
        }
        return convertParamsToModel(params);
    }
}
