package com.example.stripe.stripe_depended.util;

import com.stripe.net.RequestOptions;
import com.example.stripe.impl.model.StripeAuthProvider;

public class StripeRequestOptionsUtil {
    public static RequestOptions getOptionsByAuth(StripeAuthProvider authProvider) {
        if (authProvider.getApiKey() == null) {
            throw new IllegalArgumentException("authProvider must provide apiKey");
        }
        RequestOptions.RequestOptionsBuilder builder = RequestOptions.builder();
        builder.setApiKey(authProvider.getApiKey());
        if (authProvider.getConnectedStripeAccountUserId() != null) {
            builder.setStripeAccount(authProvider.getConnectedStripeAccountUserId());
        }
        return builder.build();
    }
}
