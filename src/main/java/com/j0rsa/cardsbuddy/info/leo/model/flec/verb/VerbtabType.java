package com.j0rsa.cardsbuddy.info.leo.model.flec.verb;

import com.j0rsa.cardsbuddy.info.leo.model.flec.ExplanationType;
import com.j0rsa.cardsbuddy.info.leo.model.flec.MoodType;
import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "verbtabType", propOrder = {
        "aux",
        "explanation",
        "mood"
})
public class VerbtabType {

    @XmlElement(required = true)
    protected AuxType aux;
    @XmlElement(required = true)
    protected ExplanationType explanation;
    protected List<MoodType> mood;
    @XmlAttribute(name = "title")
    protected String title;
}
