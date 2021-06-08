package com.github.antonnaumov.oauth2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TestToken {
    @Test
    public void tokenExtra() {
        var token = new Token("", TokenType.BEARER, "", 0, Map.of("extra-key", "abc"));
        Assertions.assertEquals("abc", token.extra.get("extra-key"));

        token = new Token("", TokenType.BEARER, "", 0, Map.of("extra-key", 123));
        Assertions.assertEquals(123, token.extra.get("extra-key"));

        token = new Token("", TokenType.BEARER, "", 0, Map.of("extra-key", ""));
        Assertions.assertEquals("", token.extra.get("extra-key"));

        token = new Token("", TokenType.BEARER, "", 0, Map.of("other-key", "foo"));
        Assertions.assertNull(token.extra.get("extra-key"));
    }

    @Test
    public void tokenExpire() {
        var token = new Token("", TokenType.BEARER, "", System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(12));
        Assertions.assertFalse(token.expired());

        token = new Token("", TokenType.BEARER, "", System.currentTimeMillis() + Token.EXPIRE_DELTA);
        Assertions.assertFalse(token.expired());

        token = new Token("", TokenType.BEARER, "", System.currentTimeMillis() + (Token.EXPIRE_DELTA - 1));
        Assertions.assertTrue(token.expired());

        token = new Token("", TokenType.BEARER, "", System.currentTimeMillis() - TimeUnit.HOURS.toMillis(1));
        Assertions.assertTrue(token.expired());
    }

    @Test
    public void testTokenType() {
        Assertions.assertSame(TokenType.BEARER, TokenType.fromString("beAREr"));
        Assertions.assertSame(TokenType.BEARER, TokenType.fromString("Bearer"));
        Assertions.assertSame(TokenType.BASIC, TokenType.fromString("Basic"));
        Assertions.assertSame(TokenType.BASIC, TokenType.fromString("basic"));
        Assertions.assertSame(TokenType.MAC, TokenType.fromString("mac"));
        Assertions.assertSame(TokenType.MAC, TokenType.fromString("MAC"));
        Assertions.assertSame(TokenType.MAC, TokenType.fromString("mAc"));
    }
}
