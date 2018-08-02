package com.j0rsa.cardsbuddy.leo;

import com.j0rsa.cardsbuddy.translation.model.Language;
import com.j0rsa.cardsbuddy.translation.model.TranslationRequest;
import org.apache.http.client.fluent.Request;
import org.junit.Test;

import static com.j0rsa.cardsbuddy.translation.model.TranslationRequest.builder;
import static org.apache.http.client.fluent.Request.Get;
import static org.assertj.core.api.Assertions.assertThat;

public class RequestFactoryTest {

    @Test
    public void testCreateWhenTranslateFromEnToDe() {
        String expectedUri = "https://dict.leo.org/dictQuery/m-vocab/ende/query.xml?tolerMode=nof&lp=ende&lang=de&rmWords=off&rmSearch=on&search=apple&searchLoc=0&resultOrder=basic&multiwordShowSingle=on";
        final TranslationRequest testTranslationRequest = builder()
                .fromLanguage(Language.Code.EN)
                .toLanguage(Language.Code.DE)
                .word("apple")
                .build();

        Request request = RequestFactory.infoRequest(testTranslationRequest);

        assertThat(request.toString()).isEqualTo(Get(expectedUri).toString());
    }

    @Test
    public void testCreateWhenTranslateFromDeToEnThenLanguagesPropertiesAreTheSameAsFromEnToDe() {
        String expectedUri = "https://dict.leo.org/dictQuery/m-vocab/ende/query.xml?tolerMode=nof&lp=ende&lang=de&rmWords=off&rmSearch=on&search=lesen&searchLoc=0&resultOrder=basic&multiwordShowSingle=on";
        final TranslationRequest testTranslationRequest = builder()
                .fromLanguage(Language.Code.DE)
                .toLanguage(Language.Code.EN)
                .word("lesen")
                .build();

        Request request = RequestFactory.infoRequest(testTranslationRequest);

        assertThat(request.toString()).isEqualTo(Get(expectedUri).toString());
    }

    @Test
    public void testCreateWhenTranslateFromEnToFrThenSecondLanguageIsStillDe() {
        String expectedUri = "https://dict.leo.org/dictQuery/m-vocab/frde/query.xml?tolerMode=nof&lp=frde&lang=de&rmWords=off&rmSearch=on&search=pomme&searchLoc=0&resultOrder=basic&multiwordShowSingle=on";
        final TranslationRequest testTranslationRequest = builder()
                .fromLanguage(Language.Code.FR)
                .toLanguage(Language.Code.EN)
                .word("pomme")
                .build();

        Request request = RequestFactory.infoRequest(testTranslationRequest);

        assertThat(request.toString()).isEqualTo(Get(expectedUri).toString());
    }
}