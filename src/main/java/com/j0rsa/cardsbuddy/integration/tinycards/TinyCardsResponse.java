package com.j0rsa.cardsbuddy.integration.tinycards;

import com.j0rsa.cardsbuddy.integration.common.BaseResponse;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Response;

import java.util.Optional;

@Slf4j
class TinyCardsResponse extends BaseResponse<TinyCardsHttpResponse> {

    protected TinyCardsResponse(Response response) {
        super(response);
    }

    @Override
    protected TinyCardsHttpResponse createResponse(HttpResponse response) {
        return TinyCardsHttpResponse.create(response);
    }

    static TinyCardsResponse create(Response response) {
        return new TinyCardsResponse(response);
    }
}
