package com.j0rsa.cardsbuddy.controller.model.leo.flec;


import com.j0rsa.cardsbuddy.common.Highlight;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
public class FlecRecord {
    @Getter
    private List<Highlight> highlights;
    @Getter
    private String value;
}
