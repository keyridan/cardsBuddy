package com.j0rsa.cardsbuddy.integration.tinycards.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TextFact extends Fact {
    private String text;
    private String ttsUrl;

    public TextFact(String text) {
        this.text = text;
    }
}
