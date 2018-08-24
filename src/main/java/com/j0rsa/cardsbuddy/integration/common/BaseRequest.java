package com.j0rsa.cardsbuddy.integration.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.j0rsa.cardsbuddy.integration.translation.exceptions.JsonConversionException;
import io.vavr.CheckedFunction0;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.CookieStore;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicCookieStore;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.j0rsa.cardsbuddy.SystemConstants.*;
import static org.apache.http.client.fluent.Request.*;

@Slf4j
public abstract class BaseRequest<T extends BaseRequest, R extends BaseResponse> {
    private Request request;

    protected BaseRequest(Request request) {
        this.request = request;
    }

    protected static Request post(String baseUri, String uri) {
        String requestUri = String.format("%s%s", baseUri, uri);
        return Post(requestUri);
    }

    protected static Request get(String baseUri, String uri) {
        String requestUri = String.format("%s%s", baseUri, uri);
        return Get(requestUri);
    }

    protected static Request patch(String baseUri, String uri) {
        String requestUri = String.format("%s%s", baseUri, uri);
        return Patch(requestUri);
    }

    public T bodyOf(byte[] bytes, ContentType contentType) {
        HttpEntity entity = MultipartEntityBuilder
                .create()
                .addBinaryBody(
                        "imageFile",
                        bytes,
                        contentType,
                        "factImage.jpg")
                .build();
        this.request.body(entity);
        return (T) this;
    }

    public T jsonBodyOf(Object request) {
        CheckedFunction0<String> writeValue = () -> mapper().writeValueAsString(request);

        String json = Try.of(writeValue)
                .onFailure(e -> log.error("Error", e))
                .getOrElseThrow(JsonConversionException::new);
        log.debug("json: " + json);
        this.request.bodyString(json, ContentType.APPLICATION_JSON);
        return (T) this;
    }

    protected abstract R createResponse(Response response);

    public Optional<R> execute() {
        return Try.of(() -> executorWithEmptyCookieStore().execute(this.request))
                .onFailure(e -> log.error("Error", e))
                .map(this::createResponse)
                .toJavaOptional();
    }

    private Executor executorWithEmptyCookieStore() {
        CookieStore cookieStore = new BasicCookieStore();
        Executor executor = Executor.newInstance();
        executor.cookieStore(cookieStore);
        return executor;
    }

    protected T addCookiesHeaders(Map<String, String> headers, String prefix) {
        String cookieValue = headers
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().contains(prefix))
                .map(Map.Entry::getValue)
                .collect(Collectors.joining(";"));
        return addHeader("Cookie", cookieValue);
    }

    private T acceptHeader() {
        return addHeader(ACCEPT_HEADER, APPLICATION_JSON);
    }

    public T jsonTransportHeader() {
        return (T) acceptHeader().addHeader(CONTENT_TYPE_HEADER, APPLICATION_JSON);
    }

    public T addHeader(String name, String value) {
        this.request.addHeader(name, value);
        return (T) this;
    }

    private ObjectMapper mapper() {
        return new ObjectMapper();
    }
}
