package com.j0rsa.cardsbuddy.leo.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "reprType", propOrder = {
        "domain",
        "flecttabref",
        "sr",
        "small",
        "m"
})
public class ReprType {
    protected DomainType domain;
    protected FlecttabrefType flecttabref;
    protected SrType sr;
    protected SmallType small;
    protected MType m;

    String getFlectDescription() {
        String description = getIDescription();
        return description == null
                ? getSupDescription()
                : description;
    }

    private String getSupDescription() {
        return small != null
                ? small.getSupDescription()
                : null;
    }

    private String getIDescription() {
        return flecttabref != null
                ? flecttabref.getFlectDescription()
                : null;
    }

    String getFlectForm() {
        return flecttabref != null
                ? flecttabref.getFlectForm()
                : null;
    }
}
