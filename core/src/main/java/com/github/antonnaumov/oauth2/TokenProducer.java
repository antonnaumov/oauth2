package com.github.antonnaumov.oauth2;

import java.util.HashMap;
import java.util.Map;

/**
 * An OAuth2 token producer.
 *
 * <p>Different OAuth2 providers support different authentication styles, so token production is more complex than requesting the provider and parse the provider response.</p>
 * <p>
 *     There are two different ways how token production happens.
 *     <ol>
 *         <li>If the preferred authentication style is specified, the token production is a single request to the provider and the provider response parsing.</li>
 *         <li>Otherwise, the {@link AuthStyle#HEADER} using for the first authentication attempt, and {@link AuthStyle#PARAMS}
 *         using as the backup method once the {@link IncorrectAuthenticationStyleException} catch.</li>
 *     </ol>
 * </p>
 */
public final class TokenProducer {
    private final Transport transport;
    private final Decoder decoder;
    private final Map<String, AuthStyle> authStyleCache = new HashMap<>();

    /**
     * Creates the class instance with given {@link Transport} and {@link Decoder} instances.
     * @param transport {@link Transport} instance responsible for pass a OAuth2 requests to the provider.
     * @param decoder {@link Decoder} instance responsible for parse the OAuth2 provider response.
     */
    public TokenProducer(final Transport transport, final Decoder decoder) {
        this.transport = transport;
        this.decoder = decoder;
    }

    /**
     * Produce OAuth2 token passing the given authentication secret and extra parameters if any to the given OAuth2 provider URL.
     *
     * @param url OAuth2 provider URL to request.
     * @param clientID OAuth2 provider client unique identifier.
     * @param clientSecret OAuth2 provider client secret.
     * @param authStyle {@link AuthStyle} value defined the OAuth2 provider preferred authentication style.
     * @param params OAuth2 provider extra parameters if any.
     * @return {@link Token} instance contains the OAuth2 token information.
     * @throws Exception if OAuth2 token cannot produce.
     */
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
