package com.j0rsa.cardsbuddy.leo.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mType", propOrder = {
        "t"
})
public class MType {
    protected TType t;

    String getFlectDescription() {
        return t != null
                ? t.getValue()
                : null;
    }
}
