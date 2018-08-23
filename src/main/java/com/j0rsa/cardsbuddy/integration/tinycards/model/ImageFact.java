package com.j0rsa.cardsbuddy.integration.tinycards.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImageFact extends Fact {
    private String imageUrl;
    private String text;
    private Details details;
}
