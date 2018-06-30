package com.j0rsa.cardsbuddy.tinycards;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.CheckedFunction0;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.j0rsa.cardsbuddy.SystemConstants.TINY_CARDS_PREFIX;

@Slf4j
class TinyCardsParser {

    private static final Map<String, String> TINY_CARDS_HEADER_MAP = new HashMap<String, String>() {{
        put("jwt_token", TINY_CARDS_PREFIX + "jwt_token");
        put("session", TINY_CARDS_PREFIX + "session");
    }};

    private static <T> Optional<T> parse(String json, Class<T> tClass) {
        CheckedFunction0<T> readValue = () -> mapper().readValue(json, tClass);
        return Try.of(readValue)
                .onFailure(e -> log.error(e.toString()))
                .toJavaOptional();
    }

    static <V> Function<String, Optional<V>> parseTo(Class<V> vClass) {
        return json -> TinyCardsParser.parse(json, vClass);
    }

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

    private static ObjectMapper mapper() {
        return new ObjectMapper();
    }
}
