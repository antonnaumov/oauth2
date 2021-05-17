package com.github.antonnaumov.oauth2.clientcredentials;

import com.github.antonnaumov.oauth2.AuthStyle;
import com.github.antonnaumov.oauth2.Decoder;
import com.github.antonnaumov.oauth2.ReuseTokenSource;
import com.github.antonnaumov.oauth2.Token;
import com.github.antonnaumov.oauth2.TokenProducer;
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

        Builder withDecoder(Decoder decoder);

        Builder withAuthStyle(AuthStyle authStyle);

        Builder withScope(String scope);

        Builder withEndpointParam(String name, Object value);

        Config build();
    }

    public final String clientID;
    public final String clientSecret;
    public final String tokenURL;
    public final AuthStyle authStyle;
    public final TokenProducer producer;
    public final Set<String> scopes;
    public final Map<String, Object> endpointParams;

    public static Builder newBuilder() {
        return new ConfigBuilderImpl();
    }

    Config(final String clientID,
           final String clientSecret,
           final String tokenURL,
           final AuthStyle authStyle,
           final Set<String> scopes,
           final Map<String, Object> endpointParams,
           final Transport transport,
           final Decoder decoder) {
        this.clientID = clientID;
        this.clientSecret = clientSecret;
        this.tokenURL = tokenURL;
        this.producer = new TokenProducer(transport, decoder);
        this.scopes = Set.copyOf(scopes);
        this.endpointParams = Map.copyOf(endpointParams);
        this.authStyle = authStyle;
    }

    public Token token() throws Exception {
        return tokenSource().token();
    }

    public TokenSource tokenSource() {
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
            return producer.produce(tokenURL, clientID, clientSecret, authStyle, params);
        }, null);
    }
}
