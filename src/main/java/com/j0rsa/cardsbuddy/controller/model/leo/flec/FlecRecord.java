package com.j0rsa.cardsbuddy.controller.model.leo.flec;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
public class FlecRecord {
    @Getter
    private List<String> highlights;
    @Getter
    private String value;
}
