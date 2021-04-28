package com.github.antonnaumov.oauth2;

public interface Endpoint {
    String authURL();

    String tokenURL();

    default AuthStyle authStyle() {
        return AuthStyle.AUTO;
    }
}
