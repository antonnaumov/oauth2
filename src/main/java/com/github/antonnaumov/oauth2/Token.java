package com.github.antonnaumov.oauth2;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public final class Token {
    public static final long EXPIRE_DELTA = TimeUnit.SECONDS.toMillis(10);

    public final String accessToken;
    public final TokenType tokenType;
    public final String refreshToken;
    public final long expire;
    public final Map<String, Object> extra;

    public Token(final String accessToken, final TokenType tokenType, final String refreshToken, final long expire) {
        this(accessToken, tokenType, refreshToken, expire, Map.of());
    }

    Token(final String accessToken, final TokenType tokenType, final String refreshToken, final long expire, final Map<String, Object> extra) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.refreshToken = refreshToken;
        this.expire = expire;
        this.extra = extra;
    }

    public String authHeader() {
        return tokenType.asString() + " " + accessToken;
    }

    public Optional<Object> extra(final String key) {
        return Optional.ofNullable(extra.get(key));
    }

    public boolean valid() {
        return !accessToken.equals("") && !expired();
    }

    private boolean expired() {
        if (expire == 0) {
            return false;
        }

        return (expire - EXPIRE_DELTA) < System.currentTimeMillis();
    }
}
