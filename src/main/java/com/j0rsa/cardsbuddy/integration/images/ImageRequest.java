package com.j0rsa.cardsbuddy.integration.images;

import com.j0rsa.cardsbuddy.integration.common.BaseRequest;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;

public class ImageRequest extends BaseRequest<ImageRequest, ImageResponse> {
    private static final String URI = "https://pixabay.com/api/";
    protected ImageRequest(Request request) {
        super(request);
    }

    @Override
    protected ImageResponse createResponse(Response response) {
        return ImageResponse.create(response);
    }

    static ImageRequest get(ImageUriBuilder builder) {
        return new ImageRequest(get(URI, builder.build()));
    }

}
