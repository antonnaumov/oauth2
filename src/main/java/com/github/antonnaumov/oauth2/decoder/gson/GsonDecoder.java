package com.github.antonnaumov.oauth2.decoder.gson;

import com.github.antonnaumov.oauth2.Decoder;
import com.github.antonnaumov.oauth2.Token;
import com.github.antonnaumov.oauth2.TokenType;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.Reader;
import java.util.concurrent.TimeUnit;

public final class GsonDecoder implements Decoder {
    private static final class Response {
        @SerializedName("access_token")
        String accessToken;
        @SerializedName("refresh_token")
        String refreshToken;
        @SerializedName("expires_in")
        long expiredIn;

        Response() {}
    }

    @Override
    public Token decode(final Reader responseReader) {
        final var gson = new Gson();
        final var reader = gson.newJsonReader(responseReader);
        final Response resp = gson.fromJson(reader, Response.class);
        return new Token(resp.accessToken, TokenType.BEARER, resp.refreshToken, System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(resp.expiredIn));
    }
}
