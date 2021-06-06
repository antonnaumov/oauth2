package com.github.antonnaumov.oauth2;

import java.io.Reader;

/**
 * A Decoder object is parsing OAuth2 provider authentication response body.
 */
@FunctionalInterface
public interface Decoder {
    /**
     * Creates OAuth2 token from OAuth2 provider response body reader.
     *
     * @param responseReader OAuth2 provider response body reader.
     * @return the OAuth2 token.
     * @throws Exception if it is impossible to create OAuth2 token from response body information.
     */
    Token decode(final Reader responseReader) throws Exception;
}
