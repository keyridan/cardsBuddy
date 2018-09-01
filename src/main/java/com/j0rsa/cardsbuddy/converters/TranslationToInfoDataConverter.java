package com.j0rsa.cardsbuddy.converters;

import com.j0rsa.cardsbuddy.common.InfoData;
import com.j0rsa.cardsbuddy.domain.model.Translation;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TranslationToInfoDataConverter implements Converter<Translation, InfoData> {
    @Override
    public InfoData convert(Translation translation) {
        return InfoData.builder()
                .value(translation.getSentences().getText())
                .build();
    }
}
