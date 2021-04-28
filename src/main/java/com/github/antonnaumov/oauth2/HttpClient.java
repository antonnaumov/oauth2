package com.github.antonnaumov.oauth2;

import java.io.InputStream;
import java.util.Map;

@FunctionalInterface
public interface HttpClient {
    InputStream call(final String url,
                     final String clientID,
                     final String clientSecret,
                     final AuthStyle authStyle,
                     final Map<String, Object> body) throws Exception;
}
