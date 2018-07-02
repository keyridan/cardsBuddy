package com.j0rsa.cardsbuddy.controller;

import com.j0rsa.cardsbuddy.tinycards.TinyCardsService;
import com.j0rsa.cardsbuddy.tinycards.model.DeckInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/me/decks")
public class DecksController {
    private final TinyCardsService tinyCardsService;

    @Autowired
    public DecksController(TinyCardsService tinyCardsService) {
        this.tinyCardsService = tinyCardsService;
    }

    @GetMapping
    @ResponseBody
    public List<DeckInfo> deckInfo() {
        return tinyCardsService.requestDeckInfo();
    }
}
