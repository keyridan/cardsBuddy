package com.j0rsa.cardsbuddy.integration.info.leo;

import com.j0rsa.cardsbuddy.integration.translation.model.TranslationRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.fluent.Request;

import java.util.Optional;

import static com.j0rsa.cardsbuddy.integration.info.leo.RequestFactory.flecTableRequest;
import static com.j0rsa.cardsbuddy.integration.info.leo.RequestFactory.infoRequest;
import static io.vavr.control.Try.of;

@Slf4j
class LeoInfoSearcher {

    static Optional<String> search(TranslationRequest request) {
        return makeRequest(infoRequest(request));
    }

    static Optional<String> searchFlecTableIfPartExist(TranslationRequest request, String flecTableUrlPart) {
        return flecTableUrlPart != null
                ? makeRequest(flecTableRequest(flecTableUrlPart, request))
                : Optional.empty();
    }

    private static Optional<String> makeRequest(Request request) {
        return of(request::execute)
                .onFailure(e -> log.error("Error", e))
                .mapTry(response -> response.returnContent().asString())
                .peek(log::debug)
                .onFailure(e -> log.error("Error", e))
                .toJavaOptional();
    }
}
