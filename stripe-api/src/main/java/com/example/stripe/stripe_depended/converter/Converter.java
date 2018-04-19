package com.example.stripe.stripe_depended.converter;

import com.example.stripe.api.model.BaseModel;

import java.util.Map;

public interface Converter<T extends BaseModel, E> {
    Map<String, Object> convertModelToParams(T model);
    T convertParamsToModel(Map<String, Object> params);
    T convertEntityToModel(E entity);
}
