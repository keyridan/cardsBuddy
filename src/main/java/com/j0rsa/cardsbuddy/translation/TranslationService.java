package com.j0rsa.cardsbuddy.translation;

import com.j0rsa.cardsbuddy.translation.exceptions.TranslationNotFoundException;
import com.j0rsa.cardsbuddy.translation.model.Translation;
import com.j0rsa.cardsbuddy.translation.model.TranslationRequest;
import com.j0rsa.cardsbuddy.translation.parser.TranslationParser;

public class TranslationService {
    public static Translation translate(TranslationRequest request) {
        return Translator.translate(request)
                .map(TranslationParser::parseTranslatedWord)
                .orElseThrow(TranslationNotFoundException::new);
    }
}
