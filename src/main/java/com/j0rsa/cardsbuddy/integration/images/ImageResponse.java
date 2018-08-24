package com.j0rsa.cardsbuddy.integration.images;

import com.j0rsa.cardsbuddy.integration.common.BaseResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Response;

public class ImageResponse extends BaseResponse<ImageHttpResponse> {
    protected ImageResponse(Response response) {
        super(response);
    }

    public static ImageResponse create(Response response) {
        return new ImageResponse(response);
    }

    @Override
    protected ImageHttpResponse createResponse(HttpResponse response) {
        return ImageHttpResponse.create(response);
    }
}
