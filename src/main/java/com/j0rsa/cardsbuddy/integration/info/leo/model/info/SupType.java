package com.j0rsa.cardsbuddy.integration.info.leo.model.info;

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
public class SupType implements ValueContainer {

    @XmlElement(required = true)
    protected MType m;

    @Override
    public String parseValues() {
        return m != null
                ? m.parseValues()
                : null;
    }
}
