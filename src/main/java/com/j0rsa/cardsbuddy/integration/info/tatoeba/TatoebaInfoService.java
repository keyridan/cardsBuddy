package com.j0rsa.cardsbuddy.integration.info.tatoeba;

import com.j0rsa.cardsbuddy.common.InfoProvider;
import com.j0rsa.cardsbuddy.common.InfoService;
import com.j0rsa.cardsbuddy.controller.model.TatoebaInfo;
import com.j0rsa.cardsbuddy.domain.model.Sentences;
import com.j0rsa.cardsbuddy.domain.service.SentencesService;
import com.j0rsa.cardsbuddy.integration.translation.model.Language;
import com.j0rsa.cardsbuddy.integration.translation.model.TranslationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TatoebaInfoService implements InfoService<TatoebaInfo> {
    private final SentencesService service;
    private final TatoebaInfoFactory tatoebaInfoFactory;

    @Autowired
    public TatoebaInfoService(SentencesService service, TatoebaInfoFactory tatoebaInfoFactory) {
        this.service = service;
        this.tatoebaInfoFactory = tatoebaInfoFactory;
    }

    @Override
    public TatoebaInfo search(TranslationRequest request) {
        List<Sentences> sentences = service.findSentences(
                request.getFromLanguage(),
                request.getToLanguage(),
                request.getWord(),
                PageRequest.of(0, 10));

        return tatoebaInfoFactory.create(sentences);
    }

    @Override
    public List<Language.Code> languages() {
        return service.languages();
    }

    @Override
    public InfoProvider.Code providerCode() {
        return InfoProvider.Code.TATOEBA;
    }
}
