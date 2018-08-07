package com.j0rsa.cardsbuddy.leo.model.info;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tType", propOrder = {
        "value"
})
public class TType {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "n")
    protected String n;
}
