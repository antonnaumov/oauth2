package com.github.antonnaumov.oauth2;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Class represent an OAuth2 token.
 */
public final class Token {
    /**
     * A delta between original token expiration timestamp and the time to issue new token.
     * The delta is important to keep transition between expired and refreshed token invisible for the token user.
     * The delta is 10 minutes in milliseconds.
     */
    public static final long EXPIRE_DELTA = TimeUnit.SECONDS.toMillis(10);

    /**
     * OAuth2 access token.
     */
    public final String accessToken;
    /**
     * {@link TokenType} value defines the OAuth2 token type.
     */
    public final TokenType tokenType;
    /**
     * OAuth2 refresh token.
     */
    public final String refreshToken;
    /**
     * OAuth2 access token expiration timestamp in milliseconds.
     */
    public final long expire;
    /**
     * OAuth2 token extra parameters if any (usually OAuth2 provider specific).
     */
    public final Map<String, Object> extra;

    /**
     * Creates the class instance contains pure OAuth2 token without extra parameters.
     *
     * @param accessToken OAuth2 access token.
     * @param tokenType {@link TokenType} value defines OAuth2 token type.
     * @param refreshToken OAuth2 access token.
     * @param expire OAuth2 access token expiration timestamp.
     */
    public Token(final String accessToken, final TokenType tokenType, final String refreshToken, final long expire) {
        this(accessToken, tokenType, refreshToken, expire, Map.of());
    }

    /**
     * Creates the class instance contains OAuth2 token with extra parameters.
     *
     * @param accessToken OAuth2 access token.
     * @param tokenType {@link TokenType} value defines OAuth2 token type.
     * @param refreshToken OAuth2 access token.
     * @param expire OAuth2 access token expiration timestamp.
     * @param extra OAuth2 token extra parameters.
     */
    Token(final String accessToken, final TokenType tokenType, final String refreshToken, final long expire, final Map<String, Object> extra) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.refreshToken = refreshToken;
        this.expire = expire;
        this.extra = Map.copyOf(extra);
    }

    /**
     * Returns access token together with token type as a string ready to use for Auth header.
     *
     * @return access token together with token type as a string.
     */
    public String authHeader() {
        return tokenType.asString() + " " + accessToken;
    }

    /**
     * The token is valid if the access token filed is initialized, not empty, and not expired.
     *
     * @return {@code true} if access token is not null, not empty, and not expired. Otherwise returns {@code false}.
     */
    public boolean valid() {
        return null != accessToken && !"".equals(accessToken) && !expired();
    }

    /**
     * The token is expired if expire timestamp is 0 or the access token expiration timestamp is later than current timestamp plus expiration delta.
     *
     * @return {@code true} if expire timestamp is 0 or the access token expiration timestamp is later than current timestamp plus expiration delta. Otherwise returns {@code false}.
     */
    boolean expired() {
        if (expire == 0) {
            return false;
        }

        return (expire - EXPIRE_DELTA) < System.currentTimeMillis();
    }
}
