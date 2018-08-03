package com.j0rsa.cardsbuddy.leo.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sideType", propOrder = {
        "repr",
        "words",
        "ibox"
})
public class SideType {
    protected ReprType repr;
    protected WordsType words;
    protected IboxType ibox;
    @XmlAttribute(name = "hc")
    protected String hc;
    @XmlAttribute(name = "lang")
    protected String lang;

    public String getDescription() {
        return repr != null
                ? repr.parseValues()
                : null;
    }

    public String getWordType() {
        return ibox != null
                ? ibox.getWordType()
                : null;
    }
}
