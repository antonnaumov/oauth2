package com.github.antonnaumov.oauth2;

import java.io.Reader;

@FunctionalInterface
public interface Transport {
    Reader token(String url, Request request) throws Exception;
}
