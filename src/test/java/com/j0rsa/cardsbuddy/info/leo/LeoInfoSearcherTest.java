package com.j0rsa.cardsbuddy.info.leo;

import com.j0rsa.cardsbuddy.translation.model.TranslationRequest;
import org.junit.Test;

import java.util.Optional;

import static com.j0rsa.cardsbuddy.DefaultData.requestFromDeToEn;
import static com.j0rsa.cardsbuddy.info.leo.LeoInfoSearcher.search;
import static org.assertj.core.api.Assertions.assertThat;

public class LeoInfoSearcherTest {

    @Test
    public void testTranslateFromDeToEn() {
        final TranslationRequest testTranslationRequest = requestFromDeToEn().build();

        final Optional<String> info = search(testTranslationRequest);

        assertThat(info).isPresent();
    }
}