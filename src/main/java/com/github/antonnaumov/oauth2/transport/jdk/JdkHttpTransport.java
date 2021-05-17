package com.github.antonnaumov.oauth2.transport.jdk;

import com.github.antonnaumov.oauth2.AuthStyle;
import com.github.antonnaumov.oauth2.Request;
import com.github.antonnaumov.oauth2.Transport;
import com.github.antonnaumov.oauth2.IncorrectAuthenticationStyleException;
import com.github.antonnaumov.oauth2.Utils;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Base64;

public final class JdkHttpTransport implements Transport {
    @Override
    public Reader token(final String url, final Request request) throws Exception {
        final var client = java.net.http.HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build();
        final var req = HttpRequest.
                newBuilder(URI.create(url)).
                POST(HttpRequest.BodyPublishers.ofString(Utils.mapToHTTPParamsString(request.params))).
                header("Content-Type", "application/x-www-form-urlencoded");
        if (request.authStyle == AuthStyle.HEADER) {
            final var plainCred = String.join(
                    ":",
                    URLEncoder.encode(request.clientID, StandardCharsets.UTF_8),
                    URLEncoder.encode(request.clientSecret, StandardCharsets.UTF_8)
            );
            final var base64Cred = new String(Base64.getEncoder().encode(plainCred.getBytes(StandardCharsets.UTF_8)));
            req.header("Authorization", "Basic " + base64Cred);
        }

        final var response = client.send(req.build(), HttpResponse.BodyHandlers.ofInputStream());
        if (200 != response.statusCode()) {
            throw new IncorrectAuthenticationStyleException(request.authStyle);
        }
        return new InputStreamReader(response.body());
    }
}
