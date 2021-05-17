package com.github.antonnaumov.oauth2;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class Utils {
    private Utils() {}

    public static String mapToHTTPParamsString(final Map<String, Object> params) {
        final var builder = new StringBuilder();
        params.forEach((key, value) -> {
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(key, StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(value.toString(), StandardCharsets.UTF_8));
        });
        return builder.toString();
    }

    public static Map<String, String> HTTPParamsStringToMap(final String string) {
        final var map = new HashMap<String, String>();
        Arrays.stream(string.split("&")).filter(pair -> pair.contains("=")).forEach(pair -> {
            final var idx = pair.indexOf('=');
            final var key = pair.substring(0, idx);
            final var val = pair.substring(idx + 1);
            map.put(URLDecoder.decode(key, StandardCharsets.UTF_8), URLDecoder.decode(val, StandardCharsets.UTF_8));
        });
        return Map.copyOf(map);
    }
}
