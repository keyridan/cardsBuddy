package com.j0rsa.cardsbuddy.leo.model;

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
