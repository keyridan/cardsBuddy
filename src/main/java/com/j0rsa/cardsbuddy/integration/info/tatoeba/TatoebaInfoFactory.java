package com.j0rsa.cardsbuddy.integration.info.tatoeba;

import com.j0rsa.cardsbuddy.common.InfoTable;
import com.j0rsa.cardsbuddy.common.SmallTitledRows;
import com.j0rsa.cardsbuddy.common.Title;
import com.j0rsa.cardsbuddy.controller.model.TatoebaInfo;
import com.j0rsa.cardsbuddy.domain.model.Sentences;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TatoebaInfoFactory {
    private final ConversionService conversionService;

    @Autowired
    public TatoebaInfoFactory(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    public TatoebaInfo create(List<Sentences> sentences) {
        return TatoebaInfo.builder()
                .table(infoTableFrom(sentences))
                .build();
    }

    private InfoTable infoTableFrom(List<Sentences> sentences) {
        return new InfoTable(Lists.newArrayList(titleFrom(sentences)));
    }

    private Title titleFrom(List<Sentences> sentences) {
        return Title.builder()
                .value("Sentences")
                .rows(rowsFrom(sentences))
                .build();
    }

    private List<SmallTitledRows> rowsFrom(List<Sentences> sentences) {
        return sentences.stream()
                .map(sentence -> conversionService.convert(sentence, SmallTitledRows.class))
                .collect(Collectors.toList());
    }
}
