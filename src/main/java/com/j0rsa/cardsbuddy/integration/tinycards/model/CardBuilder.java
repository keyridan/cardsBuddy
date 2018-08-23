package com.j0rsa.cardsbuddy.integration.tinycards.model;

public class CardBuilder {
    private Side frontSide = new Side();
    private Side backSide = new Side();

    public static CardBuilder aCard() {
        return new CardBuilder();
    }

    public CardBuilder frontSideFact(String text) {
        Concept concept = createConcept(text);
        this.frontSide.addConcept(concept);
        return this;
    }

    public CardBuilder backSideFact(String text) {
        Concept concept = createConcept(text);
        this.backSide.addConcept(concept);
        return this;
    }

    public CardBuilder backSideImageFact(Details details) {
        Concept concept = createConceptWithImage(details);
        this.backSide.addConcept(concept);
        return this;
    }

    public Card build() {
        Card card = new Card();
        card.addSides(frontSide, backSide);
        return card;
    }

    private Concept createConcept(String text) {
        Fact fact = new TextFact(text);
        return new Concept(fact);
    }

    private Concept createConceptWithImage(Details details) {
        Fact fact = ImageFact.builder()
                .details(details)
                .build();
        return new Concept(fact);
    }
}
