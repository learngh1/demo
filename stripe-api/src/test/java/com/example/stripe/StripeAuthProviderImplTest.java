package com.example.stripe;

import com.example.stripe.impl.model.StripeAuthProviderImpl;
import org.junit.Test;

public class StripeAuthProviderImplTest {
    @Test(expected = IllegalArgumentException.class)
    public void testApiKeyRequiredValidation() {
        new StripeAuthProviderImpl(null);
    }
}
