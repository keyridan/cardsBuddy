package com.j0rsa.cardsbuddy.translation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class TranslatedWord {
    private String word;
    private String article;
}
