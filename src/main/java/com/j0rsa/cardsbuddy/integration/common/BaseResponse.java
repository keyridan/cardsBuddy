package com.j0rsa.cardsbuddy.integration.common;

import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Response;

import java.util.Optional;

@Slf4j
public abstract class BaseResponse<R extends BaseHttpResponse> {
    private Response response;

    protected BaseResponse(Response response) {
        this.response = response;
    }

    protected abstract R createResponse(HttpResponse response);

    public Optional<R> returnResponse() {
        return Try.of(response::returnResponse)
                .onFailure(e -> log.error("Error", e))
                .peek(response -> log.info("returnResponse HttpResponse: " + response.toString()))
                .map(this::createResponse)
                .toJavaOptional();
    }
}
