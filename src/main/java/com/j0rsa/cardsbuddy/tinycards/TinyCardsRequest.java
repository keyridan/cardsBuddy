package com.j0rsa.cardsbuddy.tinycards;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.j0rsa.cardsbuddy.translation.exceptions.JsonConversionException;
import io.vavr.CheckedFunction0;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.CookieStore;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.BasicCookieStore;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.j0rsa.cardsbuddy.SystemConstants.*;
import static org.apache.http.client.fluent.Request.*;

@Slf4j
class TinyCardsRequest {
    private static final String URI = "https://tinycards.duolingo.com/api/1/";
    private Request request;

    private TinyCardsRequest(Request request) {
        this.request = request;
    }

    static TinyCardsRequest post(String uri) {
        String requestUri = String.format("%s%s", URI, uri);
        return new TinyCardsRequest(Post(requestUri));
    }

    static TinyCardsRequest get(String uri) {
        String requestUri = String.format("%s%s", URI, uri);
        return new TinyCardsRequest(Get(requestUri));
    }

    static TinyCardsRequest patch(String uri) {
        String requestUri = String.format("%s%s", URI, uri);
        return new TinyCardsRequest(Patch(requestUri));
    }

    TinyCardsRequest jsonBodyOf(Object request) {
        CheckedFunction0<String> writeValue = () -> mapper().writeValueAsString(request);

        String json = Try.of(writeValue)
                .onFailure(e -> log.error("Error", e))
                .getOrElseThrow(JsonConversionException::new);
        log.debug("json: " + json);
        this.request.bodyString(json, ContentType.APPLICATION_JSON);
        return this;
    }

    Optional<TinyCardsResponse> execute() {
        return Try.of(() -> executorWithEmptyCookieStore().execute(this.request))
                .onFailure(e -> log.error("Error", e))
                .map(TinyCardsResponse::create)
                .toJavaOptional();
    }

    private Executor executorWithEmptyCookieStore() {
        CookieStore cookieStore = new BasicCookieStore();
        Executor executor = Executor.newInstance();
        executor.cookieStore(cookieStore);
        return executor;
    }

    TinyCardsRequest addCookiesHeaders(Map<String, String> headers) {
        String cookieValue = headers
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().contains(TINY_CARDS_PREFIX))
                .map(Map.Entry::getValue)
                .collect(Collectors.joining(";"));
        return addHeader("Cookie", cookieValue);
    }

    private TinyCardsRequest contentTypeHeader() {
        return addHeader(CONTENT_TYPE_HEADER, APPLICATION_JSON);
    }

    private TinyCardsRequest acceptHeader() {
        return addHeader(ACCEPT_HEADER, APPLICATION_JSON);
    }

    TinyCardsRequest jsonTransportHeader() {
        return contentTypeHeader().acceptHeader();
    }

    private TinyCardsRequest addHeader(String name, String value) {
        this.request.addHeader(name, value);
        return this;
    }

    private ObjectMapper mapper() {
        return new ObjectMapper();
    }

}
