package com.j0rsa.cardsbuddy.converters;

import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

@Slf4j
public abstract class XmlUnmarshaller<T, V> {
    private final Unmarshaller unmarshaller;

    public XmlUnmarshaller(Class<V> clazz) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        this.unmarshaller = jaxbContext.createUnmarshaller();
    }

    protected Try<T> unmarshall(String xml) {
        StringReader reader = new StringReader(xml);
        return Try.of(() -> unmarshaller.unmarshal(reader))
                .onFailure(e -> log.error("Error", e))
                .map(this::getValue)
                .onFailure(e -> log.error("Error", e));
    }

    private T getValue(Object element) {
        return ((JAXBElement<T>) element).getValue();
    }
}
