package com.j0rsa.cardsbuddy.leo.model.info;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "iType", propOrder = {
        "m"
})
public class IType implements ValueContainer {
    protected MType m;

    @Override
    public String parseValues() {
        return m.parseValues();
    }
}
