package com.j0rsa.cardsbuddy.converters.flec.verb;

import com.j0rsa.cardsbuddy.common.Highlight;
import com.j0rsa.cardsbuddy.controller.model.leo.flec.verb.VerbFlecRecord;
import com.j0rsa.cardsbuddy.info.leo.model.flec.CaseType;
import com.j0rsa.cardsbuddy.info.leo.model.flec.verb.VerbType;
import io.vavr.Tuple2;
import org.assertj.core.util.Lists;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class CaseToVerbFlecRecordConverter implements Converter<CaseType, VerbFlecRecord> {
    private String radicalMask = "#radical#";
    private String radicalMaskForPrefAndEnding = String.format("%s %s", radicalMask, radicalMask);
    private String radicalTemplate = "%s%s";

    @Override
    public VerbFlecRecord convert(CaseType aCase) {
        return aCase.verbExist()
                ? VerbFlecRecord.builder()
                .highlights(highlights(aCase.getVerb(), aCase.getRadical()))
                .value(value(aCase.getVerb(), aCase.getRadical()))
                .build()
                : null;
    }

    private String value(VerbType verb, String radical) {
        return verb.parseValuesByNames(namesToParse())
                .stream()
                .map(this::collectValuesWithRadicalsIfNeeded)
                .filter(elementIsEmpty())
                .collect(Collectors.joining(" "))
                .replaceAll(radicalMaskForPrefAndEnding, radical)
                .replaceAll(radicalMask, radical)
                .replaceAll("\\)/\\(", "/");
    }

    private String collectValuesWithRadicalsIfNeeded(Tuple2<String, List<String>> namedElement) {
        return namedElement._2.isEmpty()
                ? addRadicalIfNeeded(namedElement, "")
                : namedElement._2.stream()
                .map(element -> addRadicalIfNeeded(namedElement, element))
                .collect(Collectors.joining("/"));
    }

    private String addRadicalIfNeeded(Tuple2<String, List<String>> namedElement, String element) {
        if (namedElement._1.equals("pref")) {
            return String.format(radicalTemplate, element, radicalMask);
        }
        if (namedElement._1.equals("ending")) {
            return String.format(radicalTemplate, radicalMask, element);
        }
        return element;
    }

    private ArrayList<String> namesToParse() {
        return Lists.newArrayList(
                "prep"
                , "rpron"
                , "aux"
                , "ppron"
                , "pref"
                , "ending"
                , "part"
        );
    }

    private List<Highlight> highlights(VerbType verb, String radical) {
        return verb.parseValuesByNames(Lists.newArrayList("pref", "ending")).stream()
                .map(collectHighlights(radical))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private Function<Tuple2<String, List<String>>, List<Highlight>> collectHighlights(String radical) {
        return namedElement -> namedElement._2.stream()
                .filter(elementIsEmpty())
                .map(element -> createHighlight(radical, namedElement, element))
                .collect(Collectors.toList());
    }

    private Highlight createHighlight(String radical, Tuple2<String, List<String>> namedElement, String element) {
        String wordPart = wordPartFromElement(radical, namedElement, element);
        return Highlight.builder()
                .wordPart(wordPart)
                .value(element)
                .build();
    }

    private String wordPartFromElement(String radical, Tuple2<String, List<String>> namedElement, String element) {
        return addRadicalIfNeeded(namedElement, element)
                .replaceAll(radicalMaskForPrefAndEnding, radical)
                .replaceAll(radicalMask, radical);
    }

    private Predicate<String> elementIsEmpty() {
        return element -> !element.isEmpty();
    }
}
