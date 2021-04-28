package com.github.antonnaumov.oauth2;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public final class Utils {
    private Utils() {}

    public static String mapToHTTPParamsString(final Map<String, Object> params) {
        final var builder = new StringBuilder();
        for (var param : params.entrySet()) {
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(param.getKey(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(param.getValue().toString(), StandardCharsets.UTF_8));
        }
        return builder.toString();
    }
}
