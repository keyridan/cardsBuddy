package com.j0rsa.cardsbuddy.leo;

import com.j0rsa.cardsbuddy.leo.model.XmlInfo;
import com.j0rsa.cardsbuddy.translation.exceptions.ParserException;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

@Service
@Slf4j
public class XmlParser {
    private final Unmarshaller unmarshaller;

    public XmlParser() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(XmlInfo.class);
        this.unmarshaller = jaxbContext.createUnmarshaller();
    }

    XmlInfo fromXml(String xml) {
        StringReader reader = new StringReader(xml);
        return Try.of(() -> unmarshaller.unmarshal(reader))
                .onFailure(e -> log.error(e.toString()))
                .map(this::getValue)
                .onFailure(e -> log.error(e.toString()))
                .getOrElseThrow(ParserException::new);
    }

    private XmlInfo getValue(Object element) {
        return ((JAXBElement<XmlInfo>) element).getValue();
    }
}
