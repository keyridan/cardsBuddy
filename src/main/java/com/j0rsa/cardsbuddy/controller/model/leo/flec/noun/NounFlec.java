package com.j0rsa.cardsbuddy.controller.model.leo.flec.noun;

import com.j0rsa.cardsbuddy.controller.model.leo.flec.Flec;
import lombok.Builder;

import java.util.List;

public class NounFlec extends Flec<NounFlecTables> {
    @Builder
    public NounFlec(List<NounFlecTables> flecs) {
        super(flecs);
    }
}
