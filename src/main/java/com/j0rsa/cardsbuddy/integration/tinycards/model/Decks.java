package com.j0rsa.cardsbuddy.integration.tinycards.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Decks {
    private List<DeckInfo> decks;
}
