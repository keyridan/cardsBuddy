package com.j0rsa.cardsbuddy.controller;

import com.j0rsa.cardsbuddy.tinycards.TinyCardsService;
import com.j0rsa.cardsbuddy.tinycards.model.DeckInfo;
import com.j0rsa.cardsbuddy.tinycards.model.LoginRequest;
import com.j0rsa.cardsbuddy.tinycards.model.TinyCardsLoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


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
    public List<DeckInfo> translate(@RequestBody LoginRequest request) {
        Optional<String> tinyCardsId = tinyCardsService.login(request).map(TinyCardsLoginResponse::getId);
        return tinyCardsService.requestDeckInfo(tinyCardsId.get());
    }
}
