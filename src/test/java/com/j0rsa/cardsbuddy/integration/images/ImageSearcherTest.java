package com.j0rsa.cardsbuddy.integration.images;

import org.junit.Test;

import java.util.List;

import static com.j0rsa.cardsbuddy.DefaultData.testImageApiKey;
import static org.assertj.core.api.Assertions.assertThat;

public class ImageSearcherTest {
    private ImageSearcher imageService;

    public ImageSearcherTest() {
        imageService = new ImageSearcher(testImageApiKey());
    }

    @Test
    public void requestImages() {
        List<Image> result = imageService.requestImages("yellow flowers");

        assertThat(result).hasSize(20);
        assertThat(result).extracting(Image::getWebformatURL).isNotNull();
    }
}