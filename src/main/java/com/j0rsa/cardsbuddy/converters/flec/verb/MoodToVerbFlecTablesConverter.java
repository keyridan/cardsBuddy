package com.j0rsa.cardsbuddy.converters.flec.verb;

import com.j0rsa.cardsbuddy.controller.model.leo.flec.verb.VerbFlecTable;
import com.j0rsa.cardsbuddy.controller.model.leo.flec.verb.VerbFlecTables;
import com.j0rsa.cardsbuddy.integration.info.leo.model.flec.MoodType;
import com.j0rsa.cardsbuddy.integration.info.leo.model.flec.verb.TenseType;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class MoodToVerbFlecTablesConverter implements Converter<MoodType, VerbFlecTables> {
    private ConversionService conversionService;

    public MoodToVerbFlecTablesConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public VerbFlecTables convert(MoodType moodType) {
        return VerbFlecTables.builder()
                .title(moodType.getTitle())
                .tables(convertTables(moodType.getTense()))
                .build();
    }

    private List<VerbFlecTable> convertTables(List<TenseType> tenses) {
        return tenses.stream()
                .map(tense -> conversionService.convert(tense, VerbFlecTable.class))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
