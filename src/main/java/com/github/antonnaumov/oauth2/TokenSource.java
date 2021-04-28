package com.github.antonnaumov.oauth2;

@FunctionalInterface
public interface TokenSource {
    Token token() throws Exception;
}
