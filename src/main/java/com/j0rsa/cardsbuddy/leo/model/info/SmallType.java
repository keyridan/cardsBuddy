package com.j0rsa.cardsbuddy.leo.model.info;

import lombok.Data;
import org.assertj.core.util.Lists;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "smallType", propOrder = {
        "content"
})
public class SmallType extends ContentContainer {
    @XmlAttribute(name = "t")
    protected String t;
    @XmlElementRefs({
            @XmlElementRef(name = "m", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "sup", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "i", type = JAXBElement.class, required = false)
    })
    @XmlMixed
    protected List<Serializable> content = Lists.newArrayList();
}
