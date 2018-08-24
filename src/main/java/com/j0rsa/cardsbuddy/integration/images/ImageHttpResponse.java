package com.j0rsa.cardsbuddy.integration.images;

import com.j0rsa.cardsbuddy.integration.common.BaseHttpResponse;
import org.apache.http.HttpResponse;

public class ImageHttpResponse extends BaseHttpResponse<ImageHttpResponse> {
    protected ImageHttpResponse(HttpResponse httpResponse) {
        super(httpResponse);
    }

    public static ImageHttpResponse create(HttpResponse response) {
        return new ImageHttpResponse(response);
    }
}
