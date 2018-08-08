package com.j0rsa.cardsbuddy.info.leo.model.info;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mType", propOrder = {
        "t"
})
public class MType implements ValueContainer {
    protected TType t;

    @Override
    public String parseValues() {
        return t != null
                ? t.getValue()
                : null;
    }
}
