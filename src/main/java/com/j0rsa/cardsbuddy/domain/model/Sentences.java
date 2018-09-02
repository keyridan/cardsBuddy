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
    @Builder.Default
    @Embedded
    private List<Translation> translations = Lists.newArrayList();

    static public Sentences createFrom(List<Translation> translations, Sentences sentences) {
        return new Sentences(sentences.id, sentences.lang, sentences.text, translations);
    }

    public boolean addTranslationIfNotExist(Sentences sentences) {
        if (!hasTranslation(sentences)) {
            addTranslation(sentences);
            return true;
        }
        return false;
    }

    private void addTranslation(Sentences sentences) {
        Translation translation = Translation.builder()
                .sentences(sentences)
                .lang(sentences.lang)
                .build();
        this.translations.add(translation);
    }

    public boolean hasTranslation(Sentences translation) {
        return !translations.isEmpty() && translations.stream()
                .map(Translation::getSentences)
                .collect(Collectors.toList())
                .contains(translation);
    }
}
