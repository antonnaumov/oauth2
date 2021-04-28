package com.github.antonnaumov.oauth2;

public final class StaticTokeSource implements TokenSource {
    private final Token token;

    StaticTokeSource(final Token token) {
        this.token = token;
    }

    @Override
    public Token token() throws Exception {
        return token;
    }
}
