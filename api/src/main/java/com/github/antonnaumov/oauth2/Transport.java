package com.github.antonnaumov.oauth2;

import java.io.Reader;

/**
 * An interface implementation defines the way to communication with OAuth2 provider.
 */
@FunctionalInterface
public interface Transport {
    /**
     * Sends the given request to the given URL to retrieve OAuth2 token from the provider.
     *
     * @param url OAuth2 provider URL.
     * @param request {@link Request} instance holding request information.
     * @return {@code java.io.Reader} implementation ready to read response body.
     * @throws Exception if OAuth2 token request failed.
     */
    Reader token(String url, Request request) throws Exception;
}
