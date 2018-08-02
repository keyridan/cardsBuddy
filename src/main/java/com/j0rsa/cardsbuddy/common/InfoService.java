package com.j0rsa.cardsbuddy.common;

import com.j0rsa.cardsbuddy.translation.model.Language;
import com.j0rsa.cardsbuddy.translation.model.TranslationRequest;

import java.util.List;

public interface InfoService<T extends Info> {
    T search(TranslationRequest request);

    List<Language.Code> languages();
}
