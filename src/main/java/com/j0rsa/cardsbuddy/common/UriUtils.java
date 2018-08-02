package com.j0rsa.cardsbuddy.common;

import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;

import static java.net.URLEncoder.encode;

@Slf4j
public class UriUtils {
    public static String encodeUrl(String url) {
        return Try.of(() -> encode(url, "UTF-8"))
                .onFailure(e -> log.error(e.toString()))
                .getOrElse("");
    }
}
