package com.j0rsa.cardsbuddy.leo.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "supType", propOrder = {
        "m"
})
public class SupType {

    @XmlElement(required = true)
    protected MType m;

    String getFlectDescription() {
        return m != null
                ? m.getFlectDescription()
                : null;
    }
}
