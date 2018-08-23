package com.j0rsa.cardsbuddy.integration.tinycards;

import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.fluent.Response;

import java.util.Optional;

@Slf4j
class TinyCardsResponse {
    private Response response;

    private TinyCardsResponse(Response response) {
        this.response = response;
    }

    static TinyCardsResponse create(Response response) {
        return new TinyCardsResponse(response);
    }

    Optional<TinyCardsHttpResponse> returnResponse() {
        return Try.of(response::returnResponse)
                .onFailure(e -> log.error("Error", e))
                .peek(response -> log.info("returnResponse HttpResponse: " + response.toString()))
                .map(TinyCardsHttpResponse::create)
                .toJavaOptional();
    }
}
