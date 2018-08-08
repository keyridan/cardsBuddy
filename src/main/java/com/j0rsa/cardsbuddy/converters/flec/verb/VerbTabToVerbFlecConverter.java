package com.j0rsa.cardsbuddy.converters.flec.verb;

import com.j0rsa.cardsbuddy.controller.model.leo.flec.verb.VerbFlec;
import com.j0rsa.cardsbuddy.controller.model.leo.flec.verb.VerbFlecTables;
import com.j0rsa.cardsbuddy.info.leo.model.flec.MoodType;
import com.j0rsa.cardsbuddy.info.leo.model.flec.verb.VerbtabType;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VerbTabToVerbFlecConverter implements Converter<VerbtabType, VerbFlec> {
    private ConversionService conversionService;

    public VerbTabToVerbFlecConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public VerbFlec convert(VerbtabType verbtabType) {
        return VerbFlec.builder()
                .flecs(convertTables(verbtabType.getMood()))
                .build();
    }

    private List<VerbFlecTables> convertTables(List<MoodType> moods) {
        return moods.stream()
                .map(moodType -> conversionService.convert(moodType, VerbFlecTables.class))
                .collect(Collectors.toList());
    }
}
