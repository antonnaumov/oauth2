package com.github.antonnaumov.oauth2;

/**
 * Thrown when an OAuth2 provider does not support selected authentication style.
 *
 * <p>
 *     Different OAuth2 providers supports different authentication styles.
 *     Some providers supports Auth header secrets transfer only, some - form parameters only,
 *     some - both header and form parameters. In the case the particular OAuth2 provider dose not support selected
 *     authentication style the exception will thrown.
 * </p>
 */
public class IncorrectAuthenticationStyleException extends Exception {
    public IncorrectAuthenticationStyleException(final AuthStyle style) {
        super("OAuth2: incorrect authentication style: " + style.name());
    }
}
