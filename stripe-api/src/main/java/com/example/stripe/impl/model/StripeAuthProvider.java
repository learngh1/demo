package com.example.stripe.impl.model;

public abstract class StripeAuthProvider {
    public abstract String getApiKey();
    public abstract String getConnectedStripeAccountUserId();
}
