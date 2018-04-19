package com.example.stripe.stripe_depended.service;

import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Product;
import com.stripe.net.RequestOptions;
import com.example.stripe.api.model.product.ProductModel;
import com.example.stripe.api.service.ProductService;
import com.example.stripe.stripe_depended.converter.Converter;
import com.example.stripe.stripe_depended.converter.product.ProductConverter;
import com.example.stripe.impl.model.StripeAuthProvider;
import com.example.stripe.impl.model.product.ProductFields;
import com.example.stripe.stripe_depended.util.StripeRequestOptionsUtil;
import com.example.stripe.impl.util.ValidationUtil;

import java.util.Map;

public class ProductServiceImpl implements ProductService {
    //TODO: should be injected by Spring
    private Converter<ProductModel, Product> converter = new ProductConverter();

    @Override
    public ProductModel create(ProductModel model, StripeAuthProvider auth) throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException {
        Map<String, Object> params = converter.convertModelToParams(model);
        ValidationUtil.validateRequired(ProductFields.TYPE, params.get(ProductFields.TYPE));
        Product product = Product.create(params, StripeRequestOptionsUtil.getOptionsByAuth(auth));
        return converter.convertEntityToModel(product);
    }

    @Override
    public ProductModel retrieve(String id, StripeAuthProvider authProvider) throws Exception {
        Product product = Product.retrieve(id, StripeRequestOptionsUtil.getOptionsByAuth(authProvider));
        return converter.convertEntityToModel(product);
    }

    @Override
    public ProductModel update(ProductModel model, StripeAuthProvider authProvider) throws Exception {
        RequestOptions requestOptions = StripeRequestOptionsUtil.getOptionsByAuth(authProvider);
        Product product = Product.retrieve(model.getId(), requestOptions);
        Map<String, Object> updatedParams = converter.convertModelToParams(model);
        updatedParams.remove(ProductFields.ID);
        return converter.convertEntityToModel(product.update(updatedParams, requestOptions));
    }
}
