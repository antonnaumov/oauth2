package com.github.antonnaumov.oauth2;

import java.util.HashSet;
import java.util.Set;

final class ConfigBuilderImpl implements Config.Builder {
    private String clientID = "";
    private String clientSecret = "";
    private String redirectURL = "";
    private Transport transport;
    private final Set<String> scopes = new HashSet<>();
    private Endpoint endpoint;

    ConfigBuilderImpl() {}

    @Override
    public Config.Builder withClientCredentials(final String clientID, final String clientSecret) {
        this.clientID = clientID;
        this.clientSecret = clientSecret;
        return this;
    }

    @Override
    public Config.Builder withEndpoint(final Endpoint endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    @Override
    public Config.Builder withRedirectURL(final String redirectURL) {
        this.redirectURL = redirectURL;
        return this;
    }

    @Override
    public Config.Builder withTransport(final Transport transport) {
        this.transport = transport;
        return this;
    }

    @Override
    public Config.Builder withScope(final String scope) {
        this.scopes.add(scope);
        return this;
    }

    @Override
    public Config build() {
        final var cfg = new Config(clientID, clientSecret, endpoint, transport, redirectURL, scopes);

        clientID = "";
        clientSecret = "";
        endpoint = null;
        transport = null;
        redirectURL = "";
        scopes.clear();

        return cfg;
    }
}
