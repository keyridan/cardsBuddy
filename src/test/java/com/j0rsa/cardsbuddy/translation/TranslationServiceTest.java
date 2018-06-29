package com.j0rsa.cardsbuddy.translation;

import com.j0rsa.cardsbuddy.translation.model.Translation;
import com.j0rsa.cardsbuddy.translation.model.TranslationRequest;
import org.junit.Test;

import static com.j0rsa.cardsbuddy.DefaultData.*;
import static org.assertj.core.api.Assertions.assertThat;

public class TranslationServiceTest {

    @Test
    public void testTranslateFromEnToDe() {
        testTranslate(requestFromEnToDe(), translationFromEnToDe());
    }

    @Test
    public void testTranslateFromAutoToDe() {
        testTranslate(requestFromAutoToDe(), translationFromAutoToDe());
    }

    @Test
    public void testTranslateFromEnToDeWhenNoTranslatedWordsThenEmptyTranslatedPair() {
        testTranslate(requestFromEnToDe().word("desc"),
                translationFromEnToDeWithEmptyTranslatedWords("desc", "beschreibung"));
    }

    @Test
    public void testTranslatePhrase() {
        testTranslate(requestFromEnToDe().word("how are you"),
                translationFromEnToDeWithEmptyTranslatedWords("how are you", "Wie geht es dir"));
    }

    @Test
    public void testTranslateFromDeToEn() {
        testTranslate(requestFromDeToEn(), translationFromDeToEn());
    }

    private void testTranslate(TranslationRequest.TranslationRequestBuilder requestBuilder, Translation.TranslationBuilder translationBuilder) {
        TranslationRequest request = requestBuilder.build();
        Translation expected = translationBuilder.build();

        Translation translation = TranslationService.translate(request);

        assertThat(translation).isEqualTo(expected);
    }
}