package com.j0rsa.cardsbuddy.converters.flec.verb;

import com.j0rsa.cardsbuddy.controller.model.leo.flec.verb.VerbFlecRecord;
import com.j0rsa.cardsbuddy.controller.model.leo.flec.verb.VerbFlecTable;
import com.j0rsa.cardsbuddy.info.leo.model.flec.CaseType;
import com.j0rsa.cardsbuddy.info.leo.model.flec.verb.TenseType;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TenseToVerbFlecTableConverter implements Converter<TenseType, VerbFlecTable> {
    private ConversionService conversionService;

    public TenseToVerbFlecTableConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public VerbFlecTable convert(TenseType tense) {
        return caseExist(tense.get_case())
                ? VerbFlecTable.builder()
                .title(tense.getTitle())
                .flecRows(convertRows(tense.get_case()))
                .build()
                : null;
    }

    private boolean caseExist(List<CaseType> _case) {
        return _case != null && !_case.isEmpty();
    }

    private List<VerbFlecRecord> convertRows(List<CaseType> cases) {
        return cases.stream()
                .map(aCase -> conversionService.convert(aCase, VerbFlecRecord.class))
                .collect(Collectors.toList());
    }
}
