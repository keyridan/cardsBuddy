package com.j0rsa.cardsbuddy.controller.model.leo.flec.verb;


import com.j0rsa.cardsbuddy.controller.model.leo.flec.FlecTables;
import lombok.Builder;

import java.util.List;

public class VerbFlecTables extends FlecTables<VerbFlecTable> {

    @Builder
    private VerbFlecTables(String title, List<VerbFlecTable> tables) {
        super(title, tables);
    }
}
