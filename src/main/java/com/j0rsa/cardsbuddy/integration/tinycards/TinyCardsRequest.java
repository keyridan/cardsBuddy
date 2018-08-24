package com.j0rsa.cardsbuddy.integration.tinycards;

import com.j0rsa.cardsbuddy.integration.common.BaseRequest;
import com.j0rsa.cardsbuddy.integration.tinycards.model.Details;
import com.j0rsa.cardsbuddy.integration.tinycards.model.ImageType;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;

import java.util.HashMap;
import java.util.Map;

import static com.j0rsa.cardsbuddy.SystemConstants.TINY_CARDS_PREFIX;

@Slf4j
class TinyCardsRequest extends BaseRequest<TinyCardsRequest, TinyCardsResponse> {
    private static final String URI = "https://tinycards.duolingo.com/api/1/";
    private static final Map<ImageType.Code, ContentType> IMAGE_TYPE = new HashMap<>();

    static {
        IMAGE_TYPE.put(ImageType.Code.IMAGE_GIF, ContentType.IMAGE_GIF);
        IMAGE_TYPE.put(ImageType.Code.IMAGE_BMP, ContentType.IMAGE_BMP);
        IMAGE_TYPE.put(ImageType.Code.IMAGE_JPEG, ContentType.IMAGE_JPEG);
        IMAGE_TYPE.put(ImageType.Code.IMAGE_PNG, ContentType.IMAGE_PNG);
        IMAGE_TYPE.put(ImageType.Code.IMAGE_SVG, ContentType.IMAGE_SVG);
        IMAGE_TYPE.put(ImageType.Code.IMAGE_TIFF, ContentType.IMAGE_TIFF);
    }

    private TinyCardsRequest(Request request) {
        super(request);
    }

    static TinyCardsRequest post(String uri) {
        return new TinyCardsRequest(post(URI, uri));
    }

    static TinyCardsRequest get(String uri) {
        return new TinyCardsRequest(get(URI, uri));
    }

    static TinyCardsRequest patch(String uri) {
        return new TinyCardsRequest(patch(URI, uri));
    }

    @Override
    protected TinyCardsResponse createResponse(Response response) {
        return TinyCardsResponse.create(response);
    }

    TinyCardsRequest addCookiesHeaders(Map<String, String> headers) {
        return addCookiesHeaders(headers, TINY_CARDS_PREFIX);
    }

    TinyCardsRequest bodyOf(Details details) {
        return bodyOf(details.getData(), IMAGE_TYPE.get(details.getImageType()));
    }
}
