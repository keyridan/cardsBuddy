package com.j0rsa.cardsbuddy.domain.service;

import com.j0rsa.cardsbuddy.domain.model.Sentences;
import com.j0rsa.cardsbuddy.domain.model.Translation;
import com.j0rsa.cardsbuddy.integration.translation.model.Language;
import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.j0rsa.cardsbuddy.DefaultData.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SentencesServiceTest {
    @Autowired
    private SentencesService sentencesService;
    @Autowired
    private MongoOperations mongoOps;

    @After
    public void cleanAll() {
        mongoOps.dropCollection(Sentences.class);
    }

    @Test
    public void whenSupportedLanguageThenSaved() {
        Sentences sentences = aSentenceWithSupportedLanguage().build();

        sentencesService.saveIfSupportedLanguage(sentences);

        Iterable<Sentences> foundSentences = sentencesService.findAll();
        assertThat(foundSentences).containsOnly(sentences);
    }

    @Test
    public void whenLanguageIsNotSupportedThenEmpty() {
        Sentences sentences = aSentenceWithSupportedLanguage()
                .lang(Language.Code.AUTO.getIso639_3Value()).build();

        sentencesService.saveIfSupportedLanguage(sentences);

        Iterable<Sentences> foundSentences = sentencesService.findAll();
        assertThat(foundSentences).isEmpty();
    }

    @Test
    public void whenSentenceExistThenContainsOnlyToLanguageTranslations() {
        Sentences enSentence = aSentenceWithText("She passed the examination.")
                .lang(Language.Code.EN.getIso639_3Value()).build();
        Sentences ruSentence = aSentenceWithText("Она сдала экзамен.")
                .lang(Language.Code.RU.getIso639_3Value()).build();
        sentencesService.saveAll(Lists.newArrayList(enSentence, ruSentence));
        Sentences deSentence = aSentenceWithText("Sie bestand die Prüfung.")
                .lang(Language.Code.DE.getIso639_3Value()).build();
        deSentence.addTranslationIfNotExist(enSentence);
        deSentence.addTranslationIfNotExist(ruSentence);
        sentencesService.save(deSentence);

        List<Sentences> foundSentences = sentencesService.findSentences(Language.Code.DE, Language.Code.EN, "die", defaultPage());
        assertThat(foundSentences).extracting(Sentences::getId).containsOnly(deSentence.getId());
        assertThat(foundSentences)
                .flatExtracting(Sentences::getTranslations)
                .extracting(Translation::getSentences)
                .containsOnly(enSentence);
    }

    @Test
    public void whenSentenceExistButAnotherPageThenEmpty() {
        Sentences enSentence = aSentenceWithText("She passed the examination.")
                .lang(Language.Code.EN.getIso639_3Value()).build();
        Sentences ruSentence = aSentenceWithText("Она сдала экзамен.")
                .lang(Language.Code.RU.getIso639_3Value()).build();
        sentencesService.saveAll(Lists.newArrayList(enSentence, ruSentence));
        Sentences deSentence = aSentenceWithText("Sie bestand die Prüfung.")
                .lang(Language.Code.DE.getIso639_3Value()).build();
        deSentence.addTranslationIfNotExist(enSentence);
        deSentence.addTranslationIfNotExist(ruSentence);
        sentencesService.save(deSentence);

        List<Sentences> foundSentences = sentencesService.findSentences(Language.Code.DE, Language.Code.EN, "die", defaultPage(1));
        assertThat(foundSentences).isEmpty();
    }

    @Test
    public void testFindWhenSentenceNotExist() {
        Sentences englishSentence = aSentenceWithText("She passed the examination.")
                .lang(Language.Code.EN.getIso639_3Value()).build();

        sentencesService.save(englishSentence);

        List<Sentences> foundSentences = sentencesService.findSentences(Language.Code.DE, Language.Code.EN, "die", defaultPage());
        assertThat(foundSentences).isEmpty();
    }
}