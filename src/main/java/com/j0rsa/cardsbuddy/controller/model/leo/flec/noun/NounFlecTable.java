package com.j0rsa.cardsbuddy.controller.model.leo.flec.noun;


import com.j0rsa.cardsbuddy.controller.model.leo.flec.FlecTable;
import lombok.Builder;

import java.util.List;

public class NounFlecTable extends FlecTable<NounFlecRecord> {

    @Builder
    private NounFlecTable(String title, List<NounFlecRecord> flecRows) {
        super(title, flecRows);
    }
}
