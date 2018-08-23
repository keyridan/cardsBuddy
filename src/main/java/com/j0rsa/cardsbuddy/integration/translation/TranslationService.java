package com.j0rsa.cardsbuddy.integration.translation;

import com.j0rsa.cardsbuddy.integration.translation.exceptions.TranslationNotFoundException;
import com.j0rsa.cardsbuddy.integration.translation.model.Translation;
import com.j0rsa.cardsbuddy.integration.translation.model.TranslationRequest;
import com.j0rsa.cardsbuddy.integration.translation.parser.TranslationParser;

public class TranslationService {
    public static Translation translate(TranslationRequest request) {
        return Translator.translate(request)
                .map(TranslationParser::parseTranslatedWord)
                .orElseThrow(TranslationNotFoundException::new);
    }
}
