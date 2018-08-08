package com.j0rsa.cardsbuddy.controller.model.leo.flec.verb;


import com.j0rsa.cardsbuddy.common.Highlight;
import com.j0rsa.cardsbuddy.controller.model.leo.flec.FlecRecord;
import lombok.Builder;

import java.util.List;

public class VerbFlecRecord extends FlecRecord {

    @Builder
    private VerbFlecRecord(List<Highlight> highlights, String value) {
        super(highlights, value);
    }
}
