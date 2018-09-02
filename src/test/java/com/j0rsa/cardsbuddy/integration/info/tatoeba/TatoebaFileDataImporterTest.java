package com.j0rsa.cardsbuddy.integration.info.tatoeba;

import com.j0rsa.cardsbuddy.DefaultData;
import com.j0rsa.cardsbuddy.domain.model.Sentences;
import com.j0rsa.cardsbuddy.domain.model.Translation;
import com.j0rsa.cardsbuddy.domain.service.SentencesService;
import com.j0rsa.cardsbuddy.integration.translation.model.Language;
import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TatoebaFileDataImporterTest {
    @Autowired
    private TatoebaFileDataImporter fileDataImporter;
    @Autowired
    private SentencesService sentencesService;
    @Autowired
    private MongoOperations mongoOps;

    @After
    public void cleanAll() {
        mongoOps.dropCollection(Sentences.class);
    }

    @Test
    public void importSentencesData() throws Exception {
        File file = getFile("tatoeba/sentences.tar.bz2");
        fileDataImporter.importSentencesData(file, fileId());

        Iterable<Sentences> foundRecords = sentencesService.findAll();
        assertThat(foundRecords).hasSize(9);
    }

    @Test
    public void importLinksData() throws Exception {
        Sentences deSentence = DefaultData.aSentence()
                .id(2L)
                .lang(Language.Code.DE.getIso639_3Value())
                .text("Sie bestand die Prüfung.")
                .build();
        Sentences enSentence = DefaultData.aSentence()
                .id(4L)
                .lang(Language.Code.EN.getIso639_3Value())
                .text("She passed the examination.")
                .build();
        sentencesService.saveAll(Lists.newArrayList(deSentence, enSentence));
        File file = getFile("tatoeba/links.tar.bz2");
        fileDataImporter.importLinksData(file, fileId());

        Iterable<Sentences> foundRecords = sentencesService.findAll();
        assertThat(foundRecords).hasSize(2);
        Optional<Sentences> foundSentences = sentencesService.findById(2L);
        assertThat(foundSentences.get().getTranslations()).extracting(Translation::getLang).containsExactly(Language.Code.EN.getIso639_3Value());
        assertThat(foundSentences.get().getTranslations()).extracting(Translation::getSentences).containsExactly(enSentence);
    }

    private UUID fileId() {
        return UUID.randomUUID();
    }

    @Test
    public void importLinksDataWhenImportSameData() throws Exception {
        Sentences deSentence = DefaultData.aSentence()
                .id(2L)
                .lang(Language.Code.DE.getIso639_3Value())
                .text("Sie bestand die Prüfung.")
                .build();
        Sentences enSentence = DefaultData.aSentence()
                .id(4L)
                .lang(Language.Code.EN.getIso639_3Value())
                .text("She passed the examination.")
                .build();
        sentencesService.saveAll(Lists.newArrayList(deSentence, enSentence));
        File file = getFile("tatoeba/links.tar.bz2");
        fileDataImporter.importLinksData(file, fileId());
        fileDataImporter.importLinksData(file, fileId());

        Iterable<Sentences> foundRecords = sentencesService.findAll();
        assertThat(foundRecords).hasSize(2);
        Optional<Sentences> foundSentences = sentencesService.findById(2L);
        assertThat(foundSentences.get().getTranslations()).extracting(Translation::getLang).containsExactly(Language.Code.EN.getIso639_3Value());
        assertThat(foundSentences.get().getTranslations()).extracting(Translation::getSentences).containsExactly(enSentence);
    }

    private File getFile(String s) {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(s).getFile());
    }
}