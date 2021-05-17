package com.github.antonnaumov.oauth2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TestConfig {
    @Test
    public void authCodeURL() {
        final var cfg = Config.newBuilder()
                .withClientCredentials("CLIENT_ID", "CLIENT_SECRET")
                .withRedirectURL("REDIRECT_URL")
                .withScope("scope1")
                .withScope("scope2")
                .withEndpoint(new Endpoint() {
                    @Override
                    public String authURL() {
                        return "server/auth";
                    }

                    @Override
                    public String tokenURL() {
                        return "server/token";
                    }
                })
                .build();
        final var url = cfg.authCodeURL("foo", Map.of("access_type", "offline", "prompt", "consent"));
        Assertions.assertTrue(url.startsWith("server/auth?access_type=offline"));
        Assertions.assertTrue(url.contains("&scope=scope1+scope2") || url.contains("&scope=scope2+scope1"));
        Assertions.assertTrue(url.contains("&response_type=code"));
        Assertions.assertTrue(url.contains("&redirect_uri=REDIRECT_URL"));
        Assertions.assertTrue(url.contains("&state=foo"));
        Assertions.assertTrue(url.contains("&prompt=consent"));
        Assertions.assertTrue(url.contains("&client_id=CLIENT_ID"));
    }

    @Test
    public void authCodeURLCustomParam() {
        final var cfg = Config.newBuilder()
                .withClientCredentials("CLIENT_ID", "CLIENT_SECRET")
                .withRedirectURL("REDIRECT_URL")
                .withScope("scope1")
                .withScope("scope2")
                .withEndpoint(new Endpoint() {
                    @Override
                    public String authURL() {
                        return "server/auth";
                    }

                    @Override
                    public String tokenURL() {
                        return "server/token";
                    }
                })
                .build();
        final var url = cfg.authCodeURL("bazz", Map.of("foo", "bar"));
        Assertions.assertTrue(url.startsWith("server/auth"));
        Assertions.assertTrue(url.contains("?scope=scope1+scope2") || url.contains("?scope=scope2+scope1"));
        Assertions.assertTrue(url.contains("&response_type=code"));
        Assertions.assertTrue(url.contains("&redirect_uri=REDIRECT_URL"));
        Assertions.assertTrue(url.contains("&foo=bar"));
        Assertions.assertTrue(url.contains("&state=baz"));
        Assertions.assertTrue(url.contains("&client_id=CLIENT_ID"));
    }

    @Test
    public void authCodeURLBlank() {
        final var cfg = Config.newBuilder()
                .withClientCredentials("CLIENT_ID", "CLIENT_SECRET")
                .withEndpoint(new Endpoint() {
                    @Override
                    public String authURL() {
                        return "/auth-url";
                    }

                    @Override
                    public String tokenURL() {
                        return "/token-url";
                    }
                })
                .build();
        final var url = cfg.authCodeURL("", Map.of());
        Assertions.assertTrue(url.startsWith("auth-url"));
        Assertions.assertTrue(url.contains("&response_type=code"));
        Assertions.assertTrue(url.contains("&client_id=CLIENT_ID"));
    }

    @Test
    public void exchange() throws Exception {
        final var cfg = Config.newBuilder()
                .withClientCredentials("CLIENT_ID", "CLIENT_SECRET")
                .withEndpoint(new Endpoint() {
                    @Override
                    public String authURL() {
                        return "/auth-url";
                    }

                    @Override
                    public String tokenURL() {
                        return "/token-url";
                    }
                })
                .withRedirectURL("REDIRECT_URL")
                .withTransport((url, req) -> {
                    if (Optional.ofNullable(req.params.get("code")).filter(c -> c.equals("exchange-code")).isEmpty()) {
                        throw new IOException("Exchange code missing or incorrect");
                    }
                    if (Optional.ofNullable(req.params.get("grant_type")).filter(c -> c.equals("authorization-code")).isEmpty()) {
                        throw new IOException("Grant type missing or incorrect");
                    }
                    if (Optional.ofNullable(req.params.get("redirect_uri")).filter(c -> c.equals("REDIRECT_URL")).isEmpty()) {
                        throw new IOException("Redirect URL missing or incorrect");
                    }
                    return new StringReader("access_token=90d64460d14870c08c81352a05dedd3465940a7c&scope=user&token_type=bearer");
                })
                .withDecoder(reader -> {
                    final var buf = new StringBuilder();
                    try (var bufr = new BufferedReader(reader)) {
                        bufr.lines().forEach(buf::append);
                    }
                    final var map = Utils.HTTPParamsStringToMap(buf.toString());
                    final var extra = new HashMap<String, Object>();
                    map.forEach((key, value) -> {
                        if(!List.of("access_token", "refresh_token", "token_type", "expire_in").contains(key)) {
                            extra.put(key, value);
                        }
                    });
                    return new Token(map.get("access_token"), TokenType.fromString(map.get("token_type")), "", 0, extra);
                })
                .build();
        final var token = cfg.exchange("exchange-code", Map.of());
        Assertions.assertEquals("90d64460d14870c08c81352a05dedd3465940a7", token.accessToken);
        Assertions.assertSame(TokenType.BEARER, token.tokenType);
        Assertions.assertEquals("user", Optional.ofNullable(token.extra.get("scope")).map(Object::toString).orElse(""));
    }

    @Test
    public void exchangeCustomParams() throws Exception {
        final var cfg = Config.newBuilder()
                .withClientCredentials("CLIENT_ID", "CLIENT_SECRET")
                .withEndpoint(new Endpoint() {
                    @Override
                    public String authURL() {
                        return "/auth-url";
                    }

                    @Override
                    public String tokenURL() {
                        return "/token-url";
                    }
                })
                .withRedirectURL("REDIRECT_URL")
                .withTransport((url, req) -> {
                    if (Optional.ofNullable(req.params.get("code")).filter(c -> c.equals("exchange-code")).isEmpty()) {
                        throw new IOException("Exchange code missing or incorrect");
                    }
                    if (Optional.ofNullable(req.params.get("grant_type")).filter(c -> c.equals("authorization-code")).isEmpty()) {
                        throw new IOException("Grant type missing or incorrect");
                    }
                    if (Optional.ofNullable(req.params.get("redirect_uri")).filter(c -> c.equals("REDIRECT_URL")).isEmpty()) {
                        throw new IOException("Redirect URL missing or incorrect");
                    }
                    return new StringReader("access_token=90d64460d14870c08c81352a05dedd3465940a7c&scope=user&token_type=bearer");
                })
                .withDecoder(reader -> {
                    final var buf = new StringBuilder();
                    try (var bufr = new BufferedReader(reader)) {
                        bufr.lines().forEach(buf::append);
                    }
                    final var map = Utils.HTTPParamsStringToMap(buf.toString());
                    final var extra = new HashMap<String, Object>();
                    map.forEach((key, value) -> {
                        if(!List.of("access_token", "refresh_token", "token_type", "expire_in").contains(key)) {
                            extra.put(key, value);
                        }
                    });
                    return new Token(map.get("access_token"), TokenType.fromString(map.get("token_type")), "", 0, extra);
                })
                .build();
        final var token = cfg.exchange("exchange-code", Map.of("foo", "bar"));
        Assertions.assertEquals("90d64460d14870c08c81352a05dedd3465940a7", token.accessToken);
        Assertions.assertSame(TokenType.BEARER, token.tokenType);
        Assertions.assertEquals("user", Optional.ofNullable(token.extra.get("scope")).map(Object::toString).orElse(""));
        Assertions.assertEquals("bar", Optional.ofNullable(token.extra.get("foo")).map(Object::toString).orElse(""));
    }

    @Test
    public void passwordCredentials() throws Exception {
        final var cfg = Config.newBuilder()
                .withClientCredentials("CLIENT_ID", "CLIENT_SECRET")
                .withEndpoint(new Endpoint() {
                    @Override
                    public String authURL() {
                        return "/auth-url";
                    }

                    @Override
                    public String tokenURL() {
                        return "/token-url";
                    }
                })
                .withRedirectURL("REDIRECT_URL")
                .withScope("scope1")
                .withScope("scope2")
                .withTransport((url, req) -> {
                    if (Optional.ofNullable(req.params.get("grant_type")).filter(c -> c.equals("password")).isEmpty()) {
                        throw new IOException("Grant type missing or incorrect");
                    }
                    if (Optional.ofNullable(req.params.get("password")).filter(c -> c.equals("password")).isEmpty()) {
                        throw new IOException("Password missing or incorrect");
                    }
                    if (Optional.ofNullable(req.params.get("username")).filter(c -> c.equals("user1")).isEmpty()) {
                        throw new IOException("Password missing or incorrect");
                    }
                    if (Optional.ofNullable(req.params.get("redirect_uri")).filter(c -> c.equals("REDIRECT_URL")).isPresent()) {
                        throw new IOException("Redirect URL is not part of password credentials request");
                    }
                    if (Optional.ofNullable(req.params.get("scope")).filter(c -> c.equals("scope1+scope2") || c.equals("scope2+scope1")).isEmpty()) {
                        throw new IOException("Redirect URL is not part of password credentials request");
                    }
                    return new StringReader("access_token=90d64460d14870c08c81352a05dedd3465940a7c&scope=user&token_type=bearer");
                })
                .withDecoder(reader -> {
                    final var buf = new StringBuilder();
                    try (var bufr = new BufferedReader(reader)) {
                        bufr.lines().forEach(buf::append);
                    }
                    final var map = Utils.HTTPParamsStringToMap(buf.toString());
                    final var extra = new HashMap<String, Object>();
                    map.forEach((key, value) -> {
                        if(!List.of("access_token", "refresh_token", "token_type", "expire_in").contains(key)) {
                            extra.put(key, value);
                        }
                    });
                    return new Token(map.get("access_token"), TokenType.fromString(map.get("token_type")), "", 0, extra);
                })
                .build();
        final var token = cfg.passwordCredentialsToken("user1", "password");
        Assertions.assertEquals("90d64460d14870c08c81352a05dedd3465940a7", token.accessToken);
        Assertions.assertSame(TokenType.BEARER, token.tokenType);
        Assertions.assertEquals("user", Optional.ofNullable(token.extra.get("scope")).map(Object::toString).orElse(""));
    }
}
