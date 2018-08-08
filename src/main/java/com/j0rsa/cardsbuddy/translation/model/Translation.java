package com.j0rsa.cardsbuddy.translation.model;

import com.j0rsa.cardsbuddy.common.Info;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Translation extends Response {
    private TranslationPair pair;
    private Language.Code languageFrom;
    private List<TranslatedWord> translatedWords;
    private List<Info> infos;
}
