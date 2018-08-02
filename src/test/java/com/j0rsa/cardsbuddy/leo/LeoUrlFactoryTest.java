package com.j0rsa.cardsbuddy.leo;

import com.j0rsa.cardsbuddy.translation.model.Language;
import com.j0rsa.cardsbuddy.translation.model.TranslationRequest;
import org.junit.Test;

import static com.j0rsa.cardsbuddy.DefaultData.aTranslationRequest;
import static com.j0rsa.cardsbuddy.DefaultData.requestFromDeToEn;
import static org.assertj.core.api.Assertions.assertThat;

public class LeoUrlFactoryTest {
    private String deToEn = "https://dict.leo.org/englisch-deutsch/test";
    private String deToEnWithSpace = "https://dict.leo.org/englisch-deutsch/to+test";
    private String frToDeUrl = "https://dict.leo.org/franz%C3%B6sisch-deutsch/test";

    @Test
    public void testWhenFromDeToEn() {
        TranslationRequest request = requestFromDeToEn()
                .word("test")
                .build();

        String url = LeoUrlFactory.createUrl(request);

        assertThat(url).isEqualTo(deToEn);
    }

    @Test
    public void testWhenFromDeToEnWhenWordWIthSpace() {
        TranslationRequest request = requestFromDeToEn()
                .word("to test")
                .build();

        String url = LeoUrlFactory.createUrl(request);

        assertThat(url).isEqualTo(deToEnWithSpace);
    }

    @Test
    public void testWhenFromFrToDe() {
        TranslationRequest request = aTranslationRequest()
                .fromLanguage(Language.Code.FR)
                .toLanguage(Language.Code.DE)
                .word("test")
                .build();

        String url = LeoUrlFactory.createUrl(request);
        assertThat(url).isEqualTo(frToDeUrl);
    }

    @Test
    public void testWhenFromDeToFrThenTheSameAsFrToDe() {
        TranslationRequest request = aTranslationRequest()
                .fromLanguage(Language.Code.FR)
                .toLanguage(Language.Code.DE)
                .word("test")
                .build();

        String url = LeoUrlFactory.createUrl(request);

        assertThat(url).isEqualTo(frToDeUrl);
    }

    @Test
    public void testWhenFromDeToNotInTheListLanguageThenDeToEn() {
        TranslationRequest request = aTranslationRequest()
                .fromLanguage(Language.Code.DE)
                .toLanguage(Language.Code.AUTO)
                .word("test")
                .build();

        String url = LeoUrlFactory.createUrl(request);

        assertThat(url).isEqualTo(deToEn);
    }

    @Test
    public void testWhenFromFrToNotInTheListLanguageThenFrToDe() {
        TranslationRequest request = aTranslationRequest()
                .fromLanguage(Language.Code.FR)
                .toLanguage(Language.Code.AUTO)
                .word("test")
                .build();

        String url = LeoUrlFactory.createUrl(request);

        assertThat(url).isEqualTo(frToDeUrl);
    }
}