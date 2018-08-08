package com.j0rsa.cardsbuddy.converters.flec.noun;

import com.j0rsa.cardsbuddy.controller.model.leo.flec.noun.NounFlec;
import com.j0rsa.cardsbuddy.controller.model.leo.flec.noun.NounFlecTables;
import com.j0rsa.cardsbuddy.info.leo.model.flec.MoodType;
import com.j0rsa.cardsbuddy.info.leo.model.flec.noun.NountabType;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NounTabToNounFlecConverter implements Converter<NountabType, NounFlec> {
    private ConversionService conversionService;

    public NounTabToNounFlecConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public NounFlec convert(NountabType nountabType) {
        return NounFlec.builder()
                .flecs(convertMood(nountabType.getMood()))
                .build();
    }

    private List<NounFlecTables> convertMood(List<MoodType> moodTypes) {
        return moodTypes.stream()
                .map(mood -> conversionService.convert(mood, NounFlecTables.class))
                .collect(Collectors.toList());
    }
}
