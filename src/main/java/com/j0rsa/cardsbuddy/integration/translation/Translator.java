package com.j0rsa.cardsbuddy.integration.translation;

import com.j0rsa.cardsbuddy.integration.translation.model.TranslationRequest;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import static com.j0rsa.cardsbuddy.integration.translation.RequestBuilder.uri;
import static io.vavr.control.Try.of;

@Slf4j
class Translator {

    private static org.apache.http.client.fluent.Request from(TranslationRequest request) {
        return uri()
                .from(request.getFromLanguage())
                .to(request.getToLanguage())
                .word(request.getWord())
                .build();
    }

    static Optional<String> translate(TranslationRequest request) {
        return of(from(request)::execute)
                .onFailure(e -> log.error("Error", e))
                .mapTry(response -> response.returnContent().asString())
                .onFailure(e -> log.error("Error", e))
                .toJavaOptional();
    }

}
