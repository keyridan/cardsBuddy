package com.j0rsa.cardsbuddy.integration.images;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.j0rsa.cardsbuddy.integration.common.ResponseParser.parseTo;
import static com.j0rsa.cardsbuddy.integration.images.ImageRequest.get;
import static com.j0rsa.cardsbuddy.integration.images.ImageUriBuilder.uri;

@Service
public class ImageSearcher {
    private String imageApiKey;

    @Autowired
    public ImageSearcher(@Value("${api.imageKey}") final String imageApiKey) {
        this.imageApiKey = imageApiKey;
    }

    public Images requestImages(String query, int pageNumber) {
        return get(uri(imageApiKey)
                .page(pageNumber)
                .query(query))
                .execute()
                .flatMap(ImageResponse::returnResponse)
                .map(ImageHttpResponse::checkStatus)
                .flatMap(ImageHttpResponse::returnContent)
                .flatMap(parseTo(Images.class))
                .orElseGet(Images::new);
    }
}
