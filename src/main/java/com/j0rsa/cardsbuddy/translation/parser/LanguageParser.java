package com.j0rsa.cardsbuddy.translation.parser;

import com.j0rsa.cardsbuddy.translation.exceptions.NoSuchLanguageException;
import com.j0rsa.cardsbuddy.translation.exceptions.ParserException;
import com.j0rsa.cardsbuddy.translation.model.Language;

import java.util.List;

import static com.j0rsa.cardsbuddy.PatternMatchingUtils.$ListElementNo;
import static io.vavr.API.*;
import static io.vavr.Predicates.instanceOf;

class LanguageParser {

    static Language.Code parse(List list) {
        return Match(list).of(
                Case($ListElementNo(2, $(instanceOf(String.class))),
                        (language) -> Language.Code.of(language.toString())
                                .orElseThrow(NoSuchLanguageException::new)),
                Case($(), (l) -> {
                    throw new ParserException();
                })
        );
    }
}
