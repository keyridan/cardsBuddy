package com.j0rsa.cardsbuddy.tinycards.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Deck {
    private List<Card> cards = new ArrayList<>();
    private String name;
    @JsonProperty("private")
    private Boolean isPrivate;
    private Boolean shareable;

    public Deck addCard(Card card) {
        this.cards.add(card);
        return this;
    }

    public void addCards(Card... cards) {
        for (Card card : cards) {
            addCard(card);
        }
    }
}
