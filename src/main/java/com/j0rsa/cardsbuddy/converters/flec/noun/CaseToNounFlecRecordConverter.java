package com.j0rsa.cardsbuddy.converters.flec.noun;

import com.j0rsa.cardsbuddy.controller.model.leo.flec.noun.NounFlecRecord;
import com.j0rsa.cardsbuddy.info.leo.model.flec.CaseType;
import com.j0rsa.cardsbuddy.info.leo.model.flec.noun.NounType;
import org.assertj.core.util.Lists;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CaseToNounFlecRecordConverter implements Converter<CaseType, NounFlecRecord> {
    @Override
    public NounFlecRecord convert(CaseType caseType) {
        String radical = caseType.getRadical();
        return NounFlecRecord.builder()
                .caseValue(caseType.getCn())
                .highlights(highLights(caseType.getNoun()))
                .value(value(caseType.getNoun(), radical))
                .build();
    }

    private String value(NounType noun, String radical) {
        return Lists.newArrayList(
                getValueOrEmpty(noun.getArt())
                , getWord(noun, radical)
        ).stream()
                .filter(value -> !value.isEmpty())
                .collect(Collectors.joining(" "));
    }

    private String getWord(NounType noun, String radical) {
        return String.format("%s%s"
                , getValueOrEmpty(radical)
                , getValueOrEmpty(noun.getEnding())
        );
    }

    private String getValueOrEmpty(String value) {
        return value != null
                ? value
                : "";
    }

    private List<String> highLights(NounType noun) {
        return notEmpty(noun)
                ? Lists.newArrayList(noun.getEnding())
                : Lists.newArrayList();
    }

    private boolean notEmpty(NounType noun) {
        return noun.getEnding() != null && !noun.getEnding().isEmpty();
    }
}
