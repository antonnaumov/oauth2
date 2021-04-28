package com.github.antonnaumov.oauth2.clientcredentials;

import com.github.antonnaumov.oauth2.AuthStyle;
import com.github.antonnaumov.oauth2.Transport;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

final class ConfigBuilderImpl implements Config.Builder {
    private String clientID = "";
    private String clientSecret = "";
    private String tokenURL = "";
    private Transport transport;
    private final Set<String> scopes = new HashSet<>();
    private final Map<String, Object> params = new HashMap<>();
    private AuthStyle authStyle = AuthStyle.AUTO;

    ConfigBuilderImpl() {}

    @Override
    public Config.Builder withClientCredentials(final String clientID, final String clientSecret) {
        this.clientID = clientID;
        this.clientSecret = clientSecret;
        return this;
    }

    @Override
    public Config.Builder withTokenURL(final String tokenURL) {
        this.tokenURL = tokenURL;
        return this;
    }

    @Override
    public Config.Builder withTransport(final Transport transport) {
        this.transport = transport;
        return this;
    }

    @Override
    public Config.Builder withAuthStyle(final AuthStyle authStyle) {
        this.authStyle = authStyle;
        return this;
    }

    @Override
    public Config.Builder withScope(final String scope) {
        this.scopes.add(scope);
        return this;
    }

    @Override
    public Config.Builder withEndpointParam(final String name, final Object value) {
        this.params.put(name, value);
        return this;
    }

    @Override
    public Config build() {
        final var cfg = new Config(clientID, clientSecret, tokenURL, authStyle, transport, scopes, params);

        clientID = "";
        clientSecret = "";
        tokenURL = "";
        transport = null;
        authStyle = AuthStyle.AUTO;
        scopes.clear();
        params.clear();

        return cfg;
    }
}
