package com.example.stripe.stripe_depended.service;

import com.stripe.model.SKU;
import com.stripe.net.RequestOptions;
import com.example.stripe.api.model.sku.SKUModel;
import com.example.stripe.api.service.SKUService;
import com.example.stripe.stripe_depended.converter.Converter;
import com.example.stripe.stripe_depended.converter.sku.SKUConverter;
import com.example.stripe.impl.model.StripeAuthProvider;
import com.example.stripe.impl.model.sku.SKUFields;
import com.example.stripe.stripe_depended.util.StripeRequestOptionsUtil;
import com.example.stripe.impl.util.ValidationUtil;

import java.util.Map;

public class SKUServiceImpl implements SKUService {

    //TODO: should be injected by Spring
    private Converter<SKUModel, SKU> converter = new SKUConverter();

    @Override
    public SKUModel create(SKUModel skuModel, StripeAuthProvider authProvider) throws Exception {
        ValidationUtil.validateRequired(SKUFields.CURRENCY, skuModel.getCurrency());
        ValidationUtil.validateRequired(SKUFields.INVENTORY, skuModel.getInventory());
        ValidationUtil.validateRequired(SKUFields.PRICE, skuModel.getPrice());
        ValidationUtil.validateRequired(SKUFields.PRODUCT, skuModel.getProductId());

        Map<String, Object> params = converter.convertModelToParams(skuModel);

        SKU sku = SKU.create(params, StripeRequestOptionsUtil.getOptionsByAuth(authProvider));

        return converter.convertEntityToModel(sku);
    }

    @Override
    public SKUModel retrieve(String id, StripeAuthProvider authProvider) throws Exception {
        SKU sku = SKU.retrieve(id, StripeRequestOptionsUtil.getOptionsByAuth(authProvider));
        return converter.convertEntityToModel(sku);
    }

    @Override
    public SKUModel update(SKUModel skuModel, StripeAuthProvider authProvider) throws Exception {
        RequestOptions requestOptions = StripeRequestOptionsUtil.getOptionsByAuth(authProvider);
        SKU sku = SKU.retrieve(skuModel.getId(), requestOptions);
        Map<String, Object> params = converter.convertModelToParams(skuModel);
        params.remove(SKUFields.ID);
        return converter.convertEntityToModel(sku.update(params, requestOptions));
    }
}
