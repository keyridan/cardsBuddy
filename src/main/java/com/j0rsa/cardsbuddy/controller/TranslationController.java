package com.j0rsa.cardsbuddy.controller;

import com.j0rsa.cardsbuddy.translation.TranslationService;
import com.j0rsa.cardsbuddy.translation.model.Translation;
import com.j0rsa.cardsbuddy.translation.model.TranslationRequest;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/translate/")
public class TranslationController {

    @PostMapping
    @ResponseBody
    public Translation translate(@RequestBody TranslationRequest request) {
        return TranslationService.translate(request);
    }

}
