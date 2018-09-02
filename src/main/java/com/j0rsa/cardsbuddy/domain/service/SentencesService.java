package com.j0rsa.cardsbuddy.domain.service;

import com.j0rsa.cardsbuddy.domain.model.QSentences;
import com.j0rsa.cardsbuddy.domain.model.Sentences;
import com.j0rsa.cardsbuddy.domain.model.Translation;
import com.j0rsa.cardsbuddy.domain.repository.SentencesRepository;
import com.j0rsa.cardsbuddy.integration.translation.model.Language;
import com.querydsl.core.types.Predicate;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.j0rsa.cardsbuddy.domain.model.Sentences.createFrom;

@Service
public class SentencesService {
    private final SentencesRepository repository;

    @Autowired
    public SentencesService(SentencesRepository repository) {
        this.repository = repository;
    }

    public Iterable<Sentences> findAll() {
        return repository.findAll();
    }

    public List<Sentences> findSentences(Language.Code fromLanguage, Language.Code toLanguage, String query, Pageable page) {
        QSentences qSentences = QSentences.sentences;
        Predicate predicate = qSentences.lang.eq(fromLanguage.getIso639_3Value())
                .and(qSentences.text.contains(query))
                .and(qSentences.translations.any().lang.eq(toLanguage.getIso639_3Value()));
        return repository.findAll(predicate, page)
                .stream()
                .map(sentences -> createFrom(filterOtherLanguagesTranslations(toLanguage, sentences), sentences))
                .collect(Collectors.toList());
    }

    private List<Translation> filterOtherLanguagesTranslations(Language.Code toLanguage, Sentences sentences) {
        return sentences.getTranslations().stream()
                .filter(translation -> toLanguage.getIso639_3Value().equals(translation.getLang()))
                .collect(Collectors.toList());
    }

    public void save(Sentences sentences) {
        repository.save(sentences);
    }

    public void saveIfSupportedLanguage(Sentences sentences) {
        if (iso639_3Languages().contains(sentences.getLang())) {
            repository.save(sentences);
        }
    }

    public void saveAll(List<Sentences> sentences) {
        repository.saveAll(sentences);
    }

    private List<String> iso639_3Languages() {
        return languages().stream().map(Language.Code::getIso639_3Value).collect(Collectors.toList());
    }

    public List<Language.Code> languages() {
        return Lists.newArrayList(
                Language.Code.EN,
                Language.Code.DE,
                Language.Code.FR,
                Language.Code.ES,
                Language.Code.IT,
                Language.Code.RU,
                Language.Code.PT,
                Language.Code.PL,
                Language.Code.UK,
                Language.Code.BG,
                Language.Code.CS,
                Language.Code.SR
        );
    }

    public Optional<Sentences> findById(Long id) {
        return repository.findById(id);
    }
}
