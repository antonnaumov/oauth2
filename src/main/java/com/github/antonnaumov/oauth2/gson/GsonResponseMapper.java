package com.github.antonnaumov.oauth2.gson;

import com.github.antonnaumov.oauth2.ResponseMapper;
import com.github.antonnaumov.oauth2.Token;
import com.github.antonnaumov.oauth2.TokenType;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.InputStream;
import java.io.InputStreamReader;

public class GsonResponseMapper implements ResponseMapper  {
    private static final class Response {
        @SerializedName("access_token")
        String accessToken;
        @SerializedName("refresh_token")
        String refreshToken;
        @SerializedName("expired_in")
        long expiredIn;

        Response() {}
    }

    @Override
    public Token map(final InputStream stream) throws Exception {
        final var gson = new Gson();
        final var reader = gson.newJsonReader(new InputStreamReader(stream));
        final Response resp = gson.fromJson(reader, Response.class);
        return new Token(resp.accessToken, TokenType.BEARER, resp.refreshToken, System.currentTimeMillis() + resp.expiredIn);
    }
}
