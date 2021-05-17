package com.github.antonnaumov.oauth2;

import java.util.HashMap;
import java.util.Map;

public final class Request {
    public final String clientID;
    public final String clientSecret;
    public final AuthStyle authStyle;
    public final Map<String, Object> params;

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
