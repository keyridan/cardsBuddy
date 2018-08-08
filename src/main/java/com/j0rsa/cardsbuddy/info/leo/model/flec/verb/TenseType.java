package com.j0rsa.cardsbuddy.info.leo.model.flec.verb;

import com.j0rsa.cardsbuddy.info.leo.model.flec.CaseType;
import lombok.Data;
import org.assertj.core.util.Lists;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tenseType", propOrder = {
        "_case"
})
public class TenseType {

    @XmlElement(name = "case")
    protected List<CaseType> _case = Lists.newArrayList();
    @XmlAttribute(name = "title")
    protected String title;

    public boolean caseExist() {
        return this._case != null && !this._case.isEmpty();
    }
}
