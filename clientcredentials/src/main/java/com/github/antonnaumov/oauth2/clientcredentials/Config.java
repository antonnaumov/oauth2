package com.github.antonnaumov.oauth2.clientcredentials;

import com.github.antonnaumov.oauth2.AuthStyle;
import com.github.antonnaumov.oauth2.ConfigurationParameterMissingException;
import com.github.antonnaumov.oauth2.Decoder;
import com.github.antonnaumov.oauth2.ReuseTokenSource;
import com.github.antonnaumov.oauth2.Token;
import com.github.antonnaumov.oauth2.TokenProducer;
import com.github.antonnaumov.oauth2.TokenSource;
import com.github.antonnaumov.oauth2.Transport;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * OAuth2 configuration for the provider client credentials.
 *
 * The configuration defines the strategy to obtain all possible OAuth2 tokens kind with the provider client credentials.
 */
public final class Config {
    /**
     * OAuth2 configuration for the provider client credentials builder.
     *
     * The builder provides the most comfortable and straight way to setup all configuration parameters.
     */
    public interface Builder {
        /**
         * Specify OAuth2 clientID and clientSecret authentication secrets.
         *
         * The secrets are core part of the OAuth2 authentication.
         *
         * @param clientID client unique identifier.
         * @param clientSecret client password.
         * @return the builder instance with clientID and clientSecret setup.
         */
        Builder withClientCredentials(String clientID, String clientSecret);

        /**
         * Specify OAuth2 provider token URL.
         * @param tokenURL OAuth2 provider token URL.
         * @return the builder instance with clientID and clientSecret setup.
         */
        Builder withTokenURL(String tokenURL);

        Builder withEndpointParam(String name, Object value);

        Builder withAuthStyle(AuthStyle authStyle);

        /**
         * Specify OAuth2 transport.
         *
         * @param transport the {@link Transport} interface implementation using to interact with OAuth2 provider.
         * @return the builder instance with redirect URI setup.
         */
        Builder withTransport(Transport transport);

        /**
         * Specify OAuth2 response decoder.
         *
         * @param decoder the {@link Decoder} interface implementation using to decode OAuth2 provider response.
         * @return the builder instance with decoder setup.
         */
        Builder withDecoder(Decoder decoder);

        /**
         * Specify OAuth2 request scope.
         *
         * @param scope the OAuth2 provider scope.
         * @return the builder instance with new scope.
         */
        Builder withScope(String scope);

        /**
         * Build OAuth2 configuration instance.
         *
         * @return the Config instance.
         */
        Config build() throws ConfigurationParameterMissingException;
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
