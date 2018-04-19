package com.example.stripe.stripe_depended.service;

import com.stripe.model.Charge;
import com.stripe.net.RequestOptions;
import com.example.stripe.impl.model.StripeAuthProvider;
import com.example.stripe.stripe_depended.util.StripeRequestOptionsUtil;

/**
 * @author atrotsenko
 * created on 07.04.2018
 */
public class ChargeServiceImpl {
    public Charge retrieve(String id, StripeAuthProvider authProvider) throws Exception {
        RequestOptions requestOptions = StripeRequestOptionsUtil.getOptionsByAuth(authProvider);
        return Charge.retrieve(id, requestOptions);
    }
}
