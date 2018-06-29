package com.j0rsa.cardsbuddy.tinycards.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeckInfo {
    private UUID id;
    private Integer cardCount;
    private String compactId;
    private String coverImageUrl;
    private String fromLanguage;
    private String name;
    private String description;
    private Boolean isPrivate;
    private Boolean shareable;
}
