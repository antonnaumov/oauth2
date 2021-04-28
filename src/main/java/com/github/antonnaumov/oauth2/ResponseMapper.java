package com.github.antonnaumov.oauth2;

import java.io.InputStream;

@FunctionalInterface
public interface ResponseMapper {
    Token map(final InputStream stream) throws Exception;
}
