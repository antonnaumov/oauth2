package com.github.antonnaumov.oauth2;

/**
 * Thrown when an OAuth2 configuration required parameter is missing.
 */
public class ConfigurationParameterMissingException extends Exception {
    public ConfigurationParameterMissingException(final String name) {
        super("OAuth2: Configuration mandatory parameter " + name + " is missing");
    }
}
