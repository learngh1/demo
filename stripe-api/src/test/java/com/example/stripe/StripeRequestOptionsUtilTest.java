package com.example.stripe;

import com.example.stripe.impl.model.StripeAuthProvider;
import com.example.stripe.stripe_depended.util.StripeRequestOptionsUtil;
import org.junit.Test;

public class StripeRequestOptionsUtilTest {
    @Test(expected = IllegalArgumentException.class)
    public void testApiKeyRequiredValidation() {
        StripeRequestOptionsUtil.getOptionsByAuth(new StripeAuthProvider() {
            @Override
            public String getApiKey() {
                return null;
            }

            @Override
            public String getConnectedStripeAccountUserId() {
                return null;
            }
        });
    }
}
