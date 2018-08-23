package com.j0rsa.cardsbuddy.integration.translation.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TranslationPair {
    private String wordFrom;
    private String wordTo;
}
