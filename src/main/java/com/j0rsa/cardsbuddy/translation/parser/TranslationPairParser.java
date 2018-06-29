package com.j0rsa.cardsbuddy.translation.parser;

import com.j0rsa.cardsbuddy.translation.exceptions.ParserException;
import com.j0rsa.cardsbuddy.translation.model.TranslationPair;

import java.util.List;

import static com.j0rsa.cardsbuddy.PatternMatchingUtils.$List;
import static io.vavr.API.*;
import static io.vavr.Predicates.instanceOf;

class TranslationPairParser {
    static TranslationPair parse(List list) {
        return Match(list).of(
                Case($List($(instanceOf(String.class)), $(instanceOf(String.class))),
                        (wordTo, wordFrom) -> TranslationPair.builder()
                                .wordFrom((String) wordFrom)
                                .wordTo((String) wordTo)
                                .build()),
                Case($(), (p) -> {
                    throw new ParserException();
                })
        );
    }
}
