package com.github.antonnaumov.oauth2;

import java.io.Reader;

@FunctionalInterface
public interface Decoder {
    Token decode(final Reader responseReader) throws Exception;
}
