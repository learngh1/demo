package com.example.stripe.api.service;

import com.example.stripe.api.model.product.ProductModel;
import com.example.stripe.impl.model.StripeAuthProvider;

public interface ProductService {
    ProductModel create(ProductModel model, StripeAuthProvider authProvider) throws Exception;
    ProductModel retrieve(String id, StripeAuthProvider authProvider) throws Exception;
    ProductModel update(ProductModel model, StripeAuthProvider authProvider) throws Exception;

    //WTF? there is no delete method in 1.40.1 version of Stripe API
    //void delete(String id) throws Exception;
}
