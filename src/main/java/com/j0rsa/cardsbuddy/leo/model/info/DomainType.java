package com.j0rsa.cardsbuddy.leo.model.info;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "domainType", propOrder = {
        "small"
})
public class DomainType implements ValueContainer {
    protected SmallType small;

    @Override
    public String parseValues() {
        return small.parseValues();
    }
}
