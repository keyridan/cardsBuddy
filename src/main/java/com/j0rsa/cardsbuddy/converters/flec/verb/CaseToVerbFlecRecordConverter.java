package com.j0rsa.cardsbuddy.converters.flec.verb;

import com.j0rsa.cardsbuddy.controller.model.leo.flec.verb.VerbFlecRecord;
import com.j0rsa.cardsbuddy.leo.model.flec.CaseType;
import com.j0rsa.cardsbuddy.leo.model.flec.verb.VerbType;
import io.vavr.Tuple2;
import org.assertj.core.util.Lists;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CaseToVerbFlecRecordConverter implements Converter<CaseType, VerbFlecRecord> {
    private String radicalMask = "#radical#";
    private String radicalMaskForPrefAndEnding = String.format("%s %s", radicalMask, radicalMask);
    private String radicalTemplate = "%s%s";

    @Override
    public VerbFlecRecord convert(CaseType aCase) {
        String radical = aCase.getRadical();
        return VerbFlecRecord.builder()
                .highlights(highlights(aCase.getVerb()))
                .value(value(aCase.getVerb(), radical))
                .build();
    }

    private String value(VerbType verb, String radical) {
        return verb.parseValuesByNames(namesToParse())
                .stream()
                .map(collectValuesWithRadicalIfNeeded())
                .collect(Collectors.joining(" "))
                .replaceAll(radicalMaskForPrefAndEnding, radical)
                .replaceAll(radicalMask, radical)
                .replaceAll("\\)/\\(", "/");
    }

    private Function<Tuple2<String, List<String>>, String> collectValuesWithRadicalIfNeeded() {
        return namedElement -> namedElement._2.stream()
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

    private List<String> highlights(VerbType verb) {
        return verb.parseValuesByNames(Lists.newArrayList("pref", "ending")).stream()
                .flatMap(namedElement -> namedElement._2.stream())
                .collect(Collectors.toList());
    }
}
