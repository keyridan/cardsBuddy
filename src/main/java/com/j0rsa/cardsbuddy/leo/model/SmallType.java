package com.j0rsa.cardsbuddy.leo.model;

import lombok.Data;
import org.assertj.core.util.Lists;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "smallType", propOrder = {
        "content"
})
public class SmallType {
    @XmlAttribute(name = "t")
    protected String t;
    @XmlElementRefs({
            @XmlElementRef(name = "m", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "sup", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "i", type = JAXBElement.class, required = false)
    })
    @XmlMixed
    protected List<Serializable> content = Lists.newArrayList();

    String getIDescription() {
        return content.isEmpty()
                ? ""
                : getITypeValue();
    }

    String getSupDescription() {
        return content.isEmpty()
                ? ""
                : getSupValue();
    }

    public String value() {
        return content.isEmpty()
                ? ""
                : getValue();
    }

    private String getValue() {
        return content.stream()
                .filter(contentElement -> contentElement.getClass().equals(String.class))
                .findFirst()
                .map(element -> ((String) element).trim())
                .orElse("");
    }

    private String getITypeValue() {
        return get(IType.class)
                .map(value -> ((IType) value).getFlectDescription())
                .orElse("");
    }

    private String getSupValue() {
        return get(SupType.class)
                .map(value -> ((SupType) value).getFlectDescription())
                .orElse("");
    }

    private Optional<Object> get(Class clazz) {
        return content.stream()
                .filter(isContentElementClass(clazz))
                .findFirst()
                .map(element -> ((JAXBElement) element).getValue());
    }

    private Predicate<Serializable> isContentElementClass(Class clazz) {
        return contentElement -> contentElement.getClass().equals(JAXBElement.class)
                && clazz.equals(((JAXBElement) contentElement).getDeclaredType());
    }


}
