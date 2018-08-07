package com.j0rsa.cardsbuddy.controller.model.leo.flec;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
public class FlecTable<T extends FlecRecord> {
    @Getter
    private String title;
    @Getter
    private List<T> flecRows;
}
