package com.j0rsa.cardsbuddy.info.leo.model.flec;

import com.j0rsa.cardsbuddy.info.leo.model.flec.verb.VerbtabType;
import com.j0rsa.cardsbuddy.info.leo.model.flec.noun.NountabType;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bodyType", propOrder = {
        "verbtab",
        "nountab"
})
public class BodyType {

    @XmlElement
    protected VerbtabType verbtab;

    @XmlElement
    protected NountabType nountab;
}
