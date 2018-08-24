package com.j0rsa.cardsbuddy.integration.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.CheckedFunction0;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.function.Function;

@Slf4j
public class ResponseParser {
    private static <T> Optional<T> parse(String json, Class<T> tClass) {
        CheckedFunction0<T> readValue = () -> mapper().readValue(json, tClass);
        return Try.of(readValue)
                .onFailure(e -> log.error("Error", e))
                .toJavaOptional();
    }

    public static <V> Function<String, Optional<V>> parseTo(Class<V> vClass) {
        return json -> ResponseParser.parse(json, vClass);
    }

    private static ObjectMapper mapper() {
        return new ObjectMapper();
    }
}
