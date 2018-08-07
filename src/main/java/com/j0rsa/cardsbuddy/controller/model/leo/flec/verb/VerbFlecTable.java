package com.j0rsa.cardsbuddy.controller.model.leo.flec.verb;


import com.j0rsa.cardsbuddy.controller.model.leo.flec.FlecTable;
import lombok.Builder;

import java.util.List;

public class VerbFlecTable extends FlecTable<VerbFlecRecord> {

    @Builder
    private VerbFlecTable(String title, List<VerbFlecRecord> flecRows) {
        super(title, flecRows);
    }
}
