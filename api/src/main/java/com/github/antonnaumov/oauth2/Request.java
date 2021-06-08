package com.github.antonnaumov.oauth2;

import java.util.HashMap;
import java.util.Map;

/**
 * OAuth2 provider request.
 */
public final class Request {
    /**
     * OAuth2 provider unique client identifier.
     */
    public final String clientID;
    /**
     * OAuth2 provider client secret.
     */
    public final String clientSecret;
    /**
     * OAuth2 provider preferred authentication style.
     */
    public final AuthStyle authStyle;
    /**
     * OAuth2 provider extra request parameters.
     */
    public final Map<String, Object> params;

    /**
     * Creates OAuth2 provider request instance.
     *
     * @param clientID OAuth2 provider unique client identifier.
     * @param clientSecret OAuth2 provider client secret.
     * @param authStyle OAuth2 provider preferred authentication style.
     * @param params OAuth2 provider extra request parameters.
     */
    Request(final String clientID,
            final String clientSecret,
            final AuthStyle authStyle,
            final Map<String, Object> params) {
        this.clientID = clientID;
        this.clientSecret = clientSecret;
        this.authStyle = authStyle;
        final var bodyParams = new HashMap<String, Object>();
        if (authStyle != AuthStyle.HEADER) {
            bodyParams.put("client_id", clientID);
            bodyParams.put("client_secret", clientSecret);
        }
        bodyParams.putAll(params);
        this.params = Map.copyOf(bodyParams);
    }
}
