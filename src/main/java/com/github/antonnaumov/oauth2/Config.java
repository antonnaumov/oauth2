package com.github.antonnaumov.oauth2;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class Config {
    public interface Builder {
        Builder withClientCredentials(String clientID, String clientSecret);

        Builder withEndpoint(Endpoint endpoint);

        Builder withRedirectURL(String redirectURL);

        Builder withTransport(Transport transport);

        Builder withDecoder(Decoder encoder);

        Builder withScope(String scope);

        Config build();
    }

    public final String clientID;
    public final String clientSecret;
    public final Endpoint endpoint;
    public final TokenProducer producer;
    public final String redirectURL;
    public final Set<String> scopes;

    public static Builder newBuilder() {
        return new ConfigBuilderImpl();
    }

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
        if (!"".equals(redirectURL)) {
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

    public Token exchange(final String code, final Map<String, Object> extras) throws Exception {
        final var formData = new HashMap<String, Object>();
        formData.put("grant_type", "authorisation_code");
        formData.put("code", code);
        if (!"".equals(redirectURL)) {
            formData.put("redirect_uri", redirectURL);
        }
        if (null != extras) {
            formData.putAll(extras);
        }
        return producer.produce(endpoint.tokenURL(), clientID, clientSecret, endpoint.authStyle(), formData);
    }

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
