package com.j0rsa.cardsbuddy.converters.flec.noun;

import com.j0rsa.cardsbuddy.controller.model.leo.flec.noun.NounFlecRecord;
import com.j0rsa.cardsbuddy.controller.model.leo.flec.noun.NounFlecTable;
import com.j0rsa.cardsbuddy.integration.info.leo.model.flec.CaseType;
import com.j0rsa.cardsbuddy.integration.info.leo.model.flec.noun.VariantType;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class VariantToNounFlecTableConverter implements Converter<VariantType, NounFlecTable> {
    private ConversionService conversionService;

    public VariantToNounFlecTableConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public NounFlecTable convert(VariantType variantType) {
        return variantType.caseExist()
                ? NounFlecTable.builder()
                .title(variantType.getTitle())
                .flecRows(convertRows(variantType.getCase()))
                .build()
                : null;
    }

    private List<NounFlecRecord> convertRows(List<CaseType> aCase) {
        return aCase.stream()
                .map(caseType -> conversionService.convert(caseType, NounFlecRecord.class))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
