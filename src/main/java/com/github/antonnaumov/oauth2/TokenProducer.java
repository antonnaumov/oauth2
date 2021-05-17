package com.github.antonnaumov.oauth2;

import java.util.HashMap;
import java.util.Map;

public final class TokenProducer {
    private final Transport transport;
    private final Decoder decoder;
    private final Map<String, AuthStyle> authStyleCache = new HashMap<>();

    public TokenProducer(final Transport transport, final Decoder decoder) {
        this.transport = transport;
        this.decoder = decoder;
    }

    public Token produce(final String url,
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
            final var response = transport.token(url, new Request(clientID, clientSecret, probeAuthStyle, params));
            if (authStyleProbeRequired) {
                authStyleCache.put(url, authStyle);
            }
            return decoder.decode(response);
        } catch (IncorrectAuthenticationStyleException e) {
            if (authStyleProbeRequired) {
                return produce(url, clientID, clientSecret, AuthStyle.PARAMS, params);
            }
            throw new Exception("OAuth2: authentication failed", e);
        }
    }
}
