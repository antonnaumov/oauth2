package com.github.antonnaumov.oauth2;

import java.util.concurrent.locks.ReentrantLock;

public final class ReuseTokenSource implements TokenSource {
    private final TokenSource next;
    private final ReentrantLock lock;
    private Token token;

    public ReuseTokenSource(final TokenSource next, final Token token) {
        lock = new ReentrantLock();
        this.next = next;
        this.token = token;
    }

    @Override
    public Token token() throws Exception {
        lock.lock();
        try {
            if (!token.valid()) {
                token = next.token();
            }

            return token;
        } finally {
            lock.unlock();
        }
    }
}
