package com.j0rsa.cardsbuddy.converters;

import com.j0rsa.cardsbuddy.leo.model.flec.HtmlType;
import com.j0rsa.cardsbuddy.leo.model.flec.ObjectFactory;
import com.j0rsa.cardsbuddy.translation.exceptions.ParserException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;

@Component
public class XmlToHtmlConverter extends XmlUnmarshaller<HtmlType, ObjectFactory> implements Converter<String, HtmlType> {

    public XmlToHtmlConverter() throws JAXBException {
        super(ObjectFactory.class);
    }

    @Override
    public HtmlType convert(String xml) {
        return unmarshall(xml)
                .getOrElseThrow(ParserException::new);
    }
}
