package com.j0rsa.cardsbuddy.integration.info.leo.model.flec;

import com.j0rsa.cardsbuddy.integration.info.leo.model.flec.noun.NounType;
import com.j0rsa.cardsbuddy.integration.info.leo.model.flec.verb.VerbType;
import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "caseType", propOrder = {
        "comment",
        "radical",
        "noun",
        "verb"
})
public class CaseType {

    protected String comment;
    protected String radical;
    protected NounType noun;
    @XmlElement
    protected VerbType verb;
    @XmlAttribute(name = "cn")
    protected String cn;

    public boolean verbExist() {
        return this.verb != null && this.verb.contentExist();
    }

    public boolean nounExist() {
        return this.noun != null;
    }
}
