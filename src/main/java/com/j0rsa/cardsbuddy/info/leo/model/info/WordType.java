package com.j0rsa.cardsbuddy.info.leo.model.info;

import lombok.Data;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.XmlType;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wordType", propOrder = {
        "value"
})
public class WordType {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "vocab")
    protected String vocab;

}
