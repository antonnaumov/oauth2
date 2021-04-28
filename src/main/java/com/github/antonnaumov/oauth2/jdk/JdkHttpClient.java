package com.github.antonnaumov.oauth2.jdk;

import com.github.antonnaumov.oauth2.AuthStyle;
import com.github.antonnaumov.oauth2.HttpClient;
import com.github.antonnaumov.oauth2.IncorrectAuthenticationStyleException;
import com.github.antonnaumov.oauth2.Utils;

import java.io.InputStream;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Base64;
import java.util.Map;

public class JdkHttpClient implements HttpClient {
    @Override
    public InputStream call(final String url, final String clientID, final String clientSecret, final AuthStyle authStyle, final Map<String, Object> body) throws Exception {
        final var client = java.net.http.HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build();
        final var req = HttpRequest.
                newBuilder(URI.create(url)).
                POST(HttpRequest.BodyPublishers.ofString(Utils.mapToHTTPParamsString(body))).
                header("Content-Type", "application/x-www-form-urlencoded");
        if (authStyle == AuthStyle.HEADER) {
            final var plainCred = URLEncoder.encode(clientID, StandardCharsets.UTF_8) + ":" + URLEncoder.encode(clientSecret, StandardCharsets.UTF_8);
            final var base64Cred = new String(Base64.getEncoder().encode(plainCred.getBytes(StandardCharsets.UTF_8)));
            req.header("Authorization", "Basic " + base64Cred);
        }
        final var response = client.send(req.build(), HttpResponse.BodyHandlers.ofInputStream());
        if (200 != response.statusCode()) {
            throw new IncorrectAuthenticationStyleException(authStyle);
        }
        return response.body();
    }
}
