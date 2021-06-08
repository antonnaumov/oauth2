package com.github.antonnaumov.oauth2;

/**
 * An token factory.
 * <p>The {@code TokenSource} interface implementation is cashing, check, and renew if possible OAuth2 token information.</p>
 * <p>The implementation purpose is making token check and renew procedure invisible for the OAuth2 token user.</p>
 */
@FunctionalInterface
public interface TokenSource {
    /**
     * Returns an OAuth2 token as {@link Token} instance ready to use.
     *
     * @return an OAuth2 token as {@link Token} instance ready to use.
     * @throws Exception if it's impossible to create the useful OAuth2 token.
     */
    Token token() throws Exception;
}
