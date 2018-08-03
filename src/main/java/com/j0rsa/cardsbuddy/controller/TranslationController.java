package com.j0rsa.cardsbuddy.controller;

import com.j0rsa.cardsbuddy.common.Info;
import com.j0rsa.cardsbuddy.common.InfoServiceResolver;
import com.j0rsa.cardsbuddy.translation.TranslationService;
import com.j0rsa.cardsbuddy.translation.model.Language;
import com.j0rsa.cardsbuddy.translation.model.Translation;
import com.j0rsa.cardsbuddy.translation.model.TranslationRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/translate/")
public class TranslationController {
    private InfoServiceResolver infoServiceResolver;

    public TranslationController(InfoServiceResolver infoServiceResolver) {
        this.infoServiceResolver = infoServiceResolver;
    }

    @PostMapping
    @ResponseBody
    public Translation translate(@RequestBody TranslationRequest request) {
        Translation translation = TranslationService.translate(request);
        List<Info> infos = getInfosFor(requestWithFromLanguageFromTranslationIfAuto(request, translation));
        translation.setInfos(infos);
        return translation;
    }

    private List<Info> getInfosFor(@RequestBody TranslationRequest request) {
        return infoServiceResolver.resolve(request.getFromLanguage(), request.getInfoTypes())
                .stream()
                .map(infoService -> infoService.search(request))
                .collect(Collectors.toList());
    }

    private TranslationRequest requestWithFromLanguageFromTranslationIfAuto(TranslationRequest request, Translation translation) {
        return Language.Code.AUTO.equals(request.getFromLanguage())
                ? TranslationRequest.builder()
                .fromLanguage(translation.getLanguageFrom())
                .toLanguage(request.getToLanguage())
                .word(request.getWord())
                .build()
                : request;
    }

}
