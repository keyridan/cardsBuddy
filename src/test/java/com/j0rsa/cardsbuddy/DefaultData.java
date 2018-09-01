package com.j0rsa.cardsbuddy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.j0rsa.cardsbuddy.controller.model.leo.LeoInfo;
import com.j0rsa.cardsbuddy.domain.model.Sentences;
import com.j0rsa.cardsbuddy.integration.tinycards.model.Details;
import com.j0rsa.cardsbuddy.integration.tinycards.model.LoginRequest;
import com.j0rsa.cardsbuddy.integration.translation.model.*;
import org.assertj.core.util.Lists;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static com.j0rsa.cardsbuddy.integration.translation.model.TranslationRequest.builder;

public class DefaultData {
    private static TestData testData;

    static {
        try {
            testData = TestData.loadFrom("tinyUser.yml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static LoginRequest.LoginRequestBuilder defaultLoginRequest() {
        TestData testData = getTestData();
        return LoginRequest.builder()
                .identifier(testData.getEmail())
                .password(testData.getPassword());
    }

    private static TestData getTestData() {
        return testData;
    }

    public static String defaultTinyCardsId() {
        return getTestData().getId();
    }

    public static UUID defaultDecksId() {
        return getTestData().getDeckId();
    }

    static String testTinyCardsSession() {
        return getTestData().getTestTinyCardsSession();
    }

    public static String testImageApiKey() {
        return getTestData().getImageApiKey();
    }

    static String testTinyCardsJwt() {
        return getTestData().getTestTinyCardsJwt();
    }

    public static TranslationRequest.TranslationRequestBuilder requestFromDeToEn() {
        return aTranslationRequest()
                .fromLanguage(Language.Code.DE)
                .toLanguage(Language.Code.EN)
                .word("Prüfung");
    }

    public static TranslationRequest.TranslationRequestBuilder requestToEn() {
        return aTranslationRequest()
                .toLanguage(Language.Code.EN);
    }

    public static TranslationRequest.TranslationRequestBuilder aTranslationRequest() {
        return builder();
    }

    public static TranslationRequest.TranslationRequestBuilder requestFromEnToDe() {
        return requestFromEn()
                .toLanguage(Language.Code.DE);
    }

    public static TranslationRequest.TranslationRequestBuilder requestFromAutoToDe() {
        return aTranslationRequest()
                .word("тест")
                .fromLanguage(Language.Code.AUTO)
                .toLanguage(Language.Code.DE);
    }

    public static Translation.TranslationBuilder translationFromAutoToDe() {
        return Translation.builder()
                .translatedWords(Arrays.asList(translatedFromAutoToDeWords()))
                .pair(translationAutoToDePair())
                .languageFrom(Language.Code.RU);
    }

    public static TranslationRequest.TranslationRequestBuilder requestFromEn() {
        return aTranslationRequest()
                .fromLanguage(Language.Code.EN)
                .word("exam");
    }

    public static Translation.TranslationBuilder translationFromEnToDe() {
        return Translation.builder()
                .translatedWords(Arrays.asList(translatedDeWords()))
                .pair(translationEnToDePair())
                .languageFrom(Language.Code.EN);
    }

    public static Translation.TranslationBuilder translationFromEnToDeWithEmptyTranslatedWords(String wordFrom, String wordTo) {
        return Translation.builder()
                .translatedWords(new ArrayList<>())
                .pair(TranslationPair.builder().wordFrom(wordFrom).wordTo(wordTo).build())
                .languageFrom(Language.Code.EN);
    }

    private static TranslatedWord[] translatedDeWords() {
        return new TranslatedWord[]{
                TranslatedWord.builder().article("die").word("Prüfung").build(),
                TranslatedWord.builder().article("das").word("Examen").build(),
                TranslatedWord.builder().article("die").word("Klausur").build()
        };
    }

    private static TranslatedWord[] translatedFromAutoToDeWords() {
        return new TranslatedWord[]{
                TranslatedWord.builder().article("der").word("Test").build(),
                TranslatedWord.builder().article("der").word("Prüfvorschrift").build(),
                TranslatedWord.builder().article("die").word("Erprobung").build()
        };
    }

    public static Translation.TranslationBuilder translationFromDeToEn() {
        return Translation.builder()
                .translatedWords(Arrays.asList(translatedEnWords()))
                .pair(translationDeToEnPair())
                .languageFrom(Language.Code.DE);
    }

    private static TranslatedWord[] translatedEnWords() {
        return new TranslatedWord[]{
                TranslatedWord.builder().word("examination").build(),
                TranslatedWord.builder().word("test").build(),
                TranslatedWord.builder().word("audit").build(),
                TranslatedWord.builder().word("testing").build(),
                TranslatedWord.builder().word("check").build(),
                TranslatedWord.builder().word("exam").build(),
                TranslatedWord.builder().word("inspection").build(),
                TranslatedWord.builder().word("checking").build(),
                TranslatedWord.builder().word("consideration").build(),
                TranslatedWord.builder().word("trial").build(),
                TranslatedWord.builder().word("proof").build(),
                TranslatedWord.builder().word("assay").build(),
                TranslatedWord.builder().word("perusal").build(),
                TranslatedWord.builder().word("quiz").build()
        };
    }

    public static TranslationPair translationEnToDePair() {
        return TranslationPair.builder().wordFrom("exam").wordTo("Prüfung").build();
    }

    static TranslationPair translationAutoToDePair() {
        return TranslationPair.builder().wordFrom("тест").wordTo("Quiz").build();
    }

    static TranslationPair translationDeToEnPair() {
        return TranslationPair.builder().wordFrom("Prüfung").wordTo("exam").build();
    }

    public static List readJson(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, List.class);
    }

    public static LeoInfo.LeoInfoBuilder defaultInfoNoun() {
        return anInfoVerb()
                .title("noun")
                .description("die Prüfung Pl.: die Prüfungen")
                .url("https://dict.leo.org/englisch-deutsch/Pr%C3%BCfung");
    }

    public static LeoInfo.LeoInfoBuilder defaultInfoVerb() {
        return anInfoVerb()
                .description("(etw. Akk.) lesen| las, gelesen |")
                .title("verb")
                .url("https://dict.leo.org/englisch-deutsch/lesen");
    }

    private static LeoInfo.LeoInfoBuilder anInfoVerb() {
        return LeoInfo.builder();
    }

    public static LeoInfo.LeoInfoBuilder defaultInfoVerbWithSpace() {
        return anInfoVerb()
                .description("Fähigkeit zu lesen")
                .url("https://dict.leo.org/englisch-deutsch/zu+lesen");
    }

    public static Details.DetailsBuilder details() {
        return Details.builder();
    }

    public static Pageable defaultPage() {
        return PageRequest.of(0, 10);
    }

    public static Pageable defaultPage(int paguNumber) {
        return PageRequest.of(paguNumber, 10);
    }

    public static Sentences.SentencesBuilder aSentenceWithText(String text) {
        return aSentence()
                .id(new Random().nextLong())
                .text(text);
    }

    public static Sentences.SentencesBuilder aSentenceWithSupportedLanguage() {
        return aSentence()
                .lang(Language.Code.DE.getIso639_3Value())
                .text("test text")
                .id(new Random().nextLong());
    }

    private static Sentences.SentencesBuilder aSentence() {
        return Sentences.builder()
                .translations(Lists.newArrayList());
    }
}
