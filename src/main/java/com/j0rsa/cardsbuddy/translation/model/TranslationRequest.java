package com.j0rsa.cardsbuddy.translation.model;

import com.j0rsa.cardsbuddy.common.InfoProvider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TranslationRequest {
    private String word;
    private Language.Code fromLanguage;
    private Language.Code toLanguage;
    private List<InfoProvider.Code> infoTypes;
}
