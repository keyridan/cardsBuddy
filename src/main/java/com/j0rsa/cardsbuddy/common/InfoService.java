package com.j0rsa.cardsbuddy.common;

import com.j0rsa.cardsbuddy.integration.translation.model.TranslationRequest;

public interface InfoService<T extends Info> {
    T search(TranslationRequest request);

    boolean canBeProvided(TranslationRequest request);

    InfoProvider.Code providerCode();
}
