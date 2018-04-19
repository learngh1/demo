package com.example.stripe.api.service;

import com.example.stripe.api.model.sku.SKUModel;
import com.example.stripe.impl.model.StripeAuthProvider;

public interface SKUService {
    SKUModel create(SKUModel skuModel, StripeAuthProvider authProvider) throws Exception;
    SKUModel retrieve(String id, StripeAuthProvider authProvider) throws Exception;
    SKUModel update(SKUModel skuModel, StripeAuthProvider authProvider) throws Exception;
}
