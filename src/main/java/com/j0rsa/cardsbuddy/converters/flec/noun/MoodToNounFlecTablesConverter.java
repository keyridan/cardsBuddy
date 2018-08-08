package com.j0rsa.cardsbuddy.converters.flec.noun;

import com.j0rsa.cardsbuddy.controller.model.leo.flec.noun.NounFlecTable;
import com.j0rsa.cardsbuddy.controller.model.leo.flec.noun.NounFlecTables;
import com.j0rsa.cardsbuddy.info.leo.model.flec.MoodType;
import com.j0rsa.cardsbuddy.info.leo.model.flec.noun.VariantType;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class MoodToNounFlecTablesConverter implements Converter<MoodType, NounFlecTables> {
    private ConversionService conversionService;

    public MoodToNounFlecTablesConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public NounFlecTables convert(MoodType moodType) {
        return NounFlecTables.builder()
                .title(moodType.getTitle())
                .tables(convertTables(moodType.getVariant()))
                .build();
    }

    private List<NounFlecTable> convertTables(List<VariantType> variant) {
        return variant.stream()
                .filter(variantItem -> !variantItem.getTitle().isEmpty())
                .map(variantItem -> conversionService.convert(variantItem, NounFlecTable.class))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
