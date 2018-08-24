package com.j0rsa.cardsbuddy.integration.images;

import org.junit.Test;

import static com.j0rsa.cardsbuddy.DefaultData.testImageApiKey;
import static org.assertj.core.api.Assertions.assertThat;

public class ImageSearcherTest {
    private ImageSearcher imageService;

    public ImageSearcherTest() {
        imageService = new ImageSearcher(testImageApiKey());
    }

    @Test
    public void requestImages() {
        Images result = imageService.requestImages("yellow flowers", 1);

        assertThat(result.getTotalHits()).isGreaterThan(0);
        assertThat(result.getHits()).hasSize(20);
        assertThat(result.getHits()).extracting(Image::getWebformatURL).isNotNull();
    }

    @Test
    public void requestImagesWhenEmpty() {
        Images result = imageService.requestImages("canFoundNothingForThis", 1);

        assertThat(result.getTotalHits()).isEqualTo(0);
        assertThat(result.getHits()).hasSize(0);
    }
}