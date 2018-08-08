package com.j0rsa.cardsbuddy.converters;

import com.j0rsa.cardsbuddy.info.leo.model.info.ObjectFactory;
import com.j0rsa.cardsbuddy.info.leo.model.info.XmlInfo;
import com.j0rsa.cardsbuddy.translation.exceptions.ParserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;

@Component
@Slf4j
public class XmlToXmlInfoConverter extends XmlUnmarshaller<XmlInfo, ObjectFactory> implements Converter<String, XmlInfo> {

    public XmlToXmlInfoConverter() throws JAXBException {
        super(ObjectFactory.class);
    }

    @Override
    public XmlInfo convert(String xml) {
        return unmarshall(xml)
                .getOrElseThrow(ParserException::new);
    }

}
