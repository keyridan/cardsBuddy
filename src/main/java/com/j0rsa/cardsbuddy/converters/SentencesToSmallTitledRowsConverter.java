package com.j0rsa.cardsbuddy.converters;

import com.j0rsa.cardsbuddy.common.InfoData;
import com.j0rsa.cardsbuddy.common.SmallTitledRows;
import com.j0rsa.cardsbuddy.domain.model.Sentences;
import com.j0rsa.cardsbuddy.domain.model.Translation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SentencesToSmallTitledRowsConverter implements Converter<Sentences, SmallTitledRows> {
    private final ConversionService conversionService;

    @Autowired
    public SentencesToSmallTitledRowsConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public SmallTitledRows convert(Sentences sentences) {
        return SmallTitledRows.builder()
                .value(sentences.getText())
                .infoData(infoData(sentences.getTranslations()))
                .build();
    }

    private List<InfoData> infoData(List<Translation> translations) {
        return translations.stream()
                .map(translation -> conversionService.convert(translation, InfoData.class))
                .collect(Collectors.toList());
    }
}
