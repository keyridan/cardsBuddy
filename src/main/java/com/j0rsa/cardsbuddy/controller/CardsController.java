package com.j0rsa.cardsbuddy.controller;

import com.j0rsa.cardsbuddy.integration.tinycards.TinyCardsService;
import com.j0rsa.cardsbuddy.integration.tinycards.model.Card;
import com.j0rsa.cardsbuddy.integration.translation.TranslationService;
import com.j0rsa.cardsbuddy.integration.translation.model.Translation;
import com.j0rsa.cardsbuddy.integration.translation.model.TranslationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/me/cards")
public class CardsController {
    private final TinyCardsService tinyCardsService;

    @Autowired
    public CardsController(TinyCardsService tinyCardsService) {
        this.tinyCardsService = tinyCardsService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean add(@RequestBody Card card, @RequestParam UUID deckId) {
        return tinyCardsService.add(deckId, card).isPresent();
    }

}
