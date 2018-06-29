package com.j0rsa.cardsbuddy.tinycards.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.assertj.core.util.Lists;

import java.util.List;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Card {
    private UUID id;
    private List<Side> sides = Lists.newArrayList();

    public void addSides(Side... sides) {
        for (Side side : sides) {
            addSide(side);
        }
    }

    public void addSide(Side side) {
        this.sides.add(side);
    }
}
