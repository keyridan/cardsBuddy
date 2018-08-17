package com.j0rsa.cardsbuddy.tinycards.model;

import lombok.Getter;
import org.assertj.core.util.Lists;

import java.util.Optional;

public class ImageType {
    public enum Code {
        IMAGE_BMP("image/bmp"),
        IMAGE_GIF("image/gif"),
        IMAGE_JPEG("image/jpeg"),
        IMAGE_PNG("image/png"),
        IMAGE_SVG("image/svg"),
        IMAGE_TIFF("image/tiff");

        @Getter
        private String value;

        Code(String value) {
            this.value = value;
        }

        public static Optional<ImageType.Code> of(String value) {
            return Lists.newArrayList(values())
                    .stream()
                    .filter(code -> code.value.equals(value))
                    .findFirst();
        }
    }
}
