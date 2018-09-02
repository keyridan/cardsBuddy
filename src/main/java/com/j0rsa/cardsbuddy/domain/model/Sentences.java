package com.j0rsa.cardsbuddy.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.assertj.core.util.Lists;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.jdo.annotations.Embedded;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Document
public class Sentences {
    @Id
    private Long id;
    private String lang;
    private String text;
    private UUID updateId;
    @Builder.Default
    @Embedded
    private List<Translation> translations = Lists.newArrayList();

    static public Sentences createFrom(List<Translation> translations, Sentences sentences) {
        return new Sentences(sentences.id, sentences.lang, sentences.text, sentences.updateId, translations);
    }

    public void addTranslationOrReplace(Sentences sentences, UUID updateId) {
        removeIfExist(sentences);
        addTranslation(sentences, updateId);
    }

    private void addTranslation(Sentences sentences, UUID updateId) {
        Translation translation = Translation.builder()
                .sentences(sentences)
                .lang(sentences.lang)
                .updateId(updateId)
                .build();
        this.translations.add(translation);
    }

    public void removeIfExist(Sentences sentences) {
        if (!translations.isEmpty()) {
            this.translations = translations.stream()
                    .filter(existentTranslation -> !existentTranslation.getSentences().getId().equals(sentences.id))
                    .collect(Collectors.toList());
        }
    }
}
