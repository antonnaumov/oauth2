package com.github.antonnaumov.oauth2;

public enum TokenType {
    BEARER("Bearer"), MAC("MAC"), BASIC("Basic");

    private final String type;

    TokenType(final String type) {
        this.type = type;
    }

    public String asString() {
        return type;
    }
}
