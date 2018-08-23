package com.j0rsa.cardsbuddy.integration.info.leo.model.info;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pronType", propOrder = {
        "value"
})
public class PronType {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "url")
    protected String url;
}
