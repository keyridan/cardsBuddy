package com.j0rsa.cardsbuddy.translation.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.j0rsa.cardsbuddy.translation.exceptions.ParserException;
import com.j0rsa.cardsbuddy.translation.model.TranslatedWord;
import com.j0rsa.cardsbuddy.translation.model.Translation;
import com.j0rsa.cardsbuddy.translation.model.TranslationPair;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@Slf4j
public class TranslationParser {

    private static Function<Integer, Function<List, Optional<List<List>>>> unpackList =
            (i) -> (insideArray) -> insideArray.size() <= i ? Optional.empty() : Optional.ofNullable((List) insideArray.get(i));
    private static Function<List<List>, List<TranslatedWord>> parseListOfListToTranslatedWord =
            (lists) -> lists.stream()
                    .map(TranslatedWordParser::parse)
                    .collect(toList());

    private static Optional<List<List>> unpackList(int i, List list) {
        return unpackList.apply(i).apply(list);
    }

    public static Translation parseTranslatedWord(String json) {
        List list = parseJson(json);

        return Translation.builder()
                .translatedWords(parseTranslatedWords(list))
                .pair(parsePairFrom(list))
                .languageFrom(LanguageParser.parse(list))
                .build();
    }

    private static List<TranslatedWord> parseTranslatedWords(List list) {
        return unpackList(1, list)
                .flatMap(unpackList.apply(0))
                .flatMap(unpackList.apply(2))
                .map(parseListOfListToTranslatedWord)
                .orElseGet(ArrayList::new);
    }

    private static TranslationPair parsePairFrom(List list) {
        return unpackList(0, list)
                .flatMap(unpackList.apply(0))
                .map(TranslationPairParser::parse)
                .get();
    }

    private static List parseJson(String json) {
        return Try.of(() -> readValue(json))
                .onFailure(e -> log.error("Error", e))
                .getOrElseThrow(ParserException::new);
    }

    private static List readValue(String json) throws java.io.IOException {
        return new ObjectMapper().readValue(json, List.class);
    }
}
