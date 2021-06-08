package com.github.antonnaumov.oauth2;

/**
 * OAuth2 token usage type.
 * <p>The OAuth2 token is possible to use as Bearer, MAC or Basic Auth header.</p>
 */
public enum TokenType {
    BEARER("Bearer"), MAC("MAC"), BASIC("Basic");

    private final String type;

    TokenType(final String type) {
        this.type = type;
    }

    public String asString() {
        return type;
    }

    public static TokenType fromString(final String type) {
        switch (type.toLowerCase()) {
            case "bearer":
                return BEARER;
            case "mac":
                return MAC;
            case "basic":
                return BASIC;
            default:
                throw new IllegalArgumentException("Unsupported token type: " + type);
        }
    }
}
