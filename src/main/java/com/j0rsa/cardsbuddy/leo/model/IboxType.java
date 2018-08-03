package com.j0rsa.cardsbuddy.leo.model;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "iboxType", propOrder = {
        "pron",
        "flecttab",
        "extlink",
        "linguatec"
})
public class IboxType {

    protected PronType pron;
    protected FlecttabType flecttab;
    @XmlElement(required = true)
    protected String extlink;
    protected String linguatec;
    @XmlAttribute(name = "ajaxid")
    protected String ajaxid;

    public String getWordType() {
        return flecttab!= null
                ? flecttab.stemType
                : null;
    }
}
