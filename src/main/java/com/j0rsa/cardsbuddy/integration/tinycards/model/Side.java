package com.j0rsa.cardsbuddy.integration.tinycards.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.assertj.core.util.Lists;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Side {
    private List<Concept> concepts = Lists.newArrayList();

    public Side addConcept(Concept concept) {
        this.concepts.add(concept);
        return this;
    }
}
