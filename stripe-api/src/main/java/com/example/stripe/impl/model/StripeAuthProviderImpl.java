package com.example.stripe.impl.model;

public class StripeAuthProviderImpl extends StripeAuthProvider {
    private String apiKey;
    private String connectedStripeAccountUserId;

    public StripeAuthProviderImpl(String apiKey) {
        if (apiKey == null) {
            throw new IllegalArgumentException("apiKey must be not null");
        }
        this.apiKey = apiKey;
    }

    public StripeAuthProviderImpl(String apiKey, String connectedStripeAccountUserId) {
        this(apiKey);
        this.connectedStripeAccountUserId = connectedStripeAccountUserId;
    }

    @Override
    public String getApiKey() {
        return apiKey;
    }

    @Override
    public String getConnectedStripeAccountUserId() {
        return connectedStripeAccountUserId;
    }
}
