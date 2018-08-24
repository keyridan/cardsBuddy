package com.j0rsa.cardsbuddy.integration.tinycards;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.j0rsa.cardsbuddy.SystemConstants.TINY_CARDS_PREFIX;

@Slf4j
class TinyCardsParser {

    private static final Map<String, String> TINY_CARDS_HEADER_MAP = new HashMap<String, String>() {{
        put("jwt_token", TINY_CARDS_PREFIX + "jwt_token");
        put("session", TINY_CARDS_PREFIX + "session");
    }};

    static Map<String, String> parseHeaders(List<String> headers) {
        return headers.stream()
                .map(header -> header.split(";"))
                .map(Lists::newArrayList)
                .flatMap(List::stream)
                .map(String::trim)
                .distinct()
                .map(headerLine -> headerLine.contains("=") ? headerLine : String.format("%s=true", headerLine))
                .peek(header -> log.debug("parseHeaders: " + header))
                .collect(Collectors.toMap(
                        header -> String.format("%s%s", TINY_CARDS_PREFIX, header.substring(0, header.indexOf("=")))
                        , header -> header));
    }

    static Map<String, String> parseAuthenticationHeaders(List<String> headers) {
        Collection<String> authenticationHeaderKeys = TINY_CARDS_HEADER_MAP.values();
        return parseHeaders(headers)
                .entrySet()
                .stream()
                .filter(mapEntry -> authenticationHeaderKeys.contains(mapEntry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
