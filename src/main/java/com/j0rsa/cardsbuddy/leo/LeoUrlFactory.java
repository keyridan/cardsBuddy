package com.j0rsa.cardsbuddy.leo;

import com.j0rsa.cardsbuddy.translation.model.Language;
import com.j0rsa.cardsbuddy.translation.model.TranslationRequest;
import io.vavr.Tuple2;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

import static com.j0rsa.cardsbuddy.common.UriUtils.encodeUrl;

@Slf4j
class LeoUrlFactory {
    private static final String LEO_PAGE_URL_TEMPLE = "https://dict.leo.org/%s/%s";
    private static Map<Tuple2<Language.Code, Language.Code>, String> leoLanguagePairs = new HashMap<>();

    static {
        addPair(Language.Code.DE, Language.Code.EN, "englisch-deutsch");
        addPair(Language.Code.DE, Language.Code.FR, "französisch-deutsch");
        addPair(Language.Code.DE, Language.Code.ES, "spanisch-deutsch");
        addPair(Language.Code.DE, Language.Code.IT, "italienisch-deutsch");
        addPair(Language.Code.DE, Language.Code.RU, "russisch-deutsch");
        addPair(Language.Code.DE, Language.Code.PT, "portugiesisch-deutsch");
        addPair(Language.Code.DE, Language.Code.PL, "polnisch-deutsch");
    }

    private static void addPair(Language.Code code1, Language.Code code2, String value) {
        leoLanguagePairs.put(pair(code1, code2), value);
        leoLanguagePairs.put(pair(code2, code1), value);
    }

    private static Tuple2<Language.Code, Language.Code> pair(Language.Code code1, Language.Code code2) {
        return new Tuple2<>(code1, code2);
    }

    private static String languagesNames(TranslationRequest request) {
        Tuple2<Language.Code, Language.Code> pair = pair(request.getFromLanguage(), request.getToLanguage());
        return leoLanguagePairs.containsKey(pair)
                ? leoLanguagePairs.get(pair)
                : defaultValue(request.getFromLanguage());
    }

    private static String defaultValue(Language.Code fromLanguage) {
        return Language.Code.DE.equals(fromLanguage)
                ? leoLanguagePairs.get(pair(Language.Code.DE, Language.Code.EN))
                : leoLanguagePairs.get(pair(Language.Code.DE, fromLanguage));
    }

    static String createUrl(TranslationRequest request) {
        return String.format(LEO_PAGE_URL_TEMPLE, encodeUrl(languagesNames(request)), encodeUrl(request.getWord()));
    }
}
