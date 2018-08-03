package com.j0rsa.cardsbuddy.leo.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "flecttabrefType", propOrder = {
        "small"
})
public class FlecttabrefType {
    protected SmallType small;

    String getFlectDescription() {
        return small != null
                ? small.getIDescription()
                : null;
    }

    String getFlectForm() {
        return small != null
                ? small.value()
                : null;
    }
}
