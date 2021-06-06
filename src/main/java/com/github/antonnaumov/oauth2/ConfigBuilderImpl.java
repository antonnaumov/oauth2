package com.github.antonnaumov.oauth2;

import java.util.HashSet;
import java.util.Set;

/**
 * OAuth2 configuration build default implementation.
 */
final class ConfigBuilderImpl implements Config.Builder {
    private String clientID;
    private String clientSecret;
    private String redirectURL;
    private Transport transport;
    private Decoder decoder;
    private final Set<String> scopes = new HashSet<>();
    private Endpoint endpoint;

    /**
     * Creates empty builder object.
     */
    ConfigBuilderImpl() {}

    /**
     * {@inheritDoc}
     */
    @Override
    public Config.Builder withClientCredentials(final String clientID, final String clientSecret) {
        this.clientID = clientID;
        this.clientSecret = clientSecret;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Config.Builder withEndpoint(final Endpoint endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Config.Builder withRedirectURL(final String redirectURL) {
        this.redirectURL = redirectURL;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Config.Builder withTransport(final Transport transport) {
        this.transport = transport;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Config.Builder withDecoder(final Decoder decoder) {
        this.decoder = decoder;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Config.Builder withScope(final String scope) {
        this.scopes.add(scope);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Config build() throws ConfigurationParameterMissingException {
        if (clientID == null || clientID.trim().length() == 0) {
            throw new ConfigurationParameterMissingException("clientID");
        }
        if (clientSecret == null || clientSecret.trim().length() == 0) {
            throw new ConfigurationParameterMissingException("clientSecret");
        }
        if (endpoint == null) {
            throw new ConfigurationParameterMissingException("endpoint");
        }
        if (transport == null) {
            throw new ConfigurationParameterMissingException("transport");
        }
        if (decoder == null) {
            throw new ConfigurationParameterMissingException("decoder");
        }
        return new Config(clientID, clientSecret, endpoint, redirectURL, scopes, transport, decoder);
    }
}
