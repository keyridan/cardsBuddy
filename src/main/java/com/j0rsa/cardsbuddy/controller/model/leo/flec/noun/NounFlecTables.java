package com.j0rsa.cardsbuddy.controller.model.leo.flec.noun;


import com.j0rsa.cardsbuddy.controller.model.leo.flec.FlecTables;
import lombok.Builder;

import java.util.List;

public class NounFlecTables extends FlecTables<NounFlecTable> {
    @Builder
    private NounFlecTables(String title, List<NounFlecTable> tables) {
        super(title, tables);
    }
}
