package com.github.antonnaumov.oauth2;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public final class HttpTransport implements Transport {
    private final Map<String, AuthStyle> authStyleCache = new HashMap<>();
    private final HttpClient client;
    private final ResponseMapper mapper;

    HttpTransport(final HttpClient client, final ResponseMapper mapper) {
        this.client = client;
        this.mapper = mapper;
    }

    public Token token(final String url,
                       final String clientID,
                       final String clientSecret,
                       final AuthStyle authStyle,
                       final Map<String, Object> params) throws Exception {
        var authStyleProbeRequired = authStyle == AuthStyle.AUTO;
        var probeAuthStyle = authStyle;
        if (authStyleProbeRequired) {
            if (authStyleCache.containsKey(url)) {
                probeAuthStyle = authStyleCache.get(url);
                authStyleProbeRequired = false;
            } else {
                probeAuthStyle = AuthStyle.HEADER;
            }
        }

        try {
            final var body = composeRequestBody(authStyle, clientID, clientSecret, params);
            final var response = client.call(url, clientID, clientSecret, probeAuthStyle, body);
            if (authStyleProbeRequired) {
                authStyleCache.put(url, authStyle);
            }
            return mapper.map(response);
        } catch (IncorrectAuthenticationStyleException e) {
            if (authStyleProbeRequired) {
                return token(url, clientID, clientSecret, AuthStyle.PARAMS, params);
            }
            throw new Exception("OAuth2: authentication failed", e);
        }
    }

    private Map<String, Object> composeRequestBody(final AuthStyle authStyle,
                                                   final String clientID,
                                                   final String clientSecret,
                                                   final Map<String, Object> params) {
        if (authStyle == AuthStyle.HEADER) {
            return params;
        }

        final var body = new HashMap<String, Object>();
        body.put("client_id", URLEncoder.encode(clientID, StandardCharsets.UTF_8));
        body.put("client_secret", URLEncoder.encode(clientSecret, StandardCharsets.UTF_8));
        body.putAll(params);
        return body;
    }
}
