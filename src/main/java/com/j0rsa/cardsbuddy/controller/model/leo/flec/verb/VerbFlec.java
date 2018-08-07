package com.j0rsa.cardsbuddy.controller.model.leo.flec.verb;

import com.j0rsa.cardsbuddy.controller.model.leo.flec.Flec;
import lombok.Builder;

import java.util.List;

public class VerbFlec extends Flec<VerbFlecTables> {

    @Builder
    public VerbFlec(List<VerbFlecTables> flecs) {
        super(flecs);
    }
}
