package com.j0rsa.cardsbuddy.controller;

import com.j0rsa.cardsbuddy.common.Info;
import com.j0rsa.cardsbuddy.integration.info.InfoSearcher;
import com.j0rsa.cardsbuddy.integration.translation.TranslationService;
import com.j0rsa.cardsbuddy.integration.translation.model.Language;
import com.j0rsa.cardsbuddy.integration.translation.model.Translation;
import com.j0rsa.cardsbuddy.integration.translation.model.TranslationRequest;
import io.vavr.Tuple2;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/translate/")
public class TranslationController {
    private InfoSearcher infoSearcher;

    public TranslationController(InfoSearcher infoSearcher) {
        this.infoSearcher = infoSearcher;
    }

    @PostMapping
    @ResponseBody
    public Translation translate(@RequestBody TranslationRequest request) {
        Translation translation = TranslationService.translate(request);
        Tuple2<List<String>, List<Info>> infos = infoSearcher.search(requestWithFromLanguageFromTranslationIfAuto(request, translation));
        translation.setErrorMessages(infos._1);
        translation.setInfos(infos._2);
        return translation;
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
