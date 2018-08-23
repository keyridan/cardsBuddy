package com.j0rsa.cardsbuddy.integration.tinycards.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Concept {
    private Fact fact;
    private UUID id;

    public Concept(Fact fact) {
        this.fact = fact;
    }
}
