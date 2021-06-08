package com.github.antonnaumov.oauth2.clientcredentials;

import com.github.antonnaumov.oauth2.AuthStyle;
import com.github.antonnaumov.oauth2.ConfigurationParameterMissingException;
import com.github.antonnaumov.oauth2.Decoder;
import com.github.antonnaumov.oauth2.Transport;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * OAuth2 configuration for the provider client credentials builder implementation.
 */
final class ConfigBuilderImpl implements Config.Builder {
    private String clientID;
    private String clientSecret;
    private String tokenURL;
    private Transport transport;
    private Decoder decoder;
    private final Set<String> scopes = new HashSet<>();
    private final Map<String, Object> params = new HashMap<>();
    private AuthStyle authStyle;

    /**
     * Creates empty builder object.
     */
    ConfigBuilderImpl() {
        reset();
    }

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
    public Config.Builder withTokenURL(final String tokenURL) {
        this.tokenURL = tokenURL;
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
    public Config.Builder withAuthStyle(final AuthStyle authStyle) {
        this.authStyle = authStyle;
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
    public Config.Builder withEndpointParam(final String name, final Object value) {
        this.params.put(name, value);
        return this;
    }

    private void reset() {
        clientID = "";
        clientSecret = "";
        tokenURL = "";
        transport = null;
        decoder = null;
        authStyle = AuthStyle.AUTO;
        scopes.clear();
        params.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Config build() throws ConfigurationParameterMissingException {
        try {
            if (clientID == null || clientID.trim().length() == 0) {
                throw new ConfigurationParameterMissingException("clientID");
            }
            if (clientSecret == null || clientSecret.trim().length() == 0) {
                throw new ConfigurationParameterMissingException("clientSecret");
            }
            if (tokenURL == null || tokenURL.trim().length() == 0) {
                throw new ConfigurationParameterMissingException("tokenURL");
            }
            if (transport == null) {
                throw new ConfigurationParameterMissingException("transport");
            }
            if (decoder == null) {
                throw new ConfigurationParameterMissingException("decoder");
            }
            return new Config(clientID, clientSecret, tokenURL, authStyle, scopes, params, transport, decoder);
        } finally {
            reset();
        }
    }
}
