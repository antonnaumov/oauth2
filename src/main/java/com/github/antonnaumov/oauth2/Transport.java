package com.github.antonnaumov.oauth2;

import java.util.Map;

@FunctionalInterface
public interface Transport {
    Token token(String url, String clientID, String clientSecret, AuthStyle authStyle, Map<String, Object> params) throws Exception;
}
