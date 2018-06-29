package com.j0rsa.cardsbuddy.controller;

import com.j0rsa.cardsbuddy.translation.TranslationService;
import com.j0rsa.cardsbuddy.translation.model.Translation;
import com.j0rsa.cardsbuddy.translation.model.TranslationRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/me/cards")
public class CardsController {

    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.CREATED)
    public Translation translate(@RequestBody TranslationRequest request) {
        return TranslationService.translate(request);
    }

}
