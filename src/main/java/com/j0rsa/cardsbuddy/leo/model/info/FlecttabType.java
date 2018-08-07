package com.j0rsa.cardsbuddy.leo.model.info;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "flecttabType", propOrder = {
        "value"
})
public class FlecttabType {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "stemType")
    protected String stemType;
    @XmlAttribute(name = "url")
    protected String url;
    @XmlAttribute(name = "word")
    protected String word;

}
