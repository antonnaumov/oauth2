package com.github.antonnaumov.oauth2.clientcredentials;

import com.github.antonnaumov.oauth2.AuthStyle;
import com.github.antonnaumov.oauth2.ReuseTokenSource;
import com.github.antonnaumov.oauth2.Token;
import com.github.antonnaumov.oauth2.TokenSource;
import com.github.antonnaumov.oauth2.Transport;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class Config {
    public interface Builder {
        Builder withClientCredentials(String clientID, String clientSecret);

        Builder withTokenURL(String tokenURL);

        Builder withTransport(Transport transport);

        Builder withAuthStyle(AuthStyle authStyle);

        Builder withScope(String scope);

        Builder withEndpointParam(String name, Object value);

        Config build();
    }

    public final String clientID;
    public final String clientSecret;
    public final String tokenURL;
    public final AuthStyle authStyle;
    public final Transport transport;
    public final Set<String> scopes;
    public final Map<String, Object> endpointParams;

    public static Builder newBuilder() {
        return new ConfigBuilderImpl();
    }

    Config(final String clientID,
           final String clientSecret,
           final String tokenURL,
           final AuthStyle authStyle,
           final Transport transport,
           final Set<String> scopes,
           final Map<String, Object> endpointParams) {
        this.clientID = clientID;
        this.clientSecret = clientSecret;
        this.tokenURL = tokenURL;
        this.transport = transport;
        this.scopes = Set.copyOf(scopes);
        this.endpointParams = Map.copyOf(endpointParams);
        this.authStyle = authStyle;
    }

    public Token token() throws Exception {
        return tokenSource().token();
    }

    public TokenSource tokenSource() throws Exception {
        return new ReuseTokenSource(() -> {
            final var params = new HashMap<String, Object>();
            params.put("grant_type", "client_credentials");
            if (scopes.size() > 0) {
                params.put("scopes", String.join(" ", scopes));
            }
            for (var entry : params.entrySet()) {
                if (!"grant_type".equals(entry.getKey()) && params.containsKey(entry.getKey())) {
                    throw new Exception("OAuth2: cannot overwrite parameter: " + entry.getKey());
                }
                params.put(entry.getKey(), entry.getValue());
            }
            return transport.token(tokenURL, clientID, clientSecret, authStyle, params);
        }, null);
    }
}
