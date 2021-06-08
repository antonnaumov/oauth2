package com.github.antonnaumov.oauth2;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * OAuth2 configuration.
 *
 * The configuration defines the strategy to obtain all possible OAuth2 tokens kind.
 */
public final class Config {
    /**
     * OAuth2 configuration builder.
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
         * Specify OAuth2 provider endpoints.
         *
         * @param endpoint the {@link Endpoint} interface implementation defines OAuth2 provider endpoints.
         * @return the builder instance with endpoints setup.
         */
        Builder withEndpoint(Endpoint endpoint);

        /**
         * Specify OAuth2 provider redirect URI.
         *
         * @param redirectURL the OAuth2 provider redirect URL.
         * @return the builder instance with redirect URI setup.
         */
        Builder withRedirectURL(String redirectURL);

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

    /**
     * OAuth2 provider unique client identifier.
     */
    public final String clientID;
    /**
     * OAuth2 provider client secret.
     */
    public final String clientSecret;
    /**
     * {@link Endpoint} interface instance contains OAuth2 provider endpoints.
     */
    public final Endpoint endpoint;
    /**
     * {@link TokenProducer} class instance retrieves various tokens kind.
     */
    public final TokenProducer producer;
    /**
     * OAuth2 provider redirect URL.
     */
    public final String redirectURL;
    /**
     * OAuth2 provider scopes set.
     */
    public final Set<String> scopes;

    /**
     * Creates new Builder interface instance.
     *
     * @return the Builder interface instance.
     */
    public static Builder newBuilder() {
        return new ConfigBuilderImpl();
    }

    /**
     * Creates OAuth2 configuration.
     *
     * @param clientID OAuth2 provider unique client identifier.
     * @param clientSecret OAuth2 provider client secret.
     * @param endpoint OAuth2 provider endpoints specification.
     * @param redirectURL OAuth2 redirect URL.
     * @param scopes OAuth2 provider scopes set.
     * @param transport OAuth2 provider request transport.
     * @param decoder OAuth2 provider response decoder.
     */
    Config(final String clientID,
           final String clientSecret,
           final Endpoint endpoint,
           final String redirectURL,
           final Set<String> scopes,
           final Transport transport,
           final Decoder decoder) {
        this.clientID = clientID;
        this.clientSecret = clientSecret;
        this.endpoint = endpoint;
        this.producer = new TokenProducer(transport, decoder);
        this.redirectURL = redirectURL;
        this.scopes = Set.copyOf(scopes);
    }

    /**
     * Compose the URL to retrieve OAuth2 authentication code.
     *
     * @param state OAuth2 provider state.
     * @param extras OAuth2 request extra parameters.
     * @return the URL to retrieve authentication code from OAuth2 provider.
     */
    public String authCodeURL(final String state, final Map<String, Object> extras) {
        final var builder = new StringBuilder().append(endpoint.authURL());
        if (endpoint.authURL().endsWith("?")) {
            builder.append("&");
        } else {
            builder.append("?");
        }
        final var params = new HashMap<String, Object>();
        params.put("response_type", "code");
        params.put("client_id", clientID);
        if (null != redirectURL && !"".equals(redirectURL)) {
            params.put("redirect_uri", redirectURL);
        }
        if (scopes.size() > 0) {
            params.put("scope", String.join(" ", scopes));
        }
        if (!"".equals(state)) {
            params.put("state", state);
        }
        if (null != extras) {
            params.putAll(extras);
        }
        builder.append(Utils.mapToHTTPParamsString(params));
        return builder.toString();
    }

    /**
     * Retrieve OAuth2 token using the given username and password credentials.
     *
     * @param username OAuth2 provider username.
     * @param password OAuth2 provider password.
     * @return the {@link Token} instance contains OAuth2 tokens information.
     * @throws Exception if OAuth2 token cannot be retrieve.
     */
    public Token passwordCredentialsToken(final String username, final String password) throws Exception {
        final var formData = new HashMap<String, Object>();
        formData.put("grant_type", "password");
        formData.put("username", username);
        formData.put("password", password);
        if (scopes.size() > 0) {
            formData.put("scope", String.join(" ", scopes));
        }
        return producer.produce(endpoint.tokenURL(), clientID, clientSecret, endpoint.authStyle(), formData);
    }

    /**
     * Exchange OAuth2 token using the given code and extra parameters.
     *
     * @param code OAuth2 code to exchange the token.
     * @param extras OAuth2 provider request extra parameters if any.
     * @return the {@link Token} instance contains OAuth2 tokens information.
     * @throws Exception if OAuth2 token cannot be retrieve.
     */
    public Token exchange(final String code, final Map<String, Object> extras) throws Exception {
        final var formData = new HashMap<String, Object>();
        formData.put("grant_type", "authorisation_code");
        formData.put("code", code);
        if (null != redirectURL && !"".equals(redirectURL)) {
            formData.put("redirect_uri", redirectURL);
        }
        if (null != extras) {
            formData.putAll(extras);
        }
        return producer.produce(endpoint.tokenURL(), clientID, clientSecret, endpoint.authStyle(), formData);
    }

    /**
     * The {@link TokenSource} instance creates from give {@link Token}.
     *
     * @param token the {@link Token} instance.
     * @return the {@link TokenSource} instance.
     */
    public TokenSource tokenSource(final Token token) {
        return new ReuseTokenSource(() -> {
            if (token == null || "".equals(token.refreshToken)) {
                throw new Exception("OAuth2: both access token and refresh token are missing");
            }

            return producer.produce(
                    endpoint.tokenURL(),
                    clientID,
                    clientSecret,
                    endpoint.authStyle(),
                    Map.of(
                            "grant_type", "refresh_token",
                            "refresh_token", token.refreshToken
                    ));
        }, token);
    }
}
