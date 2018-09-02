package com.j0rsa.cardsbuddy.integration.info.tatoeba;

import com.j0rsa.cardsbuddy.domain.model.Sentences;
import com.j0rsa.cardsbuddy.domain.service.SentencesService;
import io.vavr.Tuple2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static com.j0rsa.cardsbuddy.integration.common.FileExtractor.unpackAndReadData;

@Service
public class TatoebaFileDataImporter {
    private final SentencesService sentencesService;

    @Autowired
    public TatoebaFileDataImporter(SentencesService sentencesService) {
        this.sentencesService = sentencesService;
    }

    public void importSentencesData(File file, UUID updateId) throws IOException {
        unpackAndReadData(file, lineParts1 -> {
            Sentences newSentence = createSentence(lineParts1, updateId);
            sentencesService.findById(newSentence.getId()).ifPresent(
                    foundSentence -> newSentence.setTranslations(foundSentence.getTranslations())
            );
            sentencesService.saveIfSupportedLanguage(newSentence);
        });
    }

    public void importLinksData(File file, UUID updateId) throws IOException {
        unpackAndReadData(file, lineParts1 -> {
            Tuple2<Long, Long> link = parseLink(lineParts1);
            sentencesService.findById(link._1)
                    .ifPresent(sentence ->
                            sentencesService.findById(link._2)
                                    .ifPresent(translation -> {
                                        sentence.addTranslationOrReplace(translation, updateId);
                                        sentencesService.save(sentence);
                                    })
                    );
        });
    }

    private Sentences createSentence(String[] lineParts, UUID updateId) {
        return Sentences.builder()
                .id(longValue(lineParts[0]))
                .lang(lineParts[1])
                .text(lineParts[2])
                .updateId(updateId)
                .build();
    }

    private Tuple2<Long, Long> parseLink(String[] lineParts) {
        return new Tuple2<>(longValue(lineParts[0]), longValue(lineParts[1]));
    }

    private Long longValue(String linePart) {
        return Long.valueOf(linePart);
    }
}
