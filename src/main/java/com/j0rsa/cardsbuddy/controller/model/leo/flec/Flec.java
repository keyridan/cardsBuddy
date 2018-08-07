package com.j0rsa.cardsbuddy.controller.model.leo.flec;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
public abstract class Flec<T extends FlecTables> {
    @Getter
    private List<T> flecs;
}
