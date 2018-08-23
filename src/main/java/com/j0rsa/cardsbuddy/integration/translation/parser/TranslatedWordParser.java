package com.j0rsa.cardsbuddy.integration.translation.parser;

import com.j0rsa.cardsbuddy.integration.translation.exceptions.ParserException;
import com.j0rsa.cardsbuddy.integration.translation.model.TranslatedWord;

import java.util.List;

import static com.j0rsa.cardsbuddy.PatternMatchingUtils.$List;
import static io.vavr.API.*;
import static io.vavr.Predicates.*;

class TranslatedWordParser {
    static TranslatedWord parse(List list) {
        return Match(list).of(
                Case($List($(instanceOf(String.class)), $(), $(), $(), $(allOf(isNotNull(), instanceOf(String.class)))),
                        (word, p, p1, p2, article) -> TranslatedWord.builder()
                                .word((String) word)
                                .article((String) article)
                                .build()),
                Case($List($(instanceOf(String.class)), $(), $(), $(), $()),
                        (word, p, p1, p2, articleAbsent) -> TranslatedWord.builder()
                                .word((String) word)
                                .build()),
                Case($(), (p) -> {
                    throw new ParserException();
                })
        );
    }
}
