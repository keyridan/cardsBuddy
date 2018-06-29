package com.j0rsa.cardsbuddy.translation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TranslationRequest {
    private String word;
    private Language.Code fromLanguage;
    private Language.Code toLanguage;
}
