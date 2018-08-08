package com.j0rsa.cardsbuddy.info.leo.model.info;

import com.j0rsa.cardsbuddy.common.JaxBUtils;
import io.vavr.Tuple2;

import javax.xml.bind.JAXBElement;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import static com.j0rsa.cardsbuddy.common.JaxBUtils.*;

public abstract class ContentContainer implements ValueContainer {
    protected abstract List<Serializable> getContent();

    public String parseValues() {
        return getContent().stream()
                .filter(JaxBUtils::isStringOrJaxBElement)
                .map(contentElement -> isString(contentElement)
                        ? getStringValue(contentElement)
                        : getComplexValue(contentElement)
                )
                .filter(string -> {
                    String strangeString = " ";
                    return !string.matches(strangeString);
                })
                .collect(Collectors.joining(" "))
                .replaceAll("\\s+(?=\\p{Punct})", "")
                .replaceAll("\\s+", " ");
    }

    public List<Tuple2<String, List<String>>> parseValuesByNames(List<String> names) {
        List<Tuple2<String, List<JAXBElement>>> contents = getSplitedCollection(getJaxBElements());

        return contents.stream()
                .filter(namedList -> names.contains(namedList._1))
                .map(namedElement -> new Tuple2<>(namedElement._1, parseValuesByName(namedElement._2)))
                .filter(namedElement -> !namedElement._2.isEmpty())
                .collect(Collectors.toList());
    }

    private String getComplexValue(Serializable element) {
        Object value = getJaxbElementValue(element);
        return value instanceof String
                ? getStringValue(value)
                : containerValue(value);
    }

    private Object getJaxbElementValue(Serializable element) {
        return ((JAXBElement) element).getValue();
    }

    private String containerValue(Object value) {
        return value instanceof ValueContainer
                ? (((ValueContainer) value).parseValues())
                : null;
    }

    private List<String> parseValuesByName(List<JAXBElement> contentPartElement) {
        return contentPartElement.stream()
                .map(this::getComplexValue)
                .filter(value -> value != null && !value.isEmpty())
                .collect(Collectors.toList());
    }

    private List<JAXBElement> getJaxBElements() {
        return getContent().stream()
                .filter(JaxBUtils::isJaxBElement)
                .map(element -> (JAXBElement) element)
                .collect(Collectors.toList());
    }
}
