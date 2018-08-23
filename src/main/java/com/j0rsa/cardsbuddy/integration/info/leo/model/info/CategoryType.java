package com.j0rsa.cardsbuddy.integration.info.leo.model.info;

import lombok.Data;

import javax.xml.bind.annotation.*;


@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "categoryType", propOrder = {
        "value"
})
public class CategoryType {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "catnum")
    protected String catnum;
    @XmlAttribute(name = "type")
    protected String type;

}
