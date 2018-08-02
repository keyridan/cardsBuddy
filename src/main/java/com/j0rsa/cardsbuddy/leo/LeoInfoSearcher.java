package com.j0rsa.cardsbuddy.leo;

import com.j0rsa.cardsbuddy.translation.model.TranslationRequest;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import static com.j0rsa.cardsbuddy.leo.RequestFactory.infoRequest;
import static io.vavr.control.Try.of;

@Slf4j
class LeoInfoSearcher {

    static Optional<String> search(TranslationRequest request) {
        return of(infoRequest(request)::execute)
                .onFailure(e -> log.error(e.toString()))
                .mapTry(response -> response.returnContent().asString())
                .peek(log::debug)
                .onFailure(e -> log.error(e.toString()))
                .toJavaOptional();
    }
}
