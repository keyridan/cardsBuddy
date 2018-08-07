package com.j0rsa.cardsbuddy.leo;

import com.j0rsa.cardsbuddy.translation.model.Language;
import com.j0rsa.cardsbuddy.translation.model.TranslationRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.fluent.Request;

import static com.j0rsa.cardsbuddy.common.UriUtils.encodeUrl;
import static org.apache.http.client.fluent.Request.Get;

@Slf4j
class RequestFactory {
    private static String INFO_URI = "https://dict.leo.org/dictQuery/m-vocab/%1$sde/query.xml" +
            "?tolerMode=nof&lp=%1$sde" +
            "&lang=de&rmWords=off&rmSearch=on" +
            "&search=%2$s&searchLoc=0&resultOrder=basic&multiwordShowSingle=on";
    private static String FLEC_TABLE_URI = "https://dict.leo.org/dictQuery/m-vocab/%1$sde/stemming.xml%2$s";

    static Request infoRequest(TranslationRequest translationRequest) {
        Language.Code fromLanguage = getFromLanguageOrDefaultLanguage(translationRequest);
        String uri = String.format(INFO_URI, fromLanguage.getValue().toLowerCase(), encodeUrl(translationRequest.getWord()));
        return Get(uri);
    }

    static Request flecTableRequest(String flecTableUrlPart, TranslationRequest translationRequest) {
        Language.Code fromLanguage = getFromLanguageOrDefaultLanguage(translationRequest);
        String uri = String.format(FLEC_TABLE_URI, fromLanguage.getValue().toLowerCase(), flecTableUrlPart);
        return Get(uri);
    }

    private static Language.Code getFromLanguageOrDefaultLanguage(TranslationRequest translationRequest) {
        return isDefaultLeoLanguage(translationRequest)
                ? Language.Code.EN
                : translationRequest.getFromLanguage();
    }

    private static boolean isDefaultLeoLanguage(TranslationRequest translationRequest) {
        return translationRequest.getFromLanguage().equals(Language.Code.DE);
    }
}
