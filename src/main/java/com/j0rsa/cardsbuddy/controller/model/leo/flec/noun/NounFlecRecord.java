package com.j0rsa.cardsbuddy.controller.model.leo.flec.noun;

import com.j0rsa.cardsbuddy.controller.model.leo.flec.FlecRecord;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class NounFlecRecord extends FlecRecord {
    @Getter
    private String article;
    @Getter
    private String caseValue;

    @Builder
    private NounFlecRecord(List<String> highlights, String ending, String article, String caseValue) {
        super(highlights, ending);
        this.article = article;
        this.caseValue = caseValue;
    }
}
