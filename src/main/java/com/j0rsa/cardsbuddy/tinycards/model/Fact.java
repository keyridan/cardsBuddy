package com.j0rsa.cardsbuddy.tinycards.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = TextFact.class, name = "TEXT"),
        @JsonSubTypes.Type(value = ImageFact.class, name = "IMAGE"),
})
public abstract class Fact {
    private String id;
}
