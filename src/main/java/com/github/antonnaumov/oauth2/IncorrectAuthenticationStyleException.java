package com.github.antonnaumov.oauth2;

public class IncorrectAuthenticationStyleException extends Exception {
    public IncorrectAuthenticationStyleException(final AuthStyle style) {
        super("OAuth2: incorrect authentication style: " + style.name());
    }
}
