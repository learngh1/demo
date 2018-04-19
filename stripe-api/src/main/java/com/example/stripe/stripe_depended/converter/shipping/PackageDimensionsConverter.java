package com.example.stripe.stripe_depended.converter.shipping;

import com.stripe.model.PackageDimensions;
import com.example.stripe.api.model.shipping.PackageDimensionsModel;
import com.example.stripe.stripe_depended.converter.Converter;
import com.example.stripe.impl.model.shipping.PackageDimensionsFields;
import com.example.stripe.impl.model.shipping.PackageDimensionsModelImpl;

import java.util.HashMap;
import java.util.Map;

public class PackageDimensionsConverter implements Converter<PackageDimensionsModel, PackageDimensions> {
    @Override
    public Map<String, Object> convertModelToParams(PackageDimensionsModel model) {
        Map<String, Object> params = new HashMap<>();
        if (model.getHeight() != null) {
            params.put(PackageDimensionsFields.HEIGHT, model.getHeight());
        }
        if (model.getLength() != null) {
            params.put(PackageDimensionsFields.LENGTH, model.getLength());
        }
        if (model.getWidth() != null) {
            params.put(PackageDimensionsFields.WIDTH, model.getWidth());
        }
        if (model.getWeight() != null) {
            params.put(PackageDimensionsFields.WEIGHT, model.getWeight());
        }
        return params;
    }

    @Override
    public PackageDimensionsModel convertParamsToModel(Map<String, Object> params) {
        PackageDimensionsModel model = new PackageDimensionsModelImpl();
        if (params.get(PackageDimensionsFields.HEIGHT) != null) {
            model.setHeight(Double.parseDouble(params.get(PackageDimensionsFields.HEIGHT).toString()));
        }
        if (params.get(PackageDimensionsFields.LENGTH) != null) {
            model.setLength(Double.parseDouble(params.get(PackageDimensionsFields.LENGTH).toString()));
        }
        if (params.get(PackageDimensionsFields.WIDTH) != null) {
            model.setWidth(Double.parseDouble(params.get(PackageDimensionsFields.WIDTH).toString()));
        }
        if (params.get(PackageDimensionsFields.WEIGHT) != null) {
            model.setWeight(Double.parseDouble(params.get(PackageDimensionsFields.WEIGHT).toString()));
        }
        return model;
    }

    @Override
    public PackageDimensionsModel convertEntityToModel(PackageDimensions entity) {
        Map<String, Object> params = new HashMap<>();
        if (entity.getHeight() != null) {
            params.put(PackageDimensionsFields.HEIGHT, entity.getHeight());
        }
        if (entity.getLength() != null) {
            params.put(PackageDimensionsFields.LENGTH, entity.getLength());
        }
        if (entity.getWidth() != null) {
            params.put(PackageDimensionsFields.WIDTH, entity.getWidth());
        }
        if (entity.getWeight() != null) {
            params.put(PackageDimensionsFields.WEIGHT, entity.getWeight());
        }
        return convertParamsToModel(params);
    }
}
