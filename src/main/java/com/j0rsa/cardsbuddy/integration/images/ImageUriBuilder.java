package com.j0rsa.cardsbuddy.integration.images;

import static com.j0rsa.cardsbuddy.common.UriUtils.encodeUrl;

public class ImageUriBuilder {
    private static final String KEY_TEMPLATE = "?key=%s";
    private static final String PARAMETER_TEMPLATE = "%s&%s=%s";
    private String uri;

    private ImageUriBuilder(String key) {
        this.uri = key;
    }

    static ImageUriBuilder uri(String key) {
        return new ImageUriBuilder(String.format(KEY_TEMPLATE, key));
    }

    ImageUriBuilder query(String q) {
        String parameter = encodeUrl(q);
        this.uri = String.format(PARAMETER_TEMPLATE, uri, "q", parameter);
        return this;
    }

    ImageUriBuilder page(Integer page) {
        this.uri = String.format(PARAMETER_TEMPLATE, uri, "page", page);
        return this;
    }

    String build() {
        return uri;
    }
}
