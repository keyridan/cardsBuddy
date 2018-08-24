package com.j0rsa.cardsbuddy.integration.tinycards;

import com.j0rsa.cardsbuddy.integration.common.BaseHttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;

@Slf4j
class TinyCardsHttpResponse extends BaseHttpResponse<TinyCardsHttpResponse> {

    protected TinyCardsHttpResponse(HttpResponse httpResponse) {
        super(httpResponse);
    }

    static TinyCardsHttpResponse create(HttpResponse httpResponse) {
        return new TinyCardsHttpResponse(httpResponse);
    }
}
