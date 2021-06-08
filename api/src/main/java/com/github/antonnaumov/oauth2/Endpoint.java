package com.github.antonnaumov.oauth2;

/**
 * An Endpoint provides OAuth2 provider authentication, token endpoint URLs, and preferred authentication style.
 */
public interface Endpoint {
    /**
     * OAuth2 provider authentication endpoint.
     *
     * @return OAuth2 provider authentication endpoint URL.
     */
    String authURL();

    /**
     * OAuth2 provider token endpoint.
     *
     * @return OAuth2 provider token endpoint URL.
     */
    String tokenURL();

    /**
     * OAuth2 provider preferred authentication style.
     *
     * @return {@link AuthStyle} enumeration value defines OAuth2 provider preferred authentication style. By default {@link AuthStyle#AUTO}.
     */
    default AuthStyle authStyle() {
        return AuthStyle.AUTO;
    }
}
