package com.j0rsa.cardsbuddy.controller.model.leo.flec.noun;

import com.j0rsa.cardsbuddy.common.Highlight;
import com.j0rsa.cardsbuddy.controller.model.leo.flec.FlecRecord;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class NounFlecRecord extends FlecRecord {
    @Getter
    private String caseValue;

    @Builder
    private NounFlecRecord(List<Highlight> highlights, String value, String caseValue) {
        super(highlights, value);
        this.caseValue = caseValue;
    }
}
