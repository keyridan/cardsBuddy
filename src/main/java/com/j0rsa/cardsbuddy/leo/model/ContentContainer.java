package com.j0rsa.cardsbuddy.leo.model;

import javax.xml.bind.JAXBElement;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ContentContainer implements ValueContainer {
    abstract List<Serializable> getContent();

    private String getComplexValue(Serializable element) {
        Object value = ((JAXBElement) element).getValue();
        return value instanceof String
                ? getStringValue(value)
                : containerValue(value);
    }

    private String containerValue(Object value) {
        return value instanceof ValueContainer
                ? (((ValueContainer) value).parseValues())
                : null;
    }

    public String parseValues() {
        return getContent().stream()
                .filter(this::isStringOrJaxBElement)
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

    private String getStringValue(Object element) {
        return ((String) element).trim();
    }

    private boolean isStringOrJaxBElement(Serializable contentElement) {
        return isString(contentElement) || isJaxBElement(contentElement);
    }

    private boolean isJaxBElement(Serializable contentElement) {
        return contentElement.getClass().equals(JAXBElement.class);
    }

    private boolean isString(Serializable contentElement) {
        return contentElement.getClass().equals(String.class);
    }
}
