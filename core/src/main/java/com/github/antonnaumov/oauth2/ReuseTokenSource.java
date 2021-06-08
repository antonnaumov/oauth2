package com.github.antonnaumov.oauth2;

import java.util.concurrent.locks.ReentrantLock;

/**
 * The {@link TokenSource} implementation provides an ability to reuse OAuth2 token.
 *
 * <p>
 *     The source using an original token until the token is valid.
 *     Once token is not valid anymore, the fallback token source will use to produce a new token.
 * </p>
 */
public final class ReuseTokenSource implements TokenSource {
    private final TokenSource next;
    private final ReentrantLock lock;
    private Token token;

    /**
     * Creates the class instance.
     *
     * @param next the {@link TokenSource} instance to use once the token is invalid.
     * @param token the {@link Token} instance to use until it's valid.
     */
    public ReuseTokenSource(final TokenSource next, final Token token) {
        lock = new ReentrantLock();
        this.next = next;
        this.token = token;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Token token() throws Exception {
        lock.lock();
        try {
            if (token == null || !token.valid()) {
                token = next.token();
            }

            return token;
        } finally {
            lock.unlock();
        }
    }
}
