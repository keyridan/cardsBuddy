package com.j0rsa.cardsbuddy.info.leo.model.info;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "flecttabrefType", propOrder = {
        "small"
})
public class FlecttabrefType implements ValueContainer {
    protected SmallType small;

    @Override
    public String parseValues() {
        return small != null
                ? small.parseValues()
                : null;
    }
}
