package com.j0rsa.cardsbuddy.leo.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "iType", propOrder = {
        "m"
})
public class IType {
    protected MType m;

    String getFlectDescription() {
        return m != null
                ? m.getFlectDescription()
                : "";
    }
}
