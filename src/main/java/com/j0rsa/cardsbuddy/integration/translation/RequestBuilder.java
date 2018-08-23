package com.j0rsa.cardsbuddy.integration.translation;

import com.j0rsa.cardsbuddy.integration.translation.model.Language;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.fluent.Request;

import static com.j0rsa.cardsbuddy.common.UriUtils.encodeUrl;
import static org.apache.http.client.fluent.Request.Get;

@Slf4j
public class RequestBuilder {
    private String uri = "http://translate.googleapis.com/translate_a/single?client=gtx&ie=UTF-8&oe=UTF-8";
    private String translationsAndPartOfSpeechParameter = "&dt=bd";
    private String translationParameter = "&dt=t";
    private String translationParameters = translationParameter + translationsAndPartOfSpeechParameter;

    public static RequestBuilder uri() {
        return new RequestBuilder();
    }

    public RequestBuilder from(Language.Code fromLanguage) {
        this.uri = String.format("%s&sl=%s", this.uri, fromLanguage.getValue());
        return this;
    }

    public RequestBuilder to(Language.Code toLanguage) {
        this.uri = String.format("%s&tl=%s", this.uri, toLanguage.getValue());
        return this;
    }

    public RequestBuilder word(String word) {
        String encodedWord = encodeUrl(word);
        this.uri = String.format("%s%s&q=%s",
                this.uri,
                translationParameters,
                encodedWord
        );
        return this;
    }

    public Request build() {
        return Get(uri);
    }
}
